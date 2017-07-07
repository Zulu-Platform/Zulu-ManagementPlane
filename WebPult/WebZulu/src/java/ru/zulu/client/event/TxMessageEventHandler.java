/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Обработчик события отправки сообщения серверу.
 * Не используется.
 * @author Носов А.В.
 */
public interface TxMessageEventHandler extends EventHandler {
    
    public void TxMessage(TxMessageEvent event);
}
