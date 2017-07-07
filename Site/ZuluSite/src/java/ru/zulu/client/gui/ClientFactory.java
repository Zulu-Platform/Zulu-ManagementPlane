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
import ru.zulu.client.messages.ITxRxDAO;
import ru.zulu.client.messages.TxRxDAOImpl;

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
    private ITxRxDAO txrx;
    private AAAView aaaView;
    
    private HeaderView headerView;
    private MenuView menuView;
    
    private MainView mainView;
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

    public ITxRxDAO getTxRx() {
        if (txrx == null) txrx = new TxRxDAOImpl();
        return txrx;
    }
        
    public AAAView getAAAView() {
        if (aaaView == null) aaaView = new AAAViewImpl();
        return aaaView;
    }

    public HeaderView getHeaderView() {
        if (headerView == null) headerView = new HeaderViewImpl();
        return headerView;
    }
    
    public MenuView getMenuView() {
        if (menuView == null) menuView = new MenuViewImpl();
        return menuView;
    }
        
    public MainView getMainView() {
        if (mainView == null) mainView = new MainViewImpl();
        return mainView;
    }
}
