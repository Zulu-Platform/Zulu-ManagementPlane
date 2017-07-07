/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.txrx;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import libMessage.client.Message;

/**
 * Реализация методов для взаимодейсвия с сервером.
 * @author Носов А.В.
 */
public class TxRxDAOImpl implements TxRxDAO {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private static final long serialVersionUID = 5L;
    public static final String CONTEXTPATH = "/WebZulu";
    
    /** Сервис сообщений. */
    private final MessageServiceAsync msgService;
    /** Сообщение ожидания. */
    private final AutoProgressMessageBox messageBox;
    private final String headingTxt = "Ожидайте...";
    private final String progressTxt = "Получение данных.";
    // End of variables declaration
    
    public TxRxDAOImpl() {
        msgService = GWT.create(MessageService.class);
        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
        String urlMsg = CONTEXTPATH + "/ru_zulu_server_services/messageServiceImpl";
        endpointMsg.setServiceEntryPoint(urlMsg);
        messageBox = new AutoProgressMessageBox(headingTxt, progressTxt);
    }
    
    @Override
    public void sendMessage(Message msg) {
        sendMessage(msg, null, null);
    }
    
    @Override
    public void sendMessage(Message msg, String dialogProgress) {
        sendMessage(msg, null, dialogProgress);
    }
    
    @Override
    public void sendMessage(Message msg, String dialogHeading, String dialogProgress) {
        messageBox.setHeadingText((dialogHeading == null) ? headingTxt : dialogHeading);
        messageBox.setProgressText((dialogProgress == null) ? progressTxt : dialogProgress);
        messageBox.auto();
        messageBox.show();
        msgService.getMessage(msg, new MessageCallBack(messageBox));
    }
}
