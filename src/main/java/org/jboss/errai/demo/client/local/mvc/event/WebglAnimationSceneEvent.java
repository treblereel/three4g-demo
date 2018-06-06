package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebglAnimationSceneEvent extends GwtEvent<WebglAnimationSceneEventHandler> {
    public static Type<WebglAnimationSceneEventHandler> TYPE = new Type<>();

    @Override
    public Type<WebglAnimationSceneEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebglAnimationSceneEventHandler handler) {
        handler.onWebglAnimationScene(this);
    }
}
