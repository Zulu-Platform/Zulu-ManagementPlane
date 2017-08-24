/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.types;

/**
 * Перечень и расшифровка ошибок.
 * @author Носов А.В.
 */
public enum TypeErrors {
    
    // Variables declaration
    /** Не верное имя пользователя. */
    ERROR_USERNAME(1, "Не верное имя пользователя.", 
                        "Не верное имя пользователя."),
    /** Не верное имя пользователя или пароль. */
    ERROR_USERNAMEPASS(2, "Не верное имя пользователя или пароль.", 
                        "Не верное имя пользователя или пароль."),
    /** Не верное имя пользователя или пароль. */
    ERROR_AUTHENTICATION(3, "Ошибка входа в систему.", 
                              "Не верное имя пользователя или пароль."),
    /** Не верное имя пользователя или пароль. */
    ERROR_ADD_USER(4, "Ошибка добавления пользователя.", 
                        "Не верное имя пользователя или пароль."),
    /** Не верное имя пользователя или пароль. */
    ERROR_EDIT_USER(4, "Ошибка редактирования пользователя.", 
                        "Не верное имя пользователя или пароль."),
    /** Ошибка удаления пользователя. */
    ERROR_REMOVE_USERBYNAME(5, "Ошибка удаления пользователя.", 
                                "Не верно задано имя пользователя."),
    /** Ошибка удаления пользователя. */
    ERROR_EDIT_SYSTEMSETTINGS(6, "Ошибка редактирования системных настроек.", 
                                "Не верно заданы системные настройки.");
    
    
    /** Код запроса. */
    private final int code;
    /** Заголовок ошибки. */
    private final String headingText;
    /** Описание ошибки. */
    private final String description;
    // End of variables declaration

    /**
     * Инициализация ошибки.
     * @param code код ошибки
     * @param headingText заголовок ошибки
     * @param description описание запроса
     */
    TypeErrors(int code, String headingText, String description) {
        this.code = code;
        this.headingText = headingText;
        this.description = description;
    }
    
    /**
     * Возвращает код ошибки.
     * @return код ошибки
     */
    public int getCode() {
        return code;
    }

    /**
     * Возвращает заголовок ошибки.
     * @return заголовок
     */
    public String getHeadingText() {
        return headingText;
    }

    /**
     * Возвращает описание ошибки.
     * @return описание ошибки.
     */
    public String getDescription() {
        return description;
    }
}
