package org.jboss.errai.demo.client.local.mvc.view;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;

public class RootPanel extends Attachable {


    HTMLDivElement container = (HTMLDivElement) DomGlobal.document.createElement("div");
    public RootPanel(){
        container.id = "content";
    }

    @Override
    protected void doAttachScene() {

    }

    @Override
    protected void doAttachInfo() {

    }

    public HTMLDivElement asWidget() {
        return container;
    }

}
