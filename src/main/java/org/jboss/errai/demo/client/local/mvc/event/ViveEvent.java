package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/4/18.
 */
public class ViveEvent extends GwtEvent<ViveEvent.ViveEventHandler> {
    public static Type<ViveEvent.ViveEventHandler> TYPE = new Type<>();

    @Override
    public Type<ViveEvent.ViveEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ViveEvent.ViveEventHandler handler) {
        handler.onVive(this);
    }

    public interface ViveEventHandler extends EventHandler {
        void onVive(ViveEvent event);
    }
}