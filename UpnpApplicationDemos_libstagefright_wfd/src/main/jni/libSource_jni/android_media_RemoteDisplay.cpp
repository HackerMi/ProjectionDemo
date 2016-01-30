/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#define LOG_TAG "RemoteDisplay"

#include "jni.h"
#include "JNIHelp.h"

#include "android_os_Parcel.h"
#include "android_util_Binder.h"

#include <android_runtime/AndroidRuntime.h>
#include <android_runtime/android_view_Surface.h>
#include <android_runtime/Log.h>

#include <binder/IServiceManager.h>

#include <gui/IGraphicBufferProducer.h>

#include <media/IMediaPlayerService.h>
#include <media/IRemoteDisplay.h>
#include <media/IRemoteDisplayClient.h>

#include <utils/Log.h>

#include <ScopedUtfChars.h>

namespace android {

static struct {
    jmethodID notifyDisplayConnected;
    jmethodID notifyDisplayDisconnected;
    jmethodID notifyDisplayError;
} gRemoteDisplayClassInfo;

static const char* const CLASS_PATH_NAME  = "com/xiaomi/upnp/examples/projection/source/device/source/host/RemoteDisplay";

// ----------------------------------------------------------------------------

class NativeRemoteDisplayClient : public BnRemoteDisplayClient {
public:
    NativeRemoteDisplayClient(JNIEnv* env, jobject remoteDisplayObj) :
            mRemoteDisplayObjGlobal(env->NewGlobalRef(remoteDisplayObj)) {
    }

protected:
    ~NativeRemoteDisplayClient() {
        JNIEnv* env = AndroidRuntime::getJNIEnv();
        env->DeleteGlobalRef(mRemoteDisplayObjGlobal);
    }

public:
    virtual void onDisplayConnected(const sp<IGraphicBufferProducer>& bufferProducer,
            uint32_t width, uint32_t height, uint32_t flags, uint32_t session) {
        JNIEnv* env = AndroidRuntime::getJNIEnv();

        jobject surfaceObj = android_view_Surface_createFromIGraphicBufferProducer(env, bufferProducer);
        if (surfaceObj == NULL) {
            ALOGE("Could not create Surface from surface texture %p provided by media server.",
                  bufferProducer.get());
            return;
        }

        env->CallVoidMethod(mRemoteDisplayObjGlobal,
                gRemoteDisplayClassInfo.notifyDisplayConnected,
                surfaceObj, width, height, flags, session);
        env->DeleteLocalRef(surfaceObj);
        checkAndClearExceptionFromCallback(env, "notifyDisplayConnected");
    }

    virtual void onDisplayDisconnected() {
        JNIEnv* env = AndroidRuntime::getJNIEnv();

        env->CallVoidMethod(mRemoteDisplayObjGlobal,
                gRemoteDisplayClassInfo.notifyDisplayDisconnected);
        checkAndClearExceptionFromCallback(env, "notifyDisplayDisconnected");
    }

    virtual void onDisplayError(int32_t error) {
        JNIEnv* env = AndroidRuntime::getJNIEnv();

        env->CallVoidMethod(mRemoteDisplayObjGlobal,
                gRemoteDisplayClassInfo.notifyDisplayError, error);
        checkAndClearExceptionFromCallback(env, "notifyDisplayError");
    }

private:
    jobject mRemoteDisplayObjGlobal;

    static void checkAndClearExceptionFromCallback(JNIEnv* env, const char* methodName) {
        if (env->ExceptionCheck()) {
            ALOGE("An exception was thrown by callback '%s'.", methodName);
            LOGE_EX(env);
            env->ExceptionClear();
        }
    }
};

class NativeRemoteDisplay {
public:
    NativeRemoteDisplay(const sp<IRemoteDisplay>& display,
            const sp<NativeRemoteDisplayClient>& client) :
            mDisplay(display), mClient(client) {
    }

    ~NativeRemoteDisplay() {
        mDisplay->dispose();
    }

    void pause() {
        mDisplay->pause();
    }

