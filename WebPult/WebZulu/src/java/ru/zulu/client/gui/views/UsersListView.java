/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;
import java.util.List;
import libMessage.client.messages.User;

/**
 * 
 * @author Носов А.В.
 */
public interface UsersListView extends IsWidget {
    
    /**
     * Устанавливает список пользоватлей.
     * @param users пользователи
     */
    public void setUsers(List<User> users);
    
    public void setPresenter(IUsersListPresenter presenter);
    
    public interface IUsersListPresenter {
        
        /**
         * Редактирование пользователя.
         * @param name идентификатор
         */
        public void goToEditUser(String name);
        
        /**
         * Удалить одного или нескольких пользователей.
         * @param users список пользователей
         */
        public void goToDeleteUsers(List<User> users);
    }
}
