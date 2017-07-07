/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.server.exceptions;

/**
 * Исключение при работе с файлом.
 * @author Носов А.В.
 */
public class ExceptionProperties extends Exception {
    
    /**
     * Устанавливает сообщение исключения.
     * @param msg сообщение
     */
    public ExceptionProperties (String msg) {
        super(msg);
    }
}
