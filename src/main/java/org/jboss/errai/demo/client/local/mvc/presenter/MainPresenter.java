package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.mvc.view.MainView;


public class MainPresenter implements Presenter{

    private Attachable display = GWT.create(MainView.class);

    @Override
    public void dispatch(HTMLDivElement container) {
        for (int i = 0; i < container.childElementCount; i++) {
            container.removeChild(container.childNodes.item(i));
        }
        container.appendChild(display.asWidget());
    }
}
