/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.receives;

import java.util.List;
import libMessage.client.messages.Group;
import libMessage.client.messages.SystemsSettings;
import libMessage.client.messages.User;
import org.freedesktop.dbus.DBusInterface;

/**
 * 
 * @author Носов А.В.
 */
public interface DBusReceive extends DBusInterface {
    
    
    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    public List<User> getUsersList();
    
    /**
     * Возвращает системные настройки Ethernet.
     * @return системные настройки
     */
    public SystemsSettings getSystemsSettings();
    
    /**
     * Удаляет пользователя из системы.
     * @param username имя пользователя
     * @return ответ
     */
    public String removeUser(String username);
    
    /**
     * Добавляет пользователя.
     * @param user пользователь
     * @return результат
     */
    public String addUser(User user);
    
    /**
     * Редактирование пользователя.
     * @param user пользователь
     * @return результат
     */
    public String editUser(User user);
    
    /**
     * Возвращает пользователя по его имени.
     * @param name имя пользователя
     * @return пользователь
     */
    public User getUserByName(String name);
    
    /**
     * Возвращает список групп.
     * @return список групп
     */
    public List<Group> getGroupList();
    
    /**
     * Редактирование системных настроек.
     * При успешном редактировании необходимо перегрузить систему.
     * @param ss системные настройки
     * @return результат
     */
    public String editSystemSettings(SystemsSettings ss);
}
