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

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import elemental2.dom.Node;
import org.jboss.errai.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;
import org.jboss.errai.demo.client.local.examples.camera.WebGlCamera;
import org.jboss.errai.demo.client.local.examples.camera.WebglCameraArray;
import org.jboss.errai.demo.client.local.examples.animation.WebglAnimationScene;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryCube;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static elemental2.dom.DomGlobal.document;
import static org.treblereel.gwt.three4g.Three.loadThreeJS;


@EntryPoint
@Templated(value = "app.html#root")
public class AppSetup {

    @Inject
    @DataField
    private HTMLDivElement root;

    @Inject
    Logger logger;

    @Inject
    //@DataField
    private HTMLUListElement list;

    @Inject
    JQueryProducer.JQuery $;

    @Inject
    protected InfoDiv info;

    public AppSetup() {
        loadThreeJS(); // only needs by errai
    }

    @PostConstruct
    public void init() {

        //addListElement("animation / cloth", WebglAnimationCloth.class);
        addListElement("animation / keyframes / json", WebGlAnimationKeyframesJson.class);
        addListElement("animation / scene", WebglAnimationScene.class);
        addListElement("camera", WebGlCamera.class);
        addListElement("camera / array", WebglCameraArray.class);
        addListElement("geometry / cube", WebglGeometryCube.class);
        addListElement("geometry / dynamic", WebglGeometryDynamic.class);
        //addListElement("MenuDemo", menuDemo);

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
