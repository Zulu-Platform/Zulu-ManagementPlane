/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Носов А.В.
 */
public class SquareButton extends Button {

    public SquareButton(String text, ImageResource imageResource) {
        super();
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        VerticalPanel vp = new VerticalPanel();
        vp.setHeight("100%");
        vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vp.setStyleName("label4Menu");
        vp.add(new Image(imageResource));
        Label label = new Label(text);
        vp.add(label);
        vp.setCellHeight(label, "100%");
        sb.appendHtmlConstant(vp.toString());
        setHTML(sb.toSafeHtml());
//        setPixelSize(159, 140);
        this.setStyleName("button4Menu");
    }
    
    public void seSize(int width, int height) {
        setPixelSize(width, height);
    }
}
