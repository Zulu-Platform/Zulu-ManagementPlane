/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import java.util.List;
import libMessage.client.messages.Ethernet;

/**
 * Событие ошибки.
 * @author Носов А.В.
 */
public class EthernetListEvent extends GwtEvent<EthernetListEventHandler> {

    // Variables declaration
    public static Type<EthernetListEventHandler> TYPE = new Type<EthernetListEventHandler>();
    
    /** Список Ethernet портов. */
    private List<Ethernet> ethernets;
    // End of variables declaration

    public EthernetListEvent(List<Ethernet> ethernets) {
        this.ethernets = ethernets;
    }
    
    @Override
    public Type<EthernetListEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EthernetListEventHandler handler) {
        handler.ethernetsList(this);
    }

    /**
     * Возвращает список Ethernet портов.
     * @return Ethernet порты
     */
    public List<Ethernet> getEthernets() {
        return ethernets;
    }
}
