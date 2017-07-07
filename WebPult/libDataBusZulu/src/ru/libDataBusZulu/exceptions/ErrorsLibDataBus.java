/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.libDataBusZulu.exceptions;

import java.util.ResourceBundle;
import ru.libDataBusZulu.utils.I18N;

/**
 * Перечень и расшифровка возможных ошибок сокета.
 * @author Носов А.В.
 */
public enum ErrorsLibDataBus {

    // Variables declaration,
    /** Создан новый файл. */
    WARNING_NEW_FILE (1, "WARNING_NEW_FILE"),
    
    /** Нет доступа к диску. */
    ERROR_NODISK (101, "ERROR_NODISK"),
    /** Ошибка загрузки файла. */
    ERROR_LOAD_FILE (102, "ERROR_LOAD_FILE"),
    /** Ошибка записи файла. */
    ERROR_SAVE_FILE (103, "ERROR_SAVE_FILE"),
    /** Ошибка при закрытии файла. */
    ERROR_CLOSE_FILE (104, "ERROR_CLOSE_FILE"),
    /** Не могу сохранить файл. */
    ERROR_CANNOT_SAVE (105, "ERROR_CANNOT_SAVE"),
    /** Не верное имя пользователя. */
    ERROR_USERNAME (107, "ERROR_USERNAME"),
    /** Не верное имя пользователя или пароль. */
    ERROR_USERNAMEPASS (108, "ERROR_USERNAMEPASS"),
    /** Удалить администратора нельзя. */
    ERROR_ADMIN_REMOVE (109, "ERROR_ADMIN_REMOVE"),
    /** Не могу изменить системные настройки. */
    ERROR_SYSTEMSETTINGS (110, "ERROR_SYSTEMSETTINGS"),
    
    /** Отсутствует группа поумолчанию. */
    FATAL_DEFAULT_GROUP (1001, "FATAL_DEFAULT_GROUP"),
    /** Отсутствует список групп. */
    FATAL_GROUPS (1001, "FATAL_GROUPS");
    
    
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
    ErrorsLibDataBus(int code, String description) {
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
