/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.exceptions;

import java.util.ResourceBundle;
import ru.zulu.server.utils.I18N;

/**
 * Перечень и расшифровка возможных ошибок сокета.
 * @author Носов А.В.
 */
public enum ErrorsWebZulu {

    // Variables declaration
    /** Нет доступа к диску. */
    ERROR_NODISK(1, "ERROR_NODISK"),
    /** Ошибка загрузки файла. */
    ERROR_LOAD_FILE(2, "ERROR_LOAD_FILE"),
    /** Ошибка записи файла. */
    ERROR_SAVE_FILE(3, "ERROR_SAVE_FILE"),
    /** Ошибка при закрытии файла. */
    ERROR_CLOSE_FILE(4, "ERROR_CLOSE_FILE"),
    /** Не могу сохранить файл. */
    ERROR_CANNOT_SAVE(5, "ERROR_CANNOT_SAVE"),
    /** Создан новый файл. */
    WARNING_NEW_FILE(6, "WARNING_NEW_FILE"),
    /** Ошибка авторизации сессии. */
    FATAL_AUTH_SESSION(7, "FATAL_AUTH_SESSION"),
    /** Ошибка авторизации групповой политики. */
    FATAL_AUTH_GROUP(8, "FATAL_AUTH_GROUP");
    
    /** Описание ошибки. */
    private final String description;
    /** Код ошибки. */
    private final int code;
    /** Загрузчик языковых ресурсов. */
    private final ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getCanonicalName());
    // End of variables declaration

    /**
     * Инициализация ошибки.
     * @param description описание ошибки
     */
    ErrorsWebZulu(int code, String description) {
        this.code = code;
        this.description = I18N.getString(rb, description);
    }

    /**
     * Возвращает описание ошибки.
     * @return описание ошибки
     */
    public String getDescription() {
        return description;
    }
    
    public int getCode() {
        return code;
    }
}
