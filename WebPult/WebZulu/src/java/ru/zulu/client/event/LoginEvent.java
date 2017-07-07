/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.messages.User;

/**
 * Событие авторизация.
 * @author Носов А.В.
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {

    // Variables declaration
    public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
    
    /** Пользователь. */
    private User user;
    // End of variables declaration
    
    public LoginEvent(User user) {
        this.user = user;
    }
    
    @Override
    public Type<LoginEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoginEventHandler handler) {
        handler.login(this);
    }
    
    /**
     * Возвращает пользователя.
     * @return пользователь
     */
    public User getUser() {
        return user;
    }
}
