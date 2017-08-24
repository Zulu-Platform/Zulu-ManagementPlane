/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Интерфейс панели логина.
 * @author Носов А.В.
 */
public interface LoginView extends IsWidget {
    
    /**
     * Устанавливает логин.
     * @param login логин
     */
    public void setLogin(String login);
    
    public void setPresenter(ILoginPresenter presenter);
    
    public interface ILoginPresenter {
        
        /** 
         * Авторизация.
         * @param login имя
         * @param pass пароль
         */
        public void login(String login, String pass);
    }
}
