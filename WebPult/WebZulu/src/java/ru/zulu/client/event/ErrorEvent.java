/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.systems.MessageSystem;

/**
 * Событие ошибки.
 * @author Носов А.В.
 */
public class ErrorEvent extends GwtEvent<ErrorEventHandler> {

    // Variables declaration
    public static Type<ErrorEventHandler> TYPE = new Type<ErrorEventHandler>();
    
    private MessageSystem messageSystem;
    // End of variables declaration

    public ErrorEvent(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }
    
    @Override
    public Type<ErrorEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ErrorEventHandler handler) {
        handler.error(this);
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }
}
