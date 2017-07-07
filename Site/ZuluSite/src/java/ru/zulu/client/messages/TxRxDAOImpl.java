/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.web.bindery.event.shared.EventBus;
import ru.zulu.client.event.*;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.messages.system.MessageSystem;
import ru.zulu.client.messages.system.MessageSystems;
import ru.zulu.client.messages.types.TypeMessage;

/**
 * Реализация методов для взаимодейсвия с сервером.
 * @author Носов А.В.
 */
public class TxRxDAOImpl implements ITxRxDAO, AsyncCallback<Message> {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private static final long serialVersionUID = 5L;
    
    /** Сервис сообщений. */
    private final MessageServiceAsync msgService;
    /** Сообщение ожидания. */
//    private final AutoProgressMessageBox messageBox;
    private final String headingTxt = "Ожидайте...";
    private final String progressTxt = "Получение данных.";
    // End of variables declaration
    
    public TxRxDAOImpl() {
        msgService = GWT.create(MessageService.class);
        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
        String urlMsg = "/WebZulu/ru_zulu_server_services/messageServiceImpl";
        endpointMsg.setServiceEntryPoint(urlMsg);
        
//        messageBox = new AutoProgressMessageBox(headingTxt, progressTxt);
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
//        if (dialogMessage != null) {
//            if (dialogHeading != null)
//                messageBox.setHeadingText(dialogHeading);
//            messageBox.setProgressText(dialogMessage);
//            messageBox.auto();
//            messageBox.show();
//        }
//        messageBox.setHeadingText((dialogHeading == null) ? headingTxt : dialogHeading);
//        messageBox.setProgressText((dialogProgress == null) ? progressTxt : dialogProgress);
//        messageBox.auto();
//        messageBox.show();
        msgService.getMessage(msg, this);
    }
    
    @Override
    public void onFailure(Throwable caught) {
        MessageSystem msgError = new MessageSystem();
        msgError.setCode(8); // ERROR_NO_RESPONSE
        msgError.setTitle("No response.");
        msgError.setDescription("No response from server.");
        onSuccess(msgError);
    }

    @Override
    public void onSuccess(Message msg) {
//        messageBox.hide();
        if ( (msg == null) || (msg.getTypeMessage() == null) ) return;
        TypeMessage tm = msg.getTypeMessage();
        EventBus bus = ClientFactory.getInstance().getEventBus();
        switch (tm) {
//            case RxGroupById:
//                Group group = (Group) msg;
//                bus.fireEvent(new GroupByIdEvent(group));
//                break;
            default:
//                Log.debug(CLASS_NAME, msg.toString());
        }
    }
    
    /**
     * Парсер системного сообщения.
     * @param systems системное сообщение
     */
    private void MessageSystemsParser(MessageSystems systems) {
        if (systems.getListMsg() == null) return;
        EventBus bus = ClientFactory.getInstance().getEventBus();
        
        for (MessageSystem system : systems.getListMsg()) {
            if (system.getTypeMessage() == null) continue;
            switch (system.getTypeMessage()) {
                case Error:
                    bus.fireEvent(new ErrorEvent((MessageSystem) system));
                    break;
                case Success:
                    bus.fireEvent(new SuccessEvent((MessageSystem) system));
                    break;
                default:
            }
        }
    }
}
