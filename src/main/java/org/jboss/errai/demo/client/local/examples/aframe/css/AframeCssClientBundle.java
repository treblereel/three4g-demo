package org.jboss.errai.demo.client.local.examples.aframe.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * @author Dmitrii Bocharov <bdshadow@gmail.com>
 */
public interface AframeCssClientBundle extends ClientBundle {

  AframeCssClientBundle IMPL = GWT.create(AframeCssClientBundle.class);

  @Source("fogCssClientBundle.css")
  AframeCssClientBundle.StyleWebglAnimationScene fogAnimationScene();

  interface StyleWebglAnimationScene extends CssResource {

  }
}
