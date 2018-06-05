package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.camera.WebGlCamera;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel on 6/1/18.
 */
public class WebGlCameraPresenter implements Presenter {
    @Override
    public void dispatch(HTMLDivElement container) {
        ((Attachable)GWT.create(WebGlCamera.class)).attach(container);
    }
}
