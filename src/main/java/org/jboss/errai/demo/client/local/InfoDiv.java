package org.jboss.errai.demo.client.local;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/20/18.
 */

@Startup
@Singleton
public class InfoDiv {

    private HTMLDivElement container = (HTMLDivElement) DomGlobal.document.createElement("div");
    private HTMLAnchorElement info = (HTMLAnchorElement) DomGlobal.document.createElement("a");
    private HTMLElement text = (HTMLElement) DomGlobal.document.createElement("span");


    @PostConstruct
    public void init(){
        container.id = "info";
        container.appendChild(info);

        info.href = "http://threejs.org";
        info.target= "_blank";
        info.rel = "noopener";
        info.textContent = "three.js";

        container.appendChild(text);
        document.body.appendChild(container);
    }

    public InfoDiv setHrefToInfo(String t){
        info.href = t;
        return this;
    }

    public InfoDiv setTextContentToInfo(String t){
        info.textContent = t;
        return this;
    }

    public InfoDiv setTextToDesc(String textContent){
        text.textContent = textContent;
        return this;
    }

    public InfoDiv setInnetHtml(String innerHTML){
        text.innerHTML = innerHTML;
        return this;
    }

    public InfoDiv addElement(HTMLElement elm){
        container.appendChild(elm);
        return this;
    }

    public InfoDiv hide(){
        container.style.display = "none";
        return this;
    }

    public InfoDiv show(){
        container.style.display = "block";
        return this;
    }
}
