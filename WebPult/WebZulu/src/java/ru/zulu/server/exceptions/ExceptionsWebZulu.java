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
public class ExceptionsWebZulu extends ExceptionWebZulu {
    
    // Variables declaration
    /** Ошибка при работе с файлом. */
    private final ErrorsWebZulu error;
    // End of variables declaration

    /**
     * Конструктор исключения.
     * @param error ошибка приработе с настройками
     */
    public ExceptionsWebZulu(ErrorsWebZulu error) {
        super(error.getDescription());
        this.error = error;
    }

    /**
     * Возвращает ошибку при работе с файлом.
     * @return ошибка при работе с настройками
     */
    public ErrorsWebZulu getError() {
        return error;
    }
}
