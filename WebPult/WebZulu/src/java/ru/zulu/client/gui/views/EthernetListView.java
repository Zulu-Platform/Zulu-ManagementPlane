/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.XTemplates;
import java.util.List;
import libMessage.client.messages.Ethernet;

/**
 * Интерфейс панели отображения Ethernet интерфейсов.
 * @author Носов А.В.
 */
public interface EthernetListView extends IsWidget {
    
    /**
     * Устанавливает список ethernet интерфейсов.
     * @param ethernets группы
     */
    public void setEthernets(List<Ethernet> ethernets);
    
    public void setPresenter(IEthernetListPresenter presenter);
    
    public interface IEthernetListPresenter {
        
        /**
         * Редактирование ethernet интерфейса.
         * Если идентификатор меньше 1, то открывается создание 
         * нового ethernet интерфейса.
         * @param id идентификатор
         */
        public void goToEditEthernet(int id);
        
        /**
         * Удалить один или несколько ethernet интерфейсов.
         * @param ethernets список ethernet интерфейсов
         */
        public void goToDeleteEthernets(List<Ethernet> ethernets);
        
        /**
         * Обновить настройки Ethernet интерфейсов.
         * @param ethernets список Ethernet интерфейсов
         */
        public void updateEthernet(List<Ethernet> ethernets);
    }
    
    public interface INumberTamplate extends XTemplates {
        @XTemplates.XTemplate("<div style='text-align:right;padding:3px'>{value:number}</div>")
        SafeHtml render(Short value);
    }
    
    public interface INameTamplate extends XTemplates {
        @XTemplates.XTemplate("<div style='text-align:right;padding:3px'>{value}</div>")
        SafeHtml render(String value);
    }
}
