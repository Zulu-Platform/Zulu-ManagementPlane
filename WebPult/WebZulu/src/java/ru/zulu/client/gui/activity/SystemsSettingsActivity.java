/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import ru.zulu.client.event.SystemsSettingsEvent;
import ru.zulu.client.event.SystemsSettingsEventHandler;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.SystemsSettingsPlace;
import ru.zulu.client.gui.views.SystemsSettingsView;
import libMessage.client.types.TypeMessage;
import libMessage.client.messages.SystemsSettings;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class SystemsSettingsActivity extends MessageAbstractActivity 
                                implements SystemsSettingsView.ISystemsSettingsPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public SystemsSettingsActivity(SystemsSettingsPlace place) {
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final SystemsSettingsView view = ClientFactory.getInstance().getSystemsSettingsView();
        view.setPresenter(this);
        eventBus.addHandler(SystemsSettingsEvent.TYPE, new SystemsSettingsEventHandler() {

            @Override
            public void systemsSettings(SystemsSettingsEvent event) {
                view.setSystemsSettings(event.getSystemsSettings());
            }
            
        });
        panel.setWidget(view.asWidget());
        rqMessage(TypeMessage.RqSystemsSettings);
    }

    @Override
    public void save(SystemsSettings systemsSettings) {
        systemsSettings.setTypeMessage(TypeMessage.SystemsSettingsEdit);
        rqMessage(systemsSettings);
    }
}
