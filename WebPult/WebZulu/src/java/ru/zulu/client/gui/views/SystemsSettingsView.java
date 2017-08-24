/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;
import libMessage.client.messages.SystemsSettings;

/**
 * Интерфейс панели логина.
 * @author Носов А.В.
 */
public interface SystemsSettingsView extends IsWidget {
    
    /** 
     * Устанавливает системные настройки.
     * @param systemsSettings системные настройки
     */
    public void setSystemsSettings(SystemsSettings systemsSettings);
    
    public void setPresenter(ISystemsSettingsPresenter presenter);
    
    public interface ISystemsSettingsPresenter {
        
        /**
         * Передать системные настройки в БД.
         * @param systemsSettings системные настройки
         */
        public void save(SystemsSettings systemsSettings);
    }
}
