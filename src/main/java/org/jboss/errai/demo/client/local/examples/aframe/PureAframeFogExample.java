package org.jboss.errai.demo.client.local.examples.aframe;

import static elemental2.dom.DomGlobal.document;

import javax.enterprise.context.ApplicationScoped;
import org.bdshadow.gwt.aframevr.AframeBox;
import org.bdshadow.gwt.aframevr.AframeScene;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.ioc.client.api.LoadAsync;

/**
 * @author Dmitrii Bocharov <bdshadow@gmail.com>
 */
@LoadAsync
@ApplicationScoped
public class PureAframeFogExample extends Attachable {

  AframeScene scene;

  @Override
  protected void doAttachScene() {
    scene = new AframeScene(document.body);
    AframeBox box = new AframeBox();

    AframeScene.Fog fog = new AframeScene.Fog();
    fog.color = "#1106e0";
    fog.near = 1f;
    fog.far = 4f;

    scene.addFog(fog);
    scene.setEmbedded();

    box.setColor("#bc2929");
    box.setPosition("-1 0.5 -3");
    box.setRotation("0 45 0");

    scene.appendChild(box);
  }

  @Override
  public void detach() {
    this.scene.getHTMLElement().parentNode.removeChild(this.scene.getHTMLElement());
  }

  @Override
  protected void doAttachInfo() {
    info.show().setHrefToInfo("https://aframe.io").setTextContentToInfo("three.js")
        .setInnetHtml("Aframe example");
  }
}
