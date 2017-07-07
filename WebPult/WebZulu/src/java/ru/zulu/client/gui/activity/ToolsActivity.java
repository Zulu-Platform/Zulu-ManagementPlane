/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.ToolsPlace;
import ru.zulu.client.gui.types.TypeMenu;
import ru.zulu.client.gui.views.ToolsView;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class ToolsActivity extends AbstractCompositeActivity implements ToolsView.IToolsPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private final TypeMenu typeMenu;
    // End of variables declaration
    
    public ToolsActivity(ToolsPlace place) {
        this.typeMenu = place.getTypeMenu();
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final ToolsView view = ClientFactory.getInstance().getToolsView();
        view.setPresenter(this);
        view.setTypeMenu(typeMenu);
        panel.setWidget(view.asWidget());
    }

    @Override
    public void reboot() {
    }

    @Override
    public void resetSettings() {
    }

    @Override
    public void resetSystem() {
    }

    @Override
    public void uploadFileSettings() {
    }

    @Override
    public void downloadFileSettings() {
    }

    @Override
    public void uploadFileApp() {
    }

    @Override
    public void downloadFileApp() {
    }
}
