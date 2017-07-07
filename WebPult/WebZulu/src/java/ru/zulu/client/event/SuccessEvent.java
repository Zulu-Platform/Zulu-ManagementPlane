/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.systems.MessageSystem;

/**
 * Событие успеха.
 * @author Носов А.В.
 */
public class SuccessEvent extends GwtEvent<SuccessEventHandler> {

    // Variables declaration
    public static Type<SuccessEventHandler> TYPE = new Type<SuccessEventHandler>();
    
    private MessageSystem messageSystem;
    // End of variables declaration

    public SuccessEvent(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }
    
    @Override
    public Type<SuccessEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SuccessEventHandler handler) {
        handler.success(this);
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }
}
