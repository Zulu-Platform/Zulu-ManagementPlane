/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages;

/**
 * Интерфейс для отправки/приема сообщений серверу.
 * @author Носов А.В.
 */
public interface ITxRxDAO {
    
    /**
     * Отправить собщение серверу.
     * @param msg сообщение
     */
    public void sendMessage(Message msg);
    
    /**
     * Отправить собщение серверу.
     * @param msg сообщение
     * @param dialogProgress сообщение окна диалога
     */
    public void sendMessage(Message msg, String dialogProgress);
    
    /**
     * Отправить собщение серверу.
     * @param msg сообщение
     * @param dialogHeading заголовок окна диалога
     * @param dialogProgress сообщение окна диалога
     */
    public void sendMessage(Message msg, String dialogHeading, String dialogProgress);
    
}
