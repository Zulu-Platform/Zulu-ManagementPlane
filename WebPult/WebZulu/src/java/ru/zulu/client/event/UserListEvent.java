/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import java.util.List;
import libMessage.client.systems.MessageSystem;
import libMessage.client.messages.User;

/**
 * Событие ошибки.
 * @author Носов А.В.
 */
public class UserListEvent extends GwtEvent<UserListEventHandler> {

    // Variables declaration
    public static Type<UserListEventHandler> TYPE = new Type<UserListEventHandler>();
    
    /** Список пользователей. */
    private List<User> users;
    // End of variables declaration

    public UserListEvent(List<User> users) {
        this.users = users;
    }
    
    @Override
    public Type<UserListEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UserListEventHandler handler) {
        handler.usersList(this);
    }

    /**
     * Возвращает список пользователей.
     * @return пользователи
     */
    public List<User> getUsers() {
        return users;
    }
}
