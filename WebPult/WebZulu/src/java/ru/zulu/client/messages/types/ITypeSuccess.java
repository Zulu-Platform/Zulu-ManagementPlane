/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.client.messages.types;

import com.google.gwt.i18n.client.Messages;

/**
 * Перечень и расшифровка успешных запросов.
 * @author Носов А.В.
 */
public interface ITypeSuccess extends Messages {

    // Variables declaration
    /** 
     * Добавление группы.
     * @return  
     */
    String add_group();
    /** 
     * Редактирование группы.
     * @return 
     */
    String edit_group();
    /** 
     * Удаление группы.
     * @return 
     */
    String remove_group();
    /** 
     * Новая сессия.
     * @return 
     */
    String add_user();
    /** 
     * Редактирование пользователя.
     * @return 
     */
    String edit_user();
    /** 
     * Удаление пользователя.
     * @return 
     */
    String remove_user();
    /** 
     * Выход из системы.
     * @return 
     */
    String logout();
    /** 
     * Редактирование системных настроек.
     * @return 
     */
    String edit_systemssettings();
    // End of variables declaration

}
