/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.client.messages;

import java.io.Serializable;
import ru.zulu.client.messages.types.TypeMessage;

/**
 * Абстракция для работы с сообщениями.
 * @author Носов А.В.
 */
public class Message implements Serializable {
    
    // Variables declaration
    private static final long serialVersionUID = 1L;
    /** Тип сообщения. */
    private TypeMessage typeMessage;
    // End of variables declaration
    
    /**
     * Возвращает тип сообщения.
     * @return тип сообщения
     * @see TypeMessage Типы сообщений
     */
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
    
    /**
     * Устанавливает тип сообщения.
     * @param typeMessage тип сообщения
     */
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
