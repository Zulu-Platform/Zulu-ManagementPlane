/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.messages.User;

/**
 * Событие ошибки.
 * @author Носов А.В.
 */
public class UserInfoEvent extends GwtEvent<UserInfoEventHandler> {

    // Variables declaration
    public static Type<UserInfoEventHandler> TYPE = new Type<UserInfoEventHandler>();
    
    /** Пользователь. */
    private User user;
    // End of variables declaration

    public UserInfoEvent(User user) {
        this.user = user;
    }
    
    @Override
    public Type<UserInfoEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UserInfoEventHandler handler) {
        handler.user(this);
    }

    /**
     * Возвращает пользователя из БД.
     * @return пользователь
     */
    public User getUser() {
        return user;
    }
}
