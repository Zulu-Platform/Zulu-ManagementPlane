/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.exceptions;

import java.util.ResourceBundle;
import ru.zulu.server.utils.I18N;

/**
 * Перечень и расшифровка возможных ошибок.
 * @author Носов А.В.
 */
public enum ErrorsMessage {

    // Variables declaration
    /** Ошибка парсера JSON. */
    ERROR_JSON(0, "ERROR_JSON"),
    /** Не обрабатываемый тип сообщения. */
    WARNING_UNPROCESSED_TYPE(1, "WARNING_UNPROCESSED_TYPE"),
    /** Неизвестный тип сообщения. */
    WARNING_UNKNOWN_TYPE(2, "WARNING_UNKNOWN_TYPE"),
    /** Новая сессия. */
    WARNING_NEW_SESSION(3, "WARNING_NEW_SESSION"),
    /** Несовпадение идентификаторов. */
    ERROR_MODULE_ID(4, "ERROR_MODULE_ID"),
    /** Неверная команда запроса. */
    ERROR_DATA_REQUEST(5, "ERROR_DATA_REQUEST"),
    /** Неверная команда ответа. */
    ERROR_DATA_RESPONSE(6, "ERROR_DATA_RESPONSE"),
    /** Неверный тип запроса. */
    ERROR_TYPE_REQUEST(5, "ERROR_TYPE_REQUEST"),
    /** Неверный тип ответа. */
    ERROR_TYPE_RESPONSE(6, "ERROR_TYPE_RESPONSE"),
    /** Ошибка регистрации пользователя. */
    ERROR_REGISTRATION(7, "ERROR_REGISTRATION"),
    /** Нет ответа от сервера. */
    ERROR_NO_RESPONSE(8, "ERROR_NO_RESPONSE");
    
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
    ErrorsMessage(int code, String description) {
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
    
    /**
     * Возвращает код ошибки.
     * @return код ошибки
     */
    public int getCode() {
        return code;
    }
}
