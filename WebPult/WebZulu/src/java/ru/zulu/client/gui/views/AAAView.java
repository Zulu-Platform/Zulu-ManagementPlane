/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Интерфейс панели .
 * @author Носов А.В.
 */
public interface AAAView extends IsWidget {
    
    public void setPresenter(IAAAPresenter presenter);
    
    public interface IAAAPresenter {
    }
}
