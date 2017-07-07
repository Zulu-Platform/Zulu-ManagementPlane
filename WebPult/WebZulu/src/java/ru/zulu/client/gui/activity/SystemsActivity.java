/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.zulu.client.event.SystemsSettingsEvent;
import ru.zulu.client.event.SystemsSettingsEventHandler;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.SystemsPlace;
import ru.zulu.client.gui.views.SystemsView;
import libMessage.client.types.TypeMessage;

/**
 * логика системной панели.
 * @author Носов А.В.
 */
public class SystemsActivity extends MessageAbstractActivity implements SystemsView.ISystemsPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public SystemsActivity(SystemsPlace place) {
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final SystemsView view = ClientFactory.getInstance().getSystemsView();
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
}
