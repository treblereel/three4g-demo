package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebglGeometryDynamicEvent extends GwtEvent<WebglGeometryDynamicEvent.WebglGeometryDynamicEventHandler> {
    public static Type<WebglGeometryDynamicEvent.WebglGeometryDynamicEventHandler> TYPE = new Type<>();

    @Override
    public Type<WebglGeometryDynamicEvent.WebglGeometryDynamicEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebglGeometryDynamicEvent.WebglGeometryDynamicEventHandler handler) {
        handler.onWebglGeometryDynamic(this);
    }

    public interface WebglGeometryDynamicEventHandler extends EventHandler {
        void onWebglGeometryDynamic(WebglGeometryDynamicEvent event);
    }
}
