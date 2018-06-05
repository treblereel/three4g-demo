package org.jboss.errai.demo.client.local.mvc.presenter;

import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.vr.Rollercoaster;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/4/18.
 */
public class RollercoasterPresenter implements Presenter{

    @Override
    public void dispatch(HTMLDivElement container) {
        ((Attachable)GWT.create(Rollercoaster.class)).attach(container);
    }
}