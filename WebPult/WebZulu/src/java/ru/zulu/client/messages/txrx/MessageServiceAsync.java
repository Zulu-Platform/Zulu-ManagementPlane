/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.txrx;

import libMessage.client.Message;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MessageServiceAsync {
    
    /**
     * Возвращает сообщение от сервера к клиенту.
     * @param msg сообщение от клиента на сервер
     * @param callback обратный вызов
     */
    public void getMessage(Message msg, AsyncCallback<Message> callback);
}
