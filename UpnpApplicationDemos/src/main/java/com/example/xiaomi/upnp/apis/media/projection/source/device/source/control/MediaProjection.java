/* Automatic generated by DeviceToClazz */

package com.example.xiaomi.upnp.apis.media.projection.source.device.source.control;

import android.util.Log;

import upnp.typedef.ErrorCode;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.ActionInfoCreator;
import upnp.typedef.property.Property;
import upnps.api.manager.UpnpManager;
import upnps.api.manager.ctrlpoint.device.AbstractService;
import upnps.api.manager.handler.MyInvokeCompletionHandler;

public class MediaProjection extends AbstractService {

    private static final String TAG = "MediaProjection";

    public MediaProjection(Service service) {
        super(service);
    }

    //-------------------------------------------------------
    // Action Names (3)
    //-------------------------------------------------------
    public static final String ACTION_GetTransportURI = "GetTransportURI";
    public static final String _GetTransportURI_ARG_InstanceID = "InstanceID";
    public static final String _GetTransportURI_ARG_CurrentURI = "CurrentURI";
    public static final String ACTION_Start = "Start";
    public static final String _Start_ARG_InstanceID = "InstanceID";
    public static final String ACTION_Stop = "Stop";
    public static final String _Stop_ARG_InstanceID = "InstanceID";

    //-------------------------------------------------------
    // Property Name (2)
    //-------------------------------------------------------
    public static final String PROPERTY_AVTransportURI = "AVTransportURI";
    public static final String PROPERTY_A_ARG_TYPE_InstanceID = "A_ARG_TYPE_InstanceID";

    //-------------------------------------------------------
    // Property value defined (0)
    //-------------------------------------------------------

    //-------------------------------------------------------
    // ActionList (3)
    //-------------------------------------------------------

    public interface GetTransportURI_CompletedHandler {
        void onSucceed(String theCurrentURI);
        void onFailed(int errCode, String description);
    }

    public int GetTransportURI(Long InstanceID, final GetTransportURI_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_GetTransportURI);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_GetTransportURI_ARG_InstanceID, InstanceID, Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    do {
                        Property pCurrentURI = invocation.getResult(_GetTransportURI_ARG_CurrentURI);
                        if (pCurrentURI == null) {
                            Log.d(TAG, String.format("%s not found", _GetTransportURI_ARG_CurrentURI));
                            break;
                        }

                        String theCurrentURI = (String) pCurrentURI.getCurrentValue();

                        handler.onSucceed(theCurrentURI);
                    } while (false);
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Start_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Start(Long InstanceID, final Start_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_Start);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_Start_ARG_InstanceID, InstanceID, Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    handler.onSucceed();
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Stop_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Stop(Long InstanceID, final Stop_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_Stop);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_Stop_ARG_InstanceID, InstanceID, Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    handler.onSucceed();
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    //-------------------------------------------------------
    // Event
    //-------------------------------------------------------

}
