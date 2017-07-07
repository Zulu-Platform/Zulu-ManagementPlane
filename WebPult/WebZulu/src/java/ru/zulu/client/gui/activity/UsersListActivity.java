/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import java.util.List;
import ru.zulu.client.event.UserListEvent;
import ru.zulu.client.event.UserListEventHandler;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.UserEditPlace;
import ru.zulu.client.gui.places.UsersListPlace;
import ru.zulu.client.gui.views.UsersListView;
import libMessage.client.messages.MessageUsers;
import libMessage.client.types.TypeMessage;
import libMessage.client.messages.User;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class UsersListActivity extends MessageAbstractActivity implements UsersListView.IUsersListPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public UsersListActivity(UsersListPlace place) {
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final UsersListView view = ClientFactory.getInstance().getUserListView();
        view.setPresenter(this);
        eventBus.addHandler(UserListEvent.TYPE, new UserListEventHandler() {
            @Override
            public void usersList(UserListEvent event) {
                view.setUsers(event.getUsers());
            }
        });
        panel.setWidget(view.asWidget());
        
        rqMessage(TypeMessage.RqUsers);
    }

    @Override
    public void goToEditUser(String name) {
        History.newItem(UserEditPlace.VIEW_HISTORY_TOKEN+":"+name);
    }

    @Override
    public void goToDeleteUsers(List<User> users) {
        if ( (users==null) || (users.size() < 1) ) return;
        MessageUsers us = new MessageUsers();
        us.setTypeMessage(TypeMessage.UsersRemove);
        us.setUsers(users);
        rqMessage(us);
        
        rqMessage(TypeMessage.RqUsers);
    }
}
