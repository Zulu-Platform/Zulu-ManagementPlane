/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.txrx;

import libMessage.client.Message;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("messageServiceImpl")
public interface MessageService extends RemoteService {
    
    /**
     * Возвращает сообщение от сервера к клиенту
     * @param msg сообщение от клиента к серверу
     * @return сообщение от сервера к клиенту
     */
    public Message getMessage(Message msg);
}
