package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import elemental2.dom.Node;
import org.jboss.errai.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;
import org.jboss.errai.demo.client.local.examples.animation.WebglAnimationScene;
import org.jboss.errai.demo.client.local.examples.camera.WebGlCamera;
import org.jboss.errai.demo.client.local.examples.camera.WebglCameraArray;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryCube;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.jboss.errai.demo.client.local.examples.vr.DayDream;
import org.jboss.errai.demo.client.local.examples.vr.Rollercoaster;
import org.jboss.errai.demo.client.local.examples.vr.Vive;
import org.jboss.errai.demo.client.local.examples.vr.ViveDragging;
import org.jboss.errai.demo.client.local.examples.vr.VivePaint;
import org.jboss.errai.demo.client.local.examples.vr.ViveSculpt;
import org.jboss.errai.demo.client.local.mvc.AppController;
import org.jboss.errai.demo.client.local.mvc.view.MainView;
import org.jboss.errai.demo.client.local.mvc.view.RootPanel;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 5/31/18.
 */
public class AppSetup implements EntryPoint {

    public static ViewSrcButton viewSrcButton = new ViewSrcButton();
    public static InfoDiv infoDiv = new InfoDiv();

    private HTMLUListElement list = (HTMLUListElement)DomGlobal.document.createElement("ul");
    private HTMLDivElement menu = (HTMLDivElement)DomGlobal.document.createElement("div");
    private RootPanel rootPanel = new RootPanel();
    private AppController appController = new AppController();



    public AppSetup() {

    }

    private void addListElement(String name, Class clazz) {
        HTMLAnchorElement elm = (HTMLAnchorElement) DomGlobal.document.createElement("a");
        elm.textContent = name;
        elm.classList.add("link");
        elm.addEventListener("click", evt -> {
            clearAndSetSelected(elm);
            Window.Location.assign("#"+clazz.getSimpleName());
        });
        list.appendChild(elm);
    }

    private void addTypeElement(String name) {
        HTMLAnchorElement elm = (HTMLAnchorElement) DomGlobal.document.createElement("a");
        elm.textContent = name.toUpperCase();
        elm.classList.add("type");
        list.appendChild(elm);
    }

    private void clearAndSetSelected(HTMLAnchorElement elm) {
        for (int i = 0; i < list.childNodes.length; i++) {
            Node node = list.childNodes.item(i);
            if (node.nodeType == Node.ELEMENT_NODE) {
                HTMLElement element = ((HTMLElement) node);
                if (element.classList.contains("selected")) {
                    element.classList.remove("selected");
                }
            }
        }
        elm.classList.add("selected");
        for (int i = 0; i < document.body.childNodes.length; i++) {
            if (document.body.childNodes.item(i).nodeType == Node.ELEMENT_NODE) {
                HTMLElement element = ((HTMLElement) document.body.childNodes.item(i));
                if (element.tagName.toLowerCase().equals("canvas")) {
                    element.remove();
                }
            }
        }
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry point.
     */
    @Override
    public void onModuleLoad() {


        menu.id = "panel";
        menu.appendChild(list);

        DomGlobal.document.body.appendChild(menu);
        DomGlobal.document.body.appendChild(rootPanel.asWidget());


        addListElement("Intro", MainView.class);
        addTypeElement("WebGL");
        addListElement(WebGlAnimationKeyframesJson.name, WebGlAnimationKeyframesJson.class);
        addListElement(WebglAnimationScene.name, WebglAnimationScene.class);
        addListElement(WebGlCamera.name, WebGlCamera.class);
        addListElement(WebglCameraArray.name, WebglCameraArray.class);
        addListElement(WebglGeometriesParametric.name, WebglGeometriesParametric.class);
        addListElement(WebglGeometryCube.name, WebglGeometryCube.class);
        addListElement(WebglGeometryDynamic.name, WebglGeometryDynamic.class);
        addTypeElement("VR");
        addListElement(DayDream.name, DayDream.class);
        addListElement(Rollercoaster.name, Rollercoaster.class);
        addListElement(Vive.name, Vive.class);
        addListElement(ViveDragging.name, ViveDragging.class);
        addListElement(VivePaint.name, VivePaint.class);
        addListElement(ViveSculpt.name, ViveSculpt.class);




        appController.dispatch(rootPanel.asWidget());
    }
}
