package org.jboss.errai.demo.client.local.mvc.view;

import org.jboss.errai.demo.client.local.AppSetup;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.utils.StatsProducer;


/**
 * Created by treblereel on 6/14/16.
 */
public class MainView extends Attachable {


    public MainView() {
        root.textContent = "MainView2";
    }

    @Override
    protected void doAttachScene() {

    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.hide();
    }
}
