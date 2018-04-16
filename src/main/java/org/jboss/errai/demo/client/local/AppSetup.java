/**
 * Copyright (C) 2016 Red Hat, Inc. and/or its affiliates.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.JavaScriptObject;
import elemental2.core.Function;
import elemental2.dom.Document;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import elemental2.dom.Node;
import jsinterop.base.Js;
import org.jboss.errai.demo.client.local.examples.aframe.AframeTest;
import org.jboss.errai.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;
import org.jboss.errai.demo.client.local.examples.animation.WebglAnimationScene;
import org.jboss.errai.demo.client.local.examples.camera.WebGlCamera;
import org.jboss.errai.demo.client.local.examples.camera.WebglCameraArray;
import org.jboss.errai.demo.client.local.examples.geometry.ParametricGeometryExample;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryCube;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.jboss.errai.demo.client.local.examples.gpgpu.WebglGpgpuBirds;
import org.jboss.errai.demo.client.local.examples.gui.Utils;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.slf4j.Logger;
import org.treblereel.gwt.datgui4g.BooleanController;
import org.treblereel.gwt.datgui4g.GUI;
import org.treblereel.gwt.datgui4g.GUIProperty;
import org.treblereel.gwt.datgui4g.NumberControllerBox;
import org.treblereel.gwt.datgui4g.NumberControllerSlider;
import org.treblereel.gwt.datgui4g.OnChange;
import org.treblereel.gwt.datgui4g.OnClick;
import org.treblereel.gwt.datgui4g.OnFinishChange;
import org.treblereel.gwt.datgui4g.OptionController;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

import static elemental2.dom.DomGlobal.document;
//import static org.treblereel.gwt.datgui4g.DatGUIEntryPoint.loadDatGUIJS;
import static org.treblereel.gwt.datgui4g.DatGUIEntryPoint.loadDatGUIJS;
import static org.treblereel.gwt.three4g.Three.loadThreeJS;


@EntryPoint
@Templated(value = "app.html#root")
public class AppSetup {

    @Inject
    @DataField
    private HTMLDivElement root, container;

    @Inject
    Logger logger;

    @Inject
    //@DataField
    private HTMLUListElement list;

    @Inject
    JQueryProducer.JQuery $;

    @Inject
    protected InfoDiv info;

    GUI gui;

    public AppSetup() {
//        loadDatGUIJS();

        loadThreeJS(); // only needs by errai
        loadDatGUIJS();
    }

    @PostConstruct
    public void init() {

        Function result = document.registerElement("a-frame", new Document.RegisterElementOptionsType() {
            @Override
            public String getExtends() {
                return "HTMLElement";
            }

            @Override
            public void setExtends(String extends_) {

            }
        });

        logger.warn("RESULT " + result.name + " " + result.displayName + " " + result.getClass().getSimpleName());


        //addListElement("animation / cloth", WebglAnimationCloth.class);
        addListElement("animation / keyframes / json", WebGlAnimationKeyframesJson.class);
        addListElement("animation / scene", WebglAnimationScene.class);
        addListElement("camera", WebGlCamera.class);
        addListElement("camera / array", WebglCameraArray.class);
        addListElement("geometry / cube", WebglGeometryCube.class);
        addListElement("geometry / dynamic", WebglGeometryDynamic.class);
        //addListElement("geometry / parametric geometry", ParametricGeometryExample.class);
        addListElement("geometry / parametric geometry", WebglGeometriesParametric.class);
        addListElement("WebglGpgpuBirds", WebglGpgpuBirds.class);
        addListElement("AframeTest", AframeTest.class);

/*        GUIProperty property = new GUIProperty();
        property.autoPlace = false;

        //GUI gui = new GUI(property);
        GUI gui = new GUI();
        NumberControllerBox box = gui.add("float", 10f);
        box.onChange(e -> {

            logger.warn(" o1 -> " + e + " " + box.getValue());

        });
        gui.add("int", 10);
        gui.add("double", 10.0);
        gui.add("double win min 5", 10).setMin(1);
        gui.add("int with max50", 10).setMax(50);
        gui.add("int with step of 5", 10).setStep(5);
        gui.add("text field", "dat.gui rulezz");




        gui.add("slider win min and max", 0.8,  -5, 5);
        gui.add("... and step of 2", 5,  -15, 15).setStep(2);

        gui.add("function on log", new OnClick() {
            @Override
            public void onClick() {
                logger.warn("PRESSED!");
            }
        });


        gui.addColor("color css","#ffae23");
        gui.addColor("color rgb", 0, 97, 255);
        gui.addColor("color rgb + alpha", 0, 128, 255, 0.3d );


        String[] args = new String[]{"AAAA","BBBBB","CCCC"};
        gui.add("option of String[]", args, "CCCC");

        Map<String, Integer> map = new HashMap<>();
        map.put("Small", 1);
        map.put("Medium", 2);
        map.put("Big", 3);

        OptionController optionController = gui.add("option of Map", map, 3);



        GUI mediumFolder = gui.addFolder("Folder");
        mediumFolder.add("m_1", "ololo");
        mediumFolder.add("m_2", 8888);
        mediumFolder.add("m_3", false);

        GUI mediumFolder2 = mediumFolder.addFolder("SubFolder 1");
        mediumFolder2.add("m_1", "ololo");
        mediumFolder2.add("m_2", 8888);
        mediumFolder2.add("m_3", false);

        GUI mediumFolder3 = mediumFolder.addFolder("SubFolder 2");
        mediumFolder3.add("m_1", "ololo");
        mediumFolder3.add("m_2", 8888);
        mediumFolder3.add("m_3", false);



        optionController.onChange(result -> {
            String value = Js.uncheckedCast(result);
            logger.warn("result >> " +  " " + Integer.valueOf(value) + " " + result.getClass().getSimpleName());
        });

        gui.add("boolean controller", false);

        gui.finishAndBuild();

       // gui.destroy();*/


        document.body.appendChild(root);

    }

    private void addListElement(String name, Class clazz) {
        HTMLAnchorElement elm = (HTMLAnchorElement) DomGlobal.document.createElement("a");
        elm.textContent = name;
        elm.classList.add("link");
        elm.addEventListener("click", evt -> {
            IOC.getAsyncBeanManager().lookupBean(clazz).getInstance(o -> {
                clearAndSetSelected(elm);
                ((Attachable)o).attach();
            });
        });
        root.appendChild(elm);
    }

    private void clearAndSetSelected(HTMLAnchorElement elm) {
        for (int i = 0; i < root.childNodes.length; i++) {
            Node node = root.childNodes.item(i);
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

        info.hide();
    }

}
