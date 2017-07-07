/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Интерфейс панели .
 * @author Носов А.В.
 */
public interface ToolsView extends IsWidget {
    
    void setTypeMenu(TypeMenu typeMenu);
    
    public void setPresenter(IToolsPresenter presenter);
    
    public interface IToolsPresenter extends  IPresenter {
        
        /** Перезапуск системы. */
        void reboot();
        
        /** Сброс настроек системы. */
        void resetSettings();
        /** Системный сброс. */
        void resetSystem();
        
        /** Загрузить файл настроек. */
        void uploadFileSettings();
        /** Сохранить файл настроек. */
        void downloadFileSettings();
        
        /** Загрузить файл ПО. */
        void uploadFileApp();
        /** Сохранить файл ПО. */
        void downloadFileApp();
    }
}
