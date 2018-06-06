package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryCube;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebglGeometryCubePresenter implements Presenter {
    //private Attachable display = GWT.create(WebglGeometryCube.class);

    @Override
    public void dispatch(HTMLDivElement container) {
        for (int i = 0; i < container.childElementCount; i++) {
            container.removeChild(container.childNodes.item(i));
        }
        //container.appendChild(display.asWidget());
        container.appendChild(((Attachable)GWT.create(WebglGeometryCube.class)).asWidget());

    }
}
