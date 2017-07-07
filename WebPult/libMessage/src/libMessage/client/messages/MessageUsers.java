/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import libMessage.client.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Запросить/Отправить/Принять список пользователей.
 * @author Носов А.В.
 */
public class MessageUsers extends Message implements Serializable {
    
    // Variables declaration
    /** Список пользователей. */
    private List<User> users;
    // End of variables declaration
    
    /**
     * Создание сообщения .
     */
    public MessageUsers() {
    }

    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Устанавливает список пользователей.
     * @param groups список пользователей
     */
    public void setUsers(List<User> groups) {
        this.users = groups;
    }
    
    /**
     * Добавляет в список пользователя.
     * @param user пользователь
     */
    public void addUser(User user) {
        if (users == null)
            users = new ArrayList<User>();
        users.add(user);
    }
}