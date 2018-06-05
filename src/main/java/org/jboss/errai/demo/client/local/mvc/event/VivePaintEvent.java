package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/5/18.
 */
public class VivePaintEvent extends GwtEvent<VivePaintEvent.VivePaintEventHandler> {
    public static Type<VivePaintEvent.VivePaintEventHandler> TYPE = new Type<>();

    @Override
    public Type<VivePaintEvent.VivePaintEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(VivePaintEvent.VivePaintEventHandler handler) {
        handler.onVivePaint(this);
    }

    public interface VivePaintEventHandler extends EventHandler {
        void onVivePaint(VivePaintEvent event);
    }
}
