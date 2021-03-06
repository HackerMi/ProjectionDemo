/* Automatic generated by DeviceToClazz */

package com.xiaomi.upnp.examples.projection.source.device.sink.control;

import android.os.Parcel;
import android.util.Log;

import upnps.manager.ctrlpoint.device.AbstractDevice;
import upnp.typedef.device.Device;
import upnp.typedef.device.Service;

public class SinkDevice extends AbstractDevice {

    private static final String TAG = SinkDevice.class.getSimpleName();
    /**
     * deviceType & serviceType
     */
    public static final String DEVICE_TYPE = "SinkDevice";
    public static final String SERVICE_StreamTransport = "StreamTransport";
    public static final String SERVICE_SessionManager = "SessionManager";

    /**
     * serviceId
     */
    private static final String ID_StreamTransport = "urn:upnp-org:serviceId:StreamTransport";
    private static final String ID_SessionManager = "urn:upnp-org:serviceId:SessionManager";

    /**
     * services
     */

    private StreamTransport _StreamTransport;
    private SessionManager _SessionManager;

    public StreamTransport getStreamTransport() {
        return _StreamTransport;
    }
    public SessionManager getSessionManager() {
        return _SessionManager;
    }

    private static final Object classLock = SinkDevice.class;

    public static SinkDevice create(Device device) {
        Log.d(TAG, "create");

        synchronized (classLock) {
            SinkDevice thiz = new SinkDevice(device);

            do {
                if (! DEVICE_TYPE.equals(device.getDeviceType().getName())) {
                    Log.d(TAG, "deviceType invalid: " + device.getDeviceType());
                    thiz = null;
                    break;
                }

                if (! thiz.initialize()) {
                    Log.d(TAG, "initialize failed");
                    thiz = null;
                    break;
                }
            } while (false);

            return thiz;
        }
    }

    private SinkDevice(Device device) {
        this.device = device;
    }

    private boolean initialize() {
        boolean ret = false;

        do {
            Service theStreamTransport = device.getService(ID_StreamTransport);
            if (theStreamTransport == null) {
                Log.d(TAG, "service not found: " + ID_StreamTransport);
                break;
            }

            Service theSessionManager = device.getService(ID_SessionManager);
            if (theSessionManager == null) {
                Log.d(TAG, "service not found: " + ID_SessionManager);
                break;
            }

            _StreamTransport = new StreamTransport(theStreamTransport);
            _SessionManager = new SessionManager(theSessionManager);

            ret = true;
        } while (false);

        return ret;
    }

    public static final Creator<SinkDevice> CREATOR = new Creator<SinkDevice>() {

        @Override
        public SinkDevice createFromParcel(Parcel in) {
            return new SinkDevice(in);
        }

        @Override
        public SinkDevice[] newArray(int size) {
            return new SinkDevice[size];
         }
    };

    private SinkDevice(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        device = in.readParcelable(Device.class.getClassLoader());
        initialize();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(device, flags);
    }
}
