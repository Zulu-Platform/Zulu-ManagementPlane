/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import ru.zulu.client.event.GroupListEvent;
import ru.zulu.client.event.GroupListEventHandler;
import ru.zulu.client.event.UserInfoEvent;
import ru.zulu.client.event.UserInfoEventHandler;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.UserEditPlace;
import ru.zulu.client.gui.places.UsersListPlace;
import ru.zulu.client.gui.views.UserEditView;
import libMessage.client.types.TypeMessage;
import libMessage.client.messages.User;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class UserEditActivity extends MessageAbstractActivity implements UserEditView.IUsersEditPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    /* Идентификатор пользователя. */
    //private int id;
    /** Имя пользователя. */
    private String name;
    // End of variables declaration
    
    public UserEditActivity(UserEditPlace place) {
        this.name = place.getName();
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final UserEditView view = ClientFactory.getInstance().getUserEditView();
        view.setPresenter(this);
        eventBus.addHandler(UserInfoEvent.TYPE, new UserInfoEventHandler() {

            @Override
            public void user(UserInfoEvent event) {
                view.setUser(event.getUser());
            }
            
        });
        eventBus.addHandler(GroupListEvent.TYPE, new GroupListEventHandler() {

            @Override
            public void groupsList(GroupListEvent event) {
                view.setGroups(event.getGroups());
            }
            
        });
        panel.setWidget(view.asWidget());
        
        rqMessage(TypeMessage.RqGroups);
        if ( (name == null) || (name.isEmpty()) ) view.setUser(new User());
        else rqUserByName();
    }

    @Override
    public void save(User user) {
        rqMessage(user);
        History.newItem(UsersListPlace.VIEW_HISTORY_TOKEN+":");
    }

    @Override
    public void cansel() {
        History.newItem(UsersListPlace.VIEW_HISTORY_TOKEN+":");
    }
    
    /**
     * Запрос пользователя по имени.
     */
    private void rqUserByName() {
        User msgU = new User();
        msgU.setName(name);
        msgU.setTypeMessage(TypeMessage.RqUserByName);
        rqMessage(msgU);
    }
}
