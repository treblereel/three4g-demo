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


    @Inject
    AframeBox box;

    @Inject
    AframeSphere sphere;

    @Inject
    AframeScene scene;

    @Inject
    AframeCylinder cylinder;

    @Inject
    AframePlane plane;

    @Inject
    AframeSky sky;

    @PostConstruct
    public void init() {


        scene.setWidth(400);
        scene.setHeight(400);

        root.offsetWidth = 400;

        box.setPosition("-1 0.5 -3");
        box.setRotation("0 45 0");

        sphere.setRadius(1.25);
        sphere.setPosition("0 1.25 -5");
        sphere.setColor("#EF2D5E");

        cylinder.setRadius(0.5);
        cylinder.setPosition("1 0.75 -3");
        cylinder.setColor("#FFC65D");
        cylinder.setHeight(1.5);

        plane.setRotation("-90 0 0");
        plane.setPosition("0 0 -4");
        plane.setColor("#7BC8A4");
        plane.setHeight(4);
        plane.setWidth(4);

        sky.setColor("#ECECEC");

        root.appendChild(scene);
        scene.appendChild(box);
        scene.appendChild(sphere);
        scene.appendChild(cylinder);
        scene.appendChild(plane);




        logger.warn("BOX IS: " + box.getColor() + " | " + box.getAttribute("placeholder"));


        logger.warn("check: " + box.object3D.name);


        box.setColor("#bc2929");

    }

    @Override
    protected void doAttachScene() {
        document.body.appendChild(root);

    }

    @Override
    protected void doAttachInfo() {

    }
}
