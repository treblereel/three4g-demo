package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebglGeometriesParametricEvent extends GwtEvent<WebglGeometriesParametricEventHandler> {
    public static Type<WebglGeometriesParametricEventHandler> TYPE = new Type<>();

    @Override
    public Type<WebglGeometriesParametricEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebglGeometriesParametricEventHandler handler) {
        handler.onWebglGeometriesParametric(this);
    }
}
