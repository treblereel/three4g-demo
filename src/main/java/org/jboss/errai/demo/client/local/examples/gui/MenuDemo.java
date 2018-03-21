package org.jboss.errai.demo.client.local.examples.gui;

import com.google.gwt.animation.client.AnimationScheduler;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.Click;
import org.jboss.errai.demo.client.local.gui.GUIVR;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static elemental2.dom.DomGlobal.document;


/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 10/2/17.
 */
@ApplicationScoped
public class MenuDemo extends Attachable {

    @Inject
    Logger logger;

    @PostConstruct
    public void init() {
        WebGLRendererParameters params = new WebGLRendererParameters();
        params.antialias = true;

        webGLRenderer = new WebGLRenderer(params);
        setupWebGLRenderer(webGLRenderer);
        webGLRenderer.setClearColor(0xeebbbb);
        camera = new PerspectiveCamera(70, aspect, 0.1f, 15);
        camera.position.set(0, 0, 1.0f);
        scene = new Scene();

        GUIVR gui = GUIVR.create("settings");
        gui.name = "new_name";
        gui.id = 776;
        gui.position.set(-0.5f, 0, 0);

        Record record = new Record();
        record.id = "1";
        record.date = 20.0;
        record.data = 30.0;
        record.alive = true;
        record.car = "a";
        record.speed = "a";

        gui.addSlider(record, "id");
        gui.addSlider(record, "date", 10.0, 40.0).name("OLOLO").max(100.0).step(10.0).onChange(() -> logger.warn(" > " + record.date));


        String[] values = new String[]{"a", "b", "c"};

        gui.addSlider(record, "data");
        gui.addDropdown(record, "car", values);


        java.util.Map<String, Object> map = new java.util.HashMap<>();
        map.put("AAA", "a");
        map.put("BBB", "b");
        map.put("CCC", "c");

        gui.addButton((Click) () -> window.alert("CLICK"), "click");
        gui.addDropdown(record, "speed", map);
        gui.addCheckbox(record, "alive");

        gui.position.set(-0.25f, 1.3f, -0.5f);
        gui.rotation.z = (float) -Math.PI / 4;
        gui.scale.set(1, 1, 1);

        scene.add(gui);


        GUIVR gui2 = GUIVR.create("more settings");
        gui2.addButton((Click) () -> {
            window.alert("CLICK " + gui.id + " " + gui.name + " " + scene.id);

        }, "click");
        gui2.addDropdown(record, "speed", map);
        gui2.addCheckbox(record, "alive");


        GUIVR.enableMouse(camera);

        scene.add(gui);
        scene.add(gui2);
        schedule();
        window.addEventListener("resize", evt -> onWindowResize(), false);
    }

    public void schedule() {
        webGLRenderer.render(scene, camera);
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement.parentNode != null) {
                schedule();
            }
        });
    }

    public void doAttachScene(){
        document.body.appendChild(webGLRenderer.domElement);
        schedule();
    }

    @Override
    protected void doAttachInfo() {

    }

}
