package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.mvc.view.MainView;


public class MainPresenter implements Presenter{

    @Override
    public void dispatch(HTMLDivElement container) {
        ((Attachable)GWT.create(MainView.class)).attach(container);

    }
}
