/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.IsWidget;
import java.util.List;
import libMessage.client.messages.Group;
import libMessage.client.messages.User;

/**
 * 
 * @author Носов А.В.
 */
public interface UserEditView extends IsWidget {
    
    /**
     * Установить редактируемого пользователя.
     * @param user пользователь
     */
    public void setUser(User user);
    
    /**
     * Установить список групп.
     * @param groups групп
     */
    public void setGroups(List<Group> groups);
    
    public void setPresenter(IUsersEditPresenter presenter);
    
    public interface IUsersEditPresenter {
        
        /**
         * Сохранить изменения о пользователе.
         * @param user пользователь
         */
        public void save(User user);
        
        /**
         * Отменить редактирование.
         */
        public void cansel();
    }
}
