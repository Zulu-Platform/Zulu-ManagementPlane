/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.server.exceptions;

/**
 *
 * @author nosov
 */
public class ExceptionsProperties extends ExceptionProperties {
    
    // Variables declaration
    /** Ошибка при работе с файлом. */
    private final ErrorsProperties error;
    // End of variables declaration

    /**
     * Конструктор исключения.
     * @param error ошибка приработе с настройками
     */
    public ExceptionsProperties(ErrorsProperties error) {
        super(error.getDescription());
        this.error = error;
    }

    /**
     * Возвращает ошибку при работе с файлом.
     * @return ошибка при работе с настройками
     */
    public ErrorsProperties getError() {
        return error;
    }
}
