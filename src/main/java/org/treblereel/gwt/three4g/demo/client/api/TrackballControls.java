package org.treblereel.gwt.three4g.demo.client.api;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;
import org.treblereel.gwt.three4g.cameras.Camera;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.math.Vector3;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/8/18.
 */
@JsType(isNative = true, namespace = "THREE")
public class TrackballControls {

    public Vector3 target;
    public float rotateSpeed;
    public float zoomSpeed;
    public float panSpeed;
    public boolean noZoom;
    public boolean noPan;
    public boolean staticMoving;
    public float dynamicDampingFactor;
    public float minDistance;
    public float maxDistance;
    public boolean enabled;

    public TrackballControls(Camera camera) {
    }

    public TrackballControls(PerspectiveCamera camera, HTMLElement domElement) {
    }

    public native void dispose();

    public native void update();

    public native void update(double delta);
}