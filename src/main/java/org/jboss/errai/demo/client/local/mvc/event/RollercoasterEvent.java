package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/4/18.
 */
public class RollercoasterEvent extends GwtEvent<RollercoasterEvent.RollercoasterEventHandler> {
    public static Type<RollercoasterEvent.RollercoasterEventHandler> TYPE = new Type<>();

    @Override
    public Type<RollercoasterEvent.RollercoasterEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RollercoasterEvent.RollercoasterEventHandler handler) {
        handler.onRollercoaster(this);
    }

    public interface RollercoasterEventHandler extends EventHandler {
        void onRollercoaster(RollercoasterEvent event);
    }
}