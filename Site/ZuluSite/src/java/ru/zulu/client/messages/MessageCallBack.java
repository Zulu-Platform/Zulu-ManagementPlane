/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Асинхронная передача сообщения.
 * Нигде не используется.
 * @author Носов А.В.
 */
public class MessageCallBack implements AsyncCallback<Message> {
      
    @Override
    public void onFailure(Throwable caught) {
        Window.alert("MessageCallBack no response");	
    }
    @Override
    public void onSuccess(Message result) {
        Window.alert("MessageCallBack\n" + result);
    }	   
}
