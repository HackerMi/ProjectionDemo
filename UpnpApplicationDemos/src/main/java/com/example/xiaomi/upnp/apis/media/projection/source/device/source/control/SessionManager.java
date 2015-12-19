/* Automatic generated by DeviceToClazz */

package com.example.xiaomi.upnp.apis.media.projection.source.device.source.control;

import android.util.Log;

import java.util.List;

import upnp.typedef.ErrorCode;
import upnp.typedef.device.Argument;
import upnp.typedef.device.PropertyChanged;
import upnp.typedef.device.Service;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.device.invocation.ActionInfoCreator;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyDefinition;
import upnps.api.manager.UpnpManager;
import upnps.api.manager.ctrlpoint.device.AbstractService;
import upnps.api.manager.handler.MyCompletionHandler;
import upnps.api.manager.handler.MyEventListener;
import upnps.api.manager.handler.MyInvokeCompletionHandler;

public class SessionManager extends AbstractService {

    private static final String TAG = "SessionManager";

    public SessionManager(Service service) {
        super(service);
    }

    //-------------------------------------------------------
    // Action Names (5)
    //-------------------------------------------------------
    public static final String ACTION_GetSessionCapabilities = "GetSessionCapabilities";
    public static final String _GetSessionCapabilities_ARG_Source = "Source";
    public static final String _GetSessionCapabilities_ARG_Sink = "Sink";
    public static final String ACTION_PrepareForSession = "PrepareForSession";
    public static final String _PrepareForSession_ARG_RemoteCapabilityInfo = "RemoteCapabilityInfo";
    public static final String _PrepareForSession_ARG_PeerSessionID = "PeerSessionID";
    public static final String _PrepareForSession_ARG_Direction = "Direction";
    public static final String _PrepareForSession_ARG_SessionID = "SessionID";
    public static final String _PrepareForSession_ARG_Address = "Address";
    public static final String _PrepareForSession_ARG_ServiceInstanceIDs = "ServiceInstanceIDs";
    public static final String ACTION_GetCurrentSessionInfo = "GetCurrentSessionInfo";
    public static final String _GetCurrentSessionInfo_ARG_SessionID = "SessionID";
    public static final String _GetCurrentSessionInfo_ARG_ServiceInstanceIDs = "ServiceInstanceIDs";
    public static final String _GetCurrentSessionInfo_ARG_CapabilityInfo = "CapabilityInfo";
    public static final String _GetCurrentSessionInfo_ARG_PeerSessionID = "PeerSessionID";
    public static final String _GetCurrentSessionInfo_ARG_Address = "Address";
    public static final String _GetCurrentSessionInfo_ARG_Direction = "Direction";
    public static final String _GetCurrentSessionInfo_ARG_Status = "Status";
    public static final String ACTION_SessionComplete = "SessionComplete";
    public static final String _SessionComplete_ARG_SessionID = "SessionID";
    public static final String ACTION_GetCurrentSessionIDs = "GetCurrentSessionIDs";
    public static final String _GetCurrentSessionIDs_ARG_SessionIDs = "SessionIDs";

    //-------------------------------------------------------
    // Property Name (9)
    //-------------------------------------------------------
    public static final String PROPERTY_A_ARG_TYPE_Direction = "A_ARG_TYPE_Direction";
    public static final String PROPERTY_SinkCapabilityInfo = "SinkCapabilityInfo";
    public static final String PROPERTY_SourceCapabilityInfo = "SourceCapabilityInfo";
    public static final String PROPERTY_A_ARG_TYPE_InstanceIDs = "A_ARG_TYPE_InstanceIDs";
    public static final String PROPERTY_A_ARG_TYPE_CapabilityInfo = "A_ARG_TYPE_CapabilityInfo";
    public static final String PROPERTY_A_ARG_TYPE_SessionID = "A_ARG_TYPE_SessionID";
    public static final String PROPERTY_CurrentSessionIDs = "CurrentSessionIDs";
    public static final String PROPERTY_A_ARG_TYPE_Address = "A_ARG_TYPE_Address";
    public static final String PROPERTY_A_ARG_TYPE_SessionStatus = "A_ARG_TYPE_SessionStatus";

