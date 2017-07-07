/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.utils.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 *
 * @author Носов А.В.
 */
public interface Images extends ClientBundle {
    
    public Images INSTANCE = GWT.create(Images.class);
    
    @Source("menuDownload.png")
    ImageResource menuDownload();

    @Source("menuMarionnet.png")
    ImageResource menuMarionnet();
    
    @Source("menuResources.png")
    ImageResource menuResources();
}
