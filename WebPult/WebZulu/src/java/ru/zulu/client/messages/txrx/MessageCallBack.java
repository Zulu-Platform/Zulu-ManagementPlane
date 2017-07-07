/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.txrx;

import com.allen_sauer.gwt.log.client.Log;
import libMessage.client.Message;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import libMessage.client.messages.MessageGroups;
import libMessage.client.messages.MessageUsers;
import libMessage.client.messages.SystemsSettings;
import libMessage.client.systems.MessageSystem;
import libMessage.client.systems.MessageSystems;
import libMessage.client.types.TypeMessage;
import ru.zulu.client.event.ErrorEvent;
import ru.zulu.client.event.LoginEvent;
import ru.zulu.client.event.SuccessEvent;
import ru.zulu.client.gui.ClientFactory;
import libMessage.client.messages.User;
import ru.zulu.client.event.GroupListEvent;
import ru.zulu.client.event.SystemsSettingsEvent;
import ru.zulu.client.event.UserInfoEvent;
import ru.zulu.client.event.UserListEvent;

/**
 * Асинхронная передача сообщения.
 * Нигде не используется.
 * @author Носов А.В.
 */
public class MessageCallBack implements AsyncCallback<Message> {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    /** Сообщение ожидания. */
    private final AutoProgressMessageBox messageBox;
    // End of variables declaration
    
    public MessageCallBack(AutoProgressMessageBox messageBox) {
        this.messageBox = messageBox;
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
        if (messageBox != null) messageBox.hide();
        if ( (msg == null) || (msg.getTypeMessage() == null) ) return;
        TypeMessage tm = msg.getTypeMessage();
//        Log.debug(CLASS_NAME, "TM:"+tm.name());
        EventBus bus = ClientFactory.getInstance().getEventBus();
        switch (tm) {
            case Error:
                bus.fireEvent(new ErrorEvent((MessageSystem) msg));
                break;
            case Success:
                bus.fireEvent(new SuccessEvent((MessageSystem) msg));
                break;
            case Systems:
                MessageSystemsParser( (MessageSystems) msg);
                break;
            case LoginInfo:
                bus.fireEvent(new LoginEvent((User) msg));
                break;
            case RxSystemsSettings:
                bus.fireEvent(new SystemsSettingsEvent((SystemsSettings) msg));
                break;
            case RxUsers:
                MessageUsers users = (MessageUsers) msg;
                bus.fireEvent(new UserListEvent(users.getUsers()));
                break;
            case RxUserByName:
                bus.fireEvent(new UserInfoEvent((User) msg));
                break;
            case RxGroups:
                MessageGroups groups = (MessageGroups) msg;
                bus.fireEvent(new GroupListEvent(groups.getGroups()));
                break;
            default:
                Log.debug(CLASS_NAME, msg.toString());
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