    void resume() {
        mDisplay->resume();
    }

private:
    sp<IRemoteDisplay> mDisplay;
    sp<NativeRemoteDisplayClient> mClient;
};


// ----------------------------------------------------------------------------

extern "C" {

static jlong nativeListen(JNIEnv* env, jobject remoteDisplayObj, jstring ifaceStr) {
    ScopedUtfChars iface(env, ifaceStr);

    sp<IServiceManager> sm = defaultServiceManager();
    sp<IMediaPlayerService> service = interface_cast<IMediaPlayerService>(
            sm->getService(String16("media.player")));
    if (service == NULL) {
        ALOGE("Could not obtain IMediaPlayerService from service manager");
        return 0;
    }

    sp<NativeRemoteDisplayClient> client(new NativeRemoteDisplayClient(env, remoteDisplayObj));
    sp<IRemoteDisplay> display = service->listenForRemoteDisplay(
            client, String8(iface.c_str()));
    if (display == NULL) {
        ALOGE("Media player service rejected request to listen for remote display '%s'.",
                iface.c_str());
        return 0;
    }

    NativeRemoteDisplay* wrapper = new NativeRemoteDisplay(display, client);
    return reinterpret_cast<jlong>(wrapper);
}

static void nativePause(JNIEnv* env, jobject remoteDisplayObj, jlong ptr) {
    NativeRemoteDisplay* wrapper = reinterpret_cast<NativeRemoteDisplay*>(ptr);
    wrapper->pause();
}

static void nativeResume(JNIEnv* env, jobject remoteDisplayObj, jlong ptr) {
    NativeRemoteDisplay* wrapper = reinterpret_cast<NativeRemoteDisplay*>(ptr);
    wrapper->resume();
}

static void nativeDispose(JNIEnv* env, jobject remoteDisplayObj, jlong ptr) {
    NativeRemoteDisplay* wrapper = reinterpret_cast<NativeRemoteDisplay*>(ptr);
    delete wrapper;
}

// ----------------------------------------------------------------------------

/*
 * This is called by the VM when the shared library is first loaded.
 */
typedef union {
    JNIEnv* env;
    void* venv;
} UnionJNIEnvToVoid;


static JNINativeMethod gMethods[] = {
    {"nativeListen", "(Ljava/lang/String;)J",
            (void*)nativeListen },
    {"nativeDispose", "(J)V",
            (void*)nativeDispose },
    {"nativePause", "(J)V",
            (void*)nativePause },
    {"nativeResume", "(J)V",
            (void*)nativeResume },
};

int registerNativeMethods(JNIEnv* env, const char* className,
    JNINativeMethod* gMethods, int numMethods)
{
    jclass clazz;
    ALOGE("RegisterNatives start for '%s'", className);
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        ALOGE("Native registration unable to find class '%s'", className);
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        ALOGE("RegisterNatives failed for '%s'", className);
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

int register_android_media_RemoteDisplay(JNIEnv* env)
{
    int err = registerNativeMethods(env, CLASS_PATH_NAME,
            gMethods, NELEM(gMethods));

    jclass clazz = env->FindClass(CLASS_PATH_NAME);
    gRemoteDisplayClassInfo.notifyDisplayConnected =
            env->GetMethodID(clazz, "notifyDisplayConnected",
                    "(Landroid/view/Surface;IIII)V");
    gRemoteDisplayClassInfo.notifyDisplayDisconnected =
            env->GetMethodID(clazz, "notifyDisplayDisconnected", "()V");
    gRemoteDisplayClassInfo.notifyDisplayError =
            env->GetMethodID(clazz, "notifyDisplayError", "(I)V");
    return err;
}

/*
 * This is called by the VM when the shared library is first loaded.
 */
jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    UnionJNIEnvToVoid uenv;
    uenv.venv = NULL;
    jint result = -1;
    JNIEnv* env = NULL;

    ALOGE("JNI_OnLoad 33");

    if (vm->GetEnv(&uenv.venv, JNI_VERSION_1_6) != JNI_OK) {
        ALOGE("GetEnv failed");
        goto fail;
    }

    env = uenv.env;

    if (register_android_media_RemoteDisplay(env) != JNI_TRUE) {
        ALOGE("ERROR: RemoteDisplay native registration failed\n");
        goto fail;
    }

    result = JNI_VERSION_1_6;
    ALOGE("Load success");
fail:
    return result;
}

}

};
