package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebglCameraArrayEvent extends GwtEvent<WebglCameraArrayEventHandler>{
    public static GwtEvent.Type<WebglCameraArrayEventHandler> TYPE = new GwtEvent.Type<>();

    @Override
    public GwtEvent.Type<WebglCameraArrayEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebglCameraArrayEventHandler handler) {
        handler.onWebglCameraArray(this);
    }
}
