package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/5/18.
 */
public class ViveSculptEvent extends GwtEvent<ViveSculptEvent.ViveSculptEventHandler> {
    public static Type<ViveSculptEvent.ViveSculptEventHandler> TYPE = new Type<>();

    @Override
    public Type<ViveSculptEvent.ViveSculptEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ViveSculptEvent.ViveSculptEventHandler handler) {
        handler.onViveSculpt(this);
    }

    public interface ViveSculptEventHandler extends EventHandler {
        void onViveSculpt(ViveSculptEvent event);
    }
}
