/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.exceptions;

/**
 *
 * @author Носов А.В.
 */
public class ExceptionsLibDataBus extends ExceptionLibDataBus {
    
    // Variables declaration
    /** Ошибка при работе с файлом. */
    private final ErrorsLibDataBus error;
    // End of variables declaration

    /**
     * Конструктор исключения.
     * @param error ошибка при работе с настройками
     */
    public ExceptionsLibDataBus(ErrorsLibDataBus error) {
        super(error.getDescription());
        this.error = error;
    }
    
    /**
     * Конструктор исключения.
     * @param error ошибка при работе с настройками
     * @param str дополнительная информация
     */
    public ExceptionsLibDataBus(ErrorsLibDataBus error, String str) {
        super(error.getDescription() + " ( " + str + " )");
        this.error = error;
    }

    /**
     * Возвращает ошибку при работе с файлом.
     * @return ошибка при работе с настройками
     */
    public ErrorsLibDataBus getError() {
        return error;
    }
}
