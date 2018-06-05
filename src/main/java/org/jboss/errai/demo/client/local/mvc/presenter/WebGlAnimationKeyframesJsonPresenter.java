package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 5/31/18.
 */
public class WebGlAnimationKeyframesJsonPresenter implements Presenter{

    @Override
    public void dispatch(HTMLDivElement container) {
        ((Attachable)GWT.create(WebGlAnimationKeyframesJson.class)).attach(container);
    }
}
