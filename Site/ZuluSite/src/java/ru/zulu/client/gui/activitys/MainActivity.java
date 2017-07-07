/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activitys;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.MainPlace;
import ru.zulu.client.gui.views.MainView;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class MainActivity extends AbstractActivity implements MainView.IMainPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public MainActivity(MainPlace place) {
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final MainView view = ClientFactory.getInstance().getMainView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }
}
