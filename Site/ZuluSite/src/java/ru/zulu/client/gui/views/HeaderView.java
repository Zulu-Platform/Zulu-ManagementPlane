/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * 
 * @author Носов А.В.
 */
public interface HeaderView extends IsWidget {
    
    public void setPresenter(IHeaderPresenter presenter);
    
    public interface IHeaderPresenter {
    }
}
