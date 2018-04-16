package org.jboss.errai.demo.client.local.examples.aframe;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.annotations.ClassNames;
import org.jboss.errai.common.client.api.annotations.Element;
import org.jboss.errai.common.client.api.annotations.Properties;
import org.jboss.errai.common.client.api.annotations.Property;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 4/16/18.
 */
@Element("a-box")
@Properties({
        @Property(name = "color", value = "#4CC3D9"),
        @Property(name = "id", value = "my_id")
})
@JsType(isNative = true, name = "HTMLElement", namespace = JsPackage.GLOBAL)
public abstract class AframeBox extends Object3D{

    public org.treblereel.gwt.three4g.core.Object3D object3D;

}
