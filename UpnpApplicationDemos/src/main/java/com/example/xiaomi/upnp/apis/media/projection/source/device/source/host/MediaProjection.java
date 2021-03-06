/* Automatic generated by DeviceToClazz */

package com.example.xiaomi.upnp.apis.media.projection.source.device.source.host;

import android.util.Log;

import upnp.typedef.UpnpError;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.EventInfo;
import upnp.typedef.device.invocation.EventInfoCreator;
import upnp.typedef.exception.UpnpException;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.host.ServiceStub;

public class MediaProjection implements ServiceStub {
    private static final String TAG = "MediaProjection";

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
    // Action Result (3)
    //-------------------------------------------------------

    public class GetTransportURI_Result {
        public String theCurrentURI;
    }

    // Start has no Result

    // Stop has no Result


    //-------------------------------------------------------
    // Action Handler (3)
    //-------------------------------------------------------

    public interface Handler {
        int onGetTransportURI(Long theInstanceID, GetTransportURI_Result result);
        int onStart(Long theInstanceID);
        int onStop(Long theInstanceID);
    }

    private int handle_GetTransportURI(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_GetTransportURI_ARG_InstanceID);
        GetTransportURI_Result result = new GetTransportURI_Result();

        int error = _handler.onGetTransportURI(theInstanceID, result);
        if (error == UpnpError.OK) {
            if (! info.setArgumentValue(_GetTransportURI_ARG_CurrentURI, result.theCurrentURI, Argument.Direction.OUT)) {
                Log.d(TAG, "setArgumentValue: false");
                return UpnpError.E_ARGUMENT_VALUE_INVALID;
            }

        }

        return error;
    }

    private int handle_Start(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_Start_ARG_InstanceID);

        int error = _handler.onStart(theInstanceID);

        return error;
    }

    private int handle_Stop(ActionInfo info) {
        Long theInstanceID = (Long)info.getArgumentValue(_Stop_ARG_InstanceID);

        int error = _handler.onStop(theInstanceID);

        return error;
    }

    //-------------------------------------------------------
    // Method
    //-------------------------------------------------------

    private Service _service;
    private Handler _handler;

    public MediaProjection(Service service) {
        _service = service;
    }

    public void setHandler(Handler handler) {
        _handler = handler;
    }

    @Override
    public int onAction(ActionInfo info) {
        if (_handler == null) {
           Log.e(TAG, "handler is null");
           return UpnpError.E_ACTION_NOT_IMPLEMENTED;
        }

        if (info.getAction().getName().equals(ACTION_GetTransportURI)) {
            return handle_GetTransportURI(info);
        }

        if (info.getAction().getName().equals(ACTION_Start)) {
            return handle_Start(info);
        }

        if (info.getAction().getName().equals(ACTION_Stop)) {
            return handle_Stop(info);
        }

        return UpnpError.E_ACTION_NOT_IMPLEMENTED;
    }

    public void sendEvents() {
        EventInfo info = EventInfoCreator.create(_service);

        try {
            UpnpManager.getUpnp().sendEvents(info);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

}
