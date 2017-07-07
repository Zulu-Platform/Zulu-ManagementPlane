/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages;

import libMessage.client.Message;
import libMessage.client.types.TypeMessage;

/**
 * Запросить/Отправить/Принять .
 * @author Носов А.В.
 */
public class MessageFish extends Message {
    
    // Variables declaration
    // End of variables declaration
    
    /**
     * Создание сообщения .
     * @param tm тип сообщения
     */
    public MessageFish(TypeMessage tm) {
        setTypeMessage(tm);
    }
}