package org.jboss.errai.demo.client.local.mvc.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 5/31/18.
 */
public class WebGlAnimationKeyframesJsonEvent extends GwtEvent<WebGlAnimationKeyframesJsonEventHander>{
    public static GwtEvent.Type<WebGlAnimationKeyframesJsonEventHander> TYPE = new GwtEvent.Type<>();

    @Override
    public GwtEvent.Type<WebGlAnimationKeyframesJsonEventHander> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(WebGlAnimationKeyframesJsonEventHander handler) {
        handler.onWebGlAnimationKeyframesJson(this);
    }
}
