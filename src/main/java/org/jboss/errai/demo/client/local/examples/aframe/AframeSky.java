package org.jboss.errai.demo.client.local.examples.aframe;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.annotations.Element;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 4/16/18.
 */
@Element("a-sky")
@JsType(isNative = true, name = "HTMLElement", namespace = JsPackage.GLOBAL)
public abstract class AframeSky extends Object3D  {


}
