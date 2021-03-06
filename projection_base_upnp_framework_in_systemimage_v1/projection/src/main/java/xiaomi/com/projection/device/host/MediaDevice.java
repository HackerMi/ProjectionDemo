/* Automatic generated by DeviceToClazz */

package xiaomi.com.projection.device.host;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import upnp.typedef.UpnpError;
import upnp.typedef.device.Device;
import upnp.typedef.device.invocation.ActionInfo;
import upnp.typedef.exception.UpnpException;

import upnps.api.manager.UpnpManager;
import upnps.api.manager.handler.MyActionHandler;
import upnps.api.manager.handler.MyCompletionHandler;
import upnps.api.manager.host.config.DeviceConfig;
import upnps.api.manager.host.ServiceStub;

public class MediaDevice implements MyActionHandler {

    private static final String TAG = "MediaDevice";

    /**
     * deviceType & serviceType
     */
    public static final String DEVICE_TYPE = "MediaDevice";
    public static final String SERVICE_MediaRenderingControl = "MediaRenderingControl";
    public static final String SERVICE_StreamTransport = "StreamTransport";

    /**
     * serviceId
     */
    private static final String ID_MediaRenderingControl = "urn:upnp-org:serviceId:MediaRenderingControl";
    private static final String ID_StreamTransport = "urn:upnp-org:serviceId:StreamTransport";

    /**
     * device & service handler;
     */

    private Device _device;
    private Map<String, ServiceStub> _services = new HashMap<String, ServiceStub>();

    public MediaDevice(Context context, DeviceConfig config) throws UpnpException {
        _device = config.build(context);
        _services.put(ID_MediaRenderingControl, new MediaRenderingControl(_device.getService(ID_MediaRenderingControl)));
        _services.put(ID_StreamTransport, new StreamTransport(_device.getService(ID_StreamTransport)));
    }

    public String getDeviceId() {
        return _device.getDeviceId();
    }

    public void start(MyCompletionHandler handler) {
        try {
            UpnpManager.getUpnp().register(_device, handler, this);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

    public void stop(MyCompletionHandler handler) {
        try {
            UpnpManager.getUpnp().unregister(_device, handler);
        } catch (UpnpException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onAction(ActionInfo info) {
        ServiceStub handler = _services.get(info.getServiceId());
        if (handler == null) {
            Log.e(TAG, "service not found: " + info.getServiceId());
            return UpnpError.E_INTERNAL_ERROR;
        }

        return handler.onAction(info);
    }

    public MediaRenderingControl getMediaRenderingControl() {
        return (MediaRenderingControl)_services.get(ID_MediaRenderingControl);
    }

    public StreamTransport getStreamTransport() {
        return (StreamTransport)_services.get(ID_StreamTransport);
    }

}
