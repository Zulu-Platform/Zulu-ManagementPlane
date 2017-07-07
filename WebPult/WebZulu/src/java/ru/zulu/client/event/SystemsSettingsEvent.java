/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.messages.SystemsSettings;

/**
 * Событие .
 * @author Носов А.В.
 */
public class SystemsSettingsEvent extends GwtEvent<SystemsSettingsEventHandler> {

    // Variables declaration
    public static Type<SystemsSettingsEventHandler> TYPE = new Type<SystemsSettingsEventHandler>();
    
    private SystemsSettings systemsSettings;
    // End of variables declaration

    public SystemsSettingsEvent(SystemsSettings messageSystem) {
        this.systemsSettings = messageSystem;
    }
    
    @Override
    public Type<SystemsSettingsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SystemsSettingsEventHandler handler) {
        handler.systemsSettings(this);
    }

    public SystemsSettings getSystemsSettings() {
        return systemsSettings;
    }
}
