/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import libMessage.client.Message;
import libMessage.client.types.TypeMessage;
import ru.zulu.client.gui.ClientFactory;

/**
 * Инициализация activity.
 * @author Носов А.В.
 */
abstract class MessageAbstractActivity extends AbstractActivity {
    
// Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    // End of variables declaration
    
    public MessageAbstractActivity() {
    }
    
    /**
     * Передать сообщение серверу.
     * @param typeMessage тип сообщения
     */
    protected void rqMessage(Message message) {
        ClientFactory.getInstance().getTxRx().sendMessage(message);
    }
    
    /**
     * Передать сообщение серверу.
     * @param typeMessage тип сообщения
     */
    protected void rqMessage(TypeMessage typeMessage) {
        Message msg = new Message();
        msg.setTypeMessage(typeMessage);
        ClientFactory.getInstance().getTxRx().sendMessage(msg);
    }
    
//    public void creatLogMessage(TypeLog typeLog, String description) {
//    }
//
//    public void creatLogMessage(TypeLog typeLog, String title, String description) {
//    }  
}