    //-------------------------------------------------------
    // Property value defined (2)
    //-------------------------------------------------------

    public enum A_ARG_TYPE_Direction {
        UNDEFINED,
        V_Input,
        V_Output;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_Input = "Input";
        private static final String CONST_V_Output = "Output";

        public static A_ARG_TYPE_Direction retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_Input)) {
                return V_Input;
            }

            if (value.equals(CONST_V_Output)) {
                return V_Output;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_Input:
                    value = CONST_V_Input;
                    break;

                case V_Output:
                    value = CONST_V_Output;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    public enum A_ARG_TYPE_SessionStatus {
        UNDEFINED,
        V_OK,
        V_ContentFormatMismatch,
        V_InsufficientBandwidth,
        V_UnreliableChannel,
        V_Destroy,
        V_Unknown;

        private static final String CONST_UNDEFINED = "UNDEFINED";
        private static final String CONST_V_OK = "OK";
        private static final String CONST_V_ContentFormatMismatch = "ContentFormatMismatch";
        private static final String CONST_V_InsufficientBandwidth = "InsufficientBandwidth";
        private static final String CONST_V_UnreliableChannel = "UnreliableChannel";
        private static final String CONST_V_Destroy = "Destroy";
        private static final String CONST_V_Unknown = "Unknown";

        public static A_ARG_TYPE_SessionStatus retrieveType(String value) {
            if (value.equals(CONST_UNDEFINED)) {
                return UNDEFINED;
            }

            if (value.equals(CONST_V_OK)) {
                return V_OK;
            }

            if (value.equals(CONST_V_ContentFormatMismatch)) {
                return V_ContentFormatMismatch;
            }

            if (value.equals(CONST_V_InsufficientBandwidth)) {
                return V_InsufficientBandwidth;
            }

            if (value.equals(CONST_V_UnreliableChannel)) {
                return V_UnreliableChannel;
            }

            if (value.equals(CONST_V_Destroy)) {
                return V_Destroy;
            }

            if (value.equals(CONST_V_Unknown)) {
                return V_Unknown;
            }

            return UNDEFINED;
        }

        public String getValue() {
            String value = null;
            switch (this) {
                case V_OK:
                    value = CONST_V_OK;
                    break;

                case V_ContentFormatMismatch:
                    value = CONST_V_ContentFormatMismatch;
                    break;

                case V_InsufficientBandwidth:
                    value = CONST_V_InsufficientBandwidth;
                    break;

                case V_UnreliableChannel:
                    value = CONST_V_UnreliableChannel;
                    break;

                case V_Destroy:
                    value = CONST_V_Destroy;
                    break;

                case V_Unknown:
                    value = CONST_V_Unknown;
                    break;

                default:
                    break;
            }

            return value;
        }
    }

    //-------------------------------------------------------
    // ActionList (5)
    //-------------------------------------------------------

    public interface GetSessionCapabilities_CompletedHandler {
        void onSucceed(String theSource,
                       String theSink);
        void onFailed(int errCode, String description);
    }

    public int GetSessionCapabilities(final GetSessionCapabilities_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_GetSessionCapabilities);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    do {
                        Property pSource = invocation.getResult(_GetSessionCapabilities_ARG_Source);
                        if (pSource == null) {
                            Log.d(TAG, String.format("%s not found", _GetSessionCapabilities_ARG_Source));
                            break;
                        }

                        Property pSink = invocation.getResult(_GetSessionCapabilities_ARG_Sink);
                        if (pSink == null) {
                            Log.d(TAG, String.format("%s not found", _GetSessionCapabilities_ARG_Sink));
                            break;
                        }

                        String theSource = (String) pSource.getCurrentValue();
                        String theSink = (String) pSink.getCurrentValue();

                        handler.onSucceed(theSource,
                                theSink);
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

    public interface PrepareForSession_CompletedHandler {
        void onSucceed(String theSessionID,
                       String theAddress,
                       String theServiceInstanceIDs);
        void onFailed(int errCode, String description);
    }

    public int PrepareForSession(String RemoteCapabilityInfo, String PeerSessionID, A_ARG_TYPE_Direction Direction, final PrepareForSession_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_PrepareForSession);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_PrepareForSession_ARG_RemoteCapabilityInfo, RemoteCapabilityInfo, Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }
            if (!action.setArgumentValue(_PrepareForSession_ARG_PeerSessionID, PeerSessionID, Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }
            if (!action.setArgumentValue(_PrepareForSession_ARG_Direction, Direction.getValue(), Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    do {
                        Property pSessionID = invocation.getResult(_PrepareForSession_ARG_SessionID);
                        if (pSessionID == null) {
                            Log.d(TAG, String.format("%s not found", _PrepareForSession_ARG_SessionID));
                            break;
                        }

                        Property pAddress = invocation.getResult(_PrepareForSession_ARG_Address);
                        if (pAddress == null) {
                            Log.d(TAG, String.format("%s not found", _PrepareForSession_ARG_Address));
                            break;
                        }

                        Property pServiceInstanceIDs = invocation.getResult(_PrepareForSession_ARG_ServiceInstanceIDs);
                        if (pServiceInstanceIDs == null) {
                            Log.d(TAG, String.format("%s not found", _PrepareForSession_ARG_ServiceInstanceIDs));
                            break;
                        }

                        String theSessionID = (String) pSessionID.getCurrentValue();
                        String theAddress = (String) pAddress.getCurrentValue();
                        String theServiceInstanceIDs = (String) pServiceInstanceIDs.getCurrentValue();

                        handler.onSucceed(theSessionID,
                                theAddress,
                                theServiceInstanceIDs);
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

    public interface GetCurrentSessionInfo_CompletedHandler {
        void onSucceed(String theServiceInstanceIDs,
                       String theCapabilityInfo,
                       String thePeerSessionID,
                       String theAddress,
                       A_ARG_TYPE_Direction theDirection,
                       A_ARG_TYPE_SessionStatus theStatus);
        void onFailed(int errCode, String description);
    }

    public int GetCurrentSessionInfo(String SessionID, final GetCurrentSessionInfo_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_GetCurrentSessionInfo);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_GetCurrentSessionInfo_ARG_SessionID, SessionID, Argument.Direction.IN)) {
                ret = ErrorCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    do {
                        Property pServiceInstanceIDs = invocation.getResult(_GetCurrentSessionInfo_ARG_ServiceInstanceIDs);
                        if (pServiceInstanceIDs == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionInfo_ARG_ServiceInstanceIDs));
                            break;
                        }

                        Property pCapabilityInfo = invocation.getResult(_GetCurrentSessionInfo_ARG_CapabilityInfo);
                        if (pCapabilityInfo == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionInfo_ARG_CapabilityInfo));
                            break;
                        }

                        Property pPeerSessionID = invocation.getResult(_GetCurrentSessionInfo_ARG_PeerSessionID);
                        if (pPeerSessionID == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionInfo_ARG_PeerSessionID));
                            break;
                        }

                        Property pAddress = invocation.getResult(_GetCurrentSessionInfo_ARG_Address);
                        if (pAddress == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionInfo_ARG_Address));
                            break;
                        }

                        Property pDirection = invocation.getResult(_GetCurrentSessionInfo_ARG_Direction);
                        if (pDirection == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionInfo_ARG_Direction));
                            break;
                        }

                        Property pStatus = invocation.getResult(_GetCurrentSessionInfo_ARG_Status);
                        if (pStatus == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionInfo_ARG_Status));
                            break;
                        }

                        String theServiceInstanceIDs = (String) pServiceInstanceIDs.getCurrentValue();
                        String theCapabilityInfo = (String) pCapabilityInfo.getCurrentValue();
                        String thePeerSessionID = (String) pPeerSessionID.getCurrentValue();
                        String theAddress = (String) pAddress.getCurrentValue();
                        A_ARG_TYPE_Direction theDirection = A_ARG_TYPE_Direction.retrieveType(pDirection.getCurrentValue().toString());
                        A_ARG_TYPE_SessionStatus theStatus = A_ARG_TYPE_SessionStatus.retrieveType(pStatus.getCurrentValue().toString());

                        handler.onSucceed(theServiceInstanceIDs,
                                theCapabilityInfo,
                                thePeerSessionID,
                                theAddress,
                                theDirection,
                                theStatus);
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

    public interface SessionComplete_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int SessionComplete(String SessionID, final SessionComplete_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_SessionComplete);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_SessionComplete_ARG_SessionID, SessionID, Argument.Direction.IN)) {
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

    public interface GetCurrentSessionIDs_CompletedHandler {
        void onSucceed(String theSessionIDs);
        void onFailed(int errCode, String description);
    }

    public int GetCurrentSessionIDs(final GetCurrentSessionIDs_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInfo action = ActionInfoCreator.create(service, ACTION_GetCurrentSessionIDs);
            if (action == null) {
                ret = ErrorCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = UpnpManager.getUpnp().invoke(action, new MyInvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInfo invocation) {
                    do {
                        Property pSessionIDs = invocation.getResult(_GetCurrentSessionIDs_ARG_SessionIDs);
                        if (pSessionIDs == null) {
                            Log.d(TAG, String.format("%s not found", _GetCurrentSessionIDs_ARG_SessionIDs));
                            break;
                        }

                        String theSessionIDs = (String) pSessionIDs.getCurrentValue();

                        handler.onSucceed(theSessionIDs);
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

    //-------------------------------------------------------
    // Event
    //-------------------------------------------------------

    public interface CompletionHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public interface EventListener {
        void onSubscriptionExpired();
        void onA_ARG_TYPE_SessionStatusChanged(A_ARG_TYPE_SessionStatus currentValue);
    }

    public int subscribe(final CompletionHandler handler, final EventListener listener) {
        int ret = ErrorCode.OK;

        do {
            if (this.service.isSubscribed()) {
                ret = ErrorCode.E_EVENT_SUBSCRIBED;
                break;
            }

            if (handler == null) {
                ret = ErrorCode.E_INVALID_PARAM;
                break;
            }

            if (listener == null) {
                ret = ErrorCode.E_INVALID_PARAM;
                break;
            }


            ret = UpnpManager.getUpnp().subscribe(this.service,
                    new MyCompletionHandler() {

                        @Override
                        public void onSucceed() {
                            handler.onSucceed();
                        }

                        @Override
                        public void onFailed(int errCode, String description) {
                            handler.onFailed(errCode, description);
                        }
                    },
                    new MyEventListener() {
                        @Override
                        public void onSubscriptionExpired(String serviceId) {
                            listener.onSubscriptionExpired();
                        }

                        @Override
                        public void onEvent(String serviceId, List<PropertyChanged> list) {
                            for (PropertyChanged c : list) {
                                if (c.getName().equals(PROPERTY_A_ARG_TYPE_SessionStatus)) {
                                    PropertyDefinition def = service.getPropertyDefinition(PROPERTY_A_ARG_TYPE_SessionStatus);
                                    listener.onA_ARG_TYPE_SessionStatusChanged(A_ARG_TYPE_SessionStatus.retrieveType((String)def.getDataType().toObjectValue(c.getValue())));
                                }
                            }
                        }
                    });
        } while (false);

        return ret;
    }

    public int unsubscribe(final CompletionHandler handler) {
        int ret = ErrorCode.OK;

        do {
            if (! this.service.isSubscribed()) {
                ret = ErrorCode.E_EVENT_SUBSCRIBED;
                break;
            }

            if (handler == null) {
                ret = ErrorCode.E_INVALID_PARAM;
                break;
            }

            ret = UpnpManager.getUpnp().unsubscribe(this.service,
                    new MyCompletionHandler() {
                        @Override
                        public void onSucceed() {
                            handler.onSucceed();
                        }

                        @Override
                        public void onFailed(int errCode, String description) {
                            handler.onFailed(errCode, description);
                        }
                    });
        } while (false);

        return ret;
    }

}
