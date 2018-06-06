package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/3/18.
 */
public class DayDreamEvent extends GwtEvent<DayDreamEvent.DayDreamEventHandler> {
    public static Type<DayDreamEvent.DayDreamEventHandler> TYPE = new Type<>();

    @Override
    public Type<DayDreamEvent.DayDreamEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DayDreamEvent.DayDreamEventHandler handler) {
        handler.onDayDream(this);
    }

    public interface DayDreamEventHandler extends EventHandler {
        void onDayDream(DayDreamEvent event);
    }
}
