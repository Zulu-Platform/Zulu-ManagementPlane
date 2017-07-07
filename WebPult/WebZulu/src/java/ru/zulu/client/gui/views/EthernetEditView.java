/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;
import libMessage.client.messages.Ethernet;

/**
 * 
 * @author Носов А.В.
 */
public interface EthernetEditView extends IsWidget {
    
    /**
     * Установить редактируемый Ethernet порт.
     * @param ethernet Ethernet порт
     */
    public void setEthernet(Ethernet ethernet);
    
    public void setPresenter(IEthernetEditPresenter presenter);
    
    public interface IEthernetEditPresenter {
        
        /**
         * Сохранить изменения Ethernet порта.
         * @param ethernet Ethernet порта
         */
        public void save(Ethernet ethernet);
        
        /**
         * Отменить редактирование.
         */
        public void cansel();
    }
}
