package org.treblereel.gwt.three4g.demo.client.local;

import elemental2.dom.HTMLDivElement;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/12/18.
 */
public class MenuDiv {
    private HTMLDivElement container = (HTMLDivElement)document.createElement("div");
    private HTMLDivElement element;

    MenuDiv() {
        document.body.appendChild(container);
        container.id ="menu_holder";
    }

    public MenuDiv addMenu(HTMLDivElement element) {
        this.element = element;
        return this;
    }

    public MenuDiv show() {
        container.appendChild(element);
        return this;
    }

    public void hide() {
        while (container.hasChildNodes()) {
            container.removeChild(container.firstChild);
        }
    }
}
