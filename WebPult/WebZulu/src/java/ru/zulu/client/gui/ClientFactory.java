/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui;

import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.SimpleEventBus;
import ru.zulu.client.gui.views.*;
import ru.zulu.client.messages.txrx.TxRxDAO;
import ru.zulu.client.messages.txrx.TxRxDAOImpl;

/**
 * Реализация фабрики объектов для GUI.
 * @author Носов А.В.
 */
public class ClientFactory {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private static ClientFactory instance = null;
    
    private EventBus eventBus;
    private PlaceController placeController;
    private TxRxDAO txrx;
    
    private LoginView loginView;
    private HeaderView headerView;
    private MenuView menuView;
    private SystemsView systemsView;
    private SystemsSettingsView systemsSettingsView;
    private EthernetListView ethernetListView;
    private EthernetEditView ethernetEditView;
    private UsersListView userListView;
    private UserEditView userEditView;
    private ToolsView toolsView;
    
    private PageDevView pageDevView;
    // End of variables declaration
    
    
    /**
     * Возвращает экземпляр фабрики.
     * @return экземпляр фабрики
     */
    public static synchronized ClientFactory getInstance() {
        if (instance == null)  instance = new ClientFactory();
        return instance;
    }
    
    public EventBus getEventBus() {
        if (eventBus == null) eventBus = new SimpleEventBus();
        return eventBus;
    }

    public PlaceController getPlaceController() {
        if (placeController == null) placeController = new PlaceController(getEventBus());
        return placeController;
    }

    public TxRxDAO getTxRx() {
        if (txrx == null) txrx = new TxRxDAOImpl();
        return txrx;
    }
    
    public LoginView getLoginView() {
        if (loginView == null) loginView = new LoginViewImpl(null);
        return loginView;
    }

    public HeaderView getHeaderView() {
        if (headerView == null) headerView = new HeaderViewImpl();
        return headerView;
    }
    
    public MenuView getMenuView() {
        if (menuView == null) menuView = new MenuViewImpl();
        return menuView;
    }

    public SystemsView getSystemsView() {
        if (systemsView == null) systemsView = new SystemsViewImpl();
        return systemsView;
    }

    public SystemsSettingsView getSystemsSettingsView() {
        if (systemsSettingsView == null) systemsSettingsView = new SystemsSettingsViewImpl();
        return systemsSettingsView;
    }

    public EthernetListView getEthernetListView() {
        if (ethernetListView == null) ethernetListView = new EthernetListViewImpl();
        return ethernetListView;
    }
    
    public EthernetEditView getEthernetEditView() {
        if (ethernetEditView == null) ethernetEditView = new EthernetEditViewImpl();
        return ethernetEditView;
    }
    
    public UsersListView getUserListView() {
        if (userListView == null) userListView = new UsersListViewImpl();
        return userListView;
    }
    
    public UserEditView getUserEditView() {
        if (userEditView == null) userEditView = new UserEditViewImpl();
        return userEditView;
    }
    
    public ToolsView getToolsView() {
        if (toolsView == null) toolsView = new ToolsViewImpl();
        return toolsView;
    }
    
    public PageDevView getPageDevView() {
        if (pageDevView == null) pageDevView = new PageDevViewImpl();
        return pageDevView;
    }
}
