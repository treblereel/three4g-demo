package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebGlCameraEvent extends GwtEvent<WebGlCameraEventHandler> {
    public static Type<WebGlCameraEventHandler> TYPE = new Type<>();

    @Override
    public Type<WebGlCameraEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebGlCameraEventHandler handler) {
        handler.onWebGlCamera(this);
    }
}
