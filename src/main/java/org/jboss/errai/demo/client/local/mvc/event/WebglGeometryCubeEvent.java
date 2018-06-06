package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebglGeometryCubeEvent extends GwtEvent<WebglGeometryCubeEvent.WebglGeometryCubeEventHandler> {
    public static Type<WebglGeometryCubeEventHandler> TYPE = new Type<>();

    @Override
    public Type<WebglGeometryCubeEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebglGeometryCubeEventHandler handler) {
        handler.onWebglGeometryCube(this);
    }

    public interface WebglGeometryCubeEventHandler extends EventHandler {
        void onWebglGeometryCube(WebglGeometryCubeEvent event);
    }
}