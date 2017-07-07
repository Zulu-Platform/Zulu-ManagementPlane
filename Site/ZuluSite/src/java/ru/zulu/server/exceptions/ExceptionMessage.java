/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.exceptions;

/**
 * Сообщение исключения.
 * @author Носов А.В.
 */
public class ExceptionMessage extends Exception {

    /**
     * Устанавливает сообщение исключения.
     * @param msg сообщение
     */
    public ExceptionMessage (String msg) {
        super(msg);
    }
}