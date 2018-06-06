package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/5/18.
 */
public class ViveDraggingEvent extends GwtEvent<ViveDraggingEvent.ViveDraggingEventHandler> {
    public static Type<ViveDraggingEvent.ViveDraggingEventHandler> TYPE = new Type<>();

    @Override
    public Type<ViveDraggingEvent.ViveDraggingEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ViveDraggingEvent.ViveDraggingEventHandler handler) {
        handler.onViveDragging(this);
    }

    public interface ViveDraggingEventHandler extends EventHandler {
        void onViveDragging(ViveDraggingEvent event);
    }
}