/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.messages.Ethernet;

/**
 * Событие .
 * @author Носов А.В.
 */
public class EthernetByIdEvent extends GwtEvent<EthernetByIdEventHandler> {

    // Variables declaration
    public static Type<EthernetByIdEventHandler> TYPE = new Type<EthernetByIdEventHandler>();
    
    /** Ethernet порт. */
    private Ethernet ethernet;
    // End of variables declaration

    public EthernetByIdEvent(Ethernet ethernet) {
        this.ethernet = ethernet;
    }
    
    @Override
    public Type<EthernetByIdEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EthernetByIdEventHandler handler) {
        handler.ethernet(this);
    }

    /**
     * Возвращает Ethernet порт из БД.
     * @return Ethernet порт
     */
    public Ethernet getEthernet() {
        return ethernet;
    }
}
