package org.jboss.errai.demo.client.local.examples.aframe;

import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 4/16/18.
 */
@Templated(value = "aframeTest.html#cc")
public class AframeTest extends Attachable {

    @Inject
    @DataField(value = "cc")
    HTMLDivElement root;

    @Inject
    Logger logger;

    @PostConstruct
    public void init() {

        org.bdshadow.gwt.aframevr.AframeScene scene = new org.bdshadow.gwt.aframevr.AframeScene(root);
        org.bdshadow.gwt.aframevr.AframeBox box = new org.bdshadow.gwt.aframevr.AframeBox();

        scene.setWidth(400);
        scene.setHeight(400);

        box.setColor("#bc2929");
        box.setPosition("-1 0.5 -3");
        box.setRotation("0 45 0");

        scene.appendChild(box);
    }

    @Override
    protected void doAttachScene() {
        document.body.appendChild(root);

    }

    @Override
    protected void doAttachInfo() {

    }
}
