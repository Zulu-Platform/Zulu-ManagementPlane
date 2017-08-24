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
public enum ErrorsProperties {

    // Variables declaration
    /** Нет доступа к диску. */
    ERROR_NODISK(101, "ERROR_NODISK"),
    /** Ошибка загрузки файла. */
    ERROR_LOAD_FILE(103, "ERROR_LOAD_FILE"),
    /** Ошибка записи файла. */
    ERROR_SAVE_FILE(104, "ERROR_SAVE_FILE"),
    /** Ошибка при закрытии файла. */
    ERROR_CLOSE_FILE(105, "ERROR_CLOSE_FILE"),
    /** Не могу сохранить файл. */
    ERROR_CANNOT_SAVE(106, "ERROR_CANNOT_SAVE"),
    /** Создан новый файл. */
    WARNING_NEW_FILE(107, "WARNING_NEW_FILE");
    
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
    ErrorsProperties(int code, String description) {
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
