package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.camera.WebGlCamera;
import org.jboss.errai.demo.client.local.examples.camera.WebglCameraArray;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel on 6/1/18.
 */
public class WebGlCameraPresenter implements Presenter {
    //private Attachable display = GWT.create(WebGlCamera.class);

    @Override
    public void dispatch(HTMLDivElement container) {

        for (int i = 0; i < container.childElementCount; i++) {
            container.removeChild(container.childNodes.item(i));
        }
        //container.appendChild(display.asWidget());
        container.appendChild(((Attachable)GWT.create(WebGlCamera.class)).asWidget());

    }
}
