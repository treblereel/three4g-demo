package org.treblereel.gwt.three4g.demo.client.api;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsType;
import org.treblereel.gwt.three4g.cameras.OrthographicCamera;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/14/18.
 */
@JsType(isNative = true, namespace = "THREE")
public class FlyControls {


    public float movementSpeed;
    public HTMLDivElement domElement;
    public float rollSpeed;
    public boolean autoForward;
    public boolean dragToLook;

    public FlyControls(PerspectiveCamera camera) {
    }

    public FlyControls(OrthographicCamera cameraOrtho) {
    }

    public native void update(float delta);

    public native void dispose();
}
