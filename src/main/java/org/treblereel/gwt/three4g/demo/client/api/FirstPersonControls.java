package org.treblereel.gwt.three4g.demo.client.api;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;
import org.treblereel.gwt.three4g.cameras.Camera;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/9/18.
 */
@JsType(isNative = true, namespace = "THREE")
public class FirstPersonControls {


    public double movementSpeed;
    public double lookSpeed;

    public FirstPersonControls(Camera camera) {

    }

    public FirstPersonControls(Camera camera, HTMLElement domElement) {

    }

    public native void dispose();

    public native void update(float delta);
}
