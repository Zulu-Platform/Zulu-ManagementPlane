/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.client.messages.types;

/**
 * Перечень и расшифровка успешных запросов.
 * @author Носов А.В.
 */
public enum TypeSuccess {

    // Variables declaration
    /** Добавление группы. */
    ADD_GROUP(0, "Добавление группы."),
    /** Редактирование группы. */
    EDIT_GROUP(1, "Редактирование группы."),
    /** Удаление группы. */
    REMOVE_GROUP(2, "Удаление группы."),
    /** Добавление пользователя. */
    ADD_USER(3, "Добавление пользователя."),
    /** Редактирование пользователя. */
    EDIT_USER(4, "Редактирование пользователя."),
    /** Удаление пользователя. */
    REMOVE_USER(5, "Удаление пользователя"),
    /** Выход из системы. */
    LOGOUT(6, "Выход из системы."),
    /** Редактирование группы. */
    EDIT_SYSTEMSSETTIGNS(7, "Редактирование системных настроек."),
    /** Редактирование Ethernet интерфейса. */
    EDIT_INTERFACE_ETHERNET(8, "Редактирование Ethernet интерфейса.");
    
    /** Описание запроса. */
    private final String description;
    /** Код запроса. */
    private final int code;
    // End of variables declaration

    /**
     * Инициализация успешного запроса.
     * @param description описание запроса
     */
    TypeSuccess(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Возвращает описание запроса.
     * @return описание запроса
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код запроса.
     * @return код запроса
     */
    public int getCode() {
        return code;
    }
}
