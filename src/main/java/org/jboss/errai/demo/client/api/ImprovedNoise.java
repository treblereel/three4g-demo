package org.jboss.errai.demo.client.api;

/**
 * @author Dmitrii Bocharov <bdshadow@gmail.com>
 */

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class ImprovedNoise {

  public native float noise(float x, float y, float z);

}
