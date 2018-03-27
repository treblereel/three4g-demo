package org.jboss.errai.demo.client.local.examples.animation;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.treblereel.gwt.three4g.geometries.ParametricGeometry;
import org.treblereel.gwt.three4g.geometries.ParametricGeometryFunction;
import org.treblereel.gwt.three4g.math.Vector3;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/22/18.
 */
//@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Cloth")
@JsType(isNative=true, namespace="<window>", name="Cloth")
public class Cloth {

 public static int ballSize;

 public static native Vector3 plane(int width, int height);

}
