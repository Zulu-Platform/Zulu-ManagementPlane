/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ru.zulu.client.messages.Message;
import ru.zulu.client.messages.MessageService;
import ru.zulu.client.messages.system.MessageSystem;
import ru.zulu.client.messages.types.TypeMessage;
import ru.zulu.server.exceptions.ErrorsMessage;
import ru.zulu.server.utils.I18N;

public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MessageServiceImpl.class);
    
    /** Загрузчик языковых ресурсов. */
    private final ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getCanonicalName());
    // End of variables declaration
    
    @Override
    public Message getMessage(Message msg) {
        TypeMessage tm;
        if ( (msg == null) || (msg.getTypeMessage() == null) ) tm = TypeMessage.Error;
        else tm = msg.getTypeMessage();
        
        if (log.isDebugEnabled())
            log.debug(I18N.getString(rb, "DEBUG_TYPE_INPUT_MESSAGE") + tm.name());
        
        HttpSession session = this.getThreadLocalRequest().getSession();
        if (session.isNew()) {
//            int interval = 60; //1min
//            session.setMaxInactiveInterval(interval);
            tm = TypeMessage.NewSession;
        }
        
        Message msgRx = (Message) getMsgErrorType();
        switch (tm) {
            case NewSession:
                msgRx = (Message) getMsgError(ErrorsMessage.WARNING_NEW_SESSION.getCode(), 
                        null, ErrorsMessage.WARNING_NEW_SESSION.getDescription());
                break;
            case Error:
//                msgRx = msg;
                break;
            default:
                msgRx = (Message) getMsgError(
                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getCode(), 
                        ErrorsMessage.ERROR_DATA_REQUEST.getDescription(), 
                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription());
        }
        
        return msgRx;
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @return сообщение ошибки
     */
    private MessageSystem getMsgErrorType() {
        return getMsgError(ErrorsMessage.WARNING_UNKNOWN_TYPE.getCode(), null,
                           ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription());
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    private MessageSystem getMsgError() {
        return getMsgError(-1);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    private MessageSystem getMsgError(int code) {
        return getMsgError(code, null);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    private MessageSystem getMsgError(int code, String title) {
        return getMsgError(code, title, null);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    private MessageSystem getMsgError(String title, String description) {
        return getMsgError(-1, title, description);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    private MessageSystem getMsgError(int code, String title, String description) {
        MessageSystem msgError = new MessageSystem();
        msgError.setCode(code);
        msgError.setTitle(title);
        msgError.setDescription(description);
        return msgError;
    }
    
    /**
     * Возвращает сообщение успеха.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение успеха
     */
    private MessageSystem getMsgSuccess(int code, String title) {
        return getMsgSuccess(code, title, null);
    }
    
    /**
     * Возвращает сообщение успеха.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение успеха
     */
    private MessageSystem getMsgSuccess(String title, String description) {
        return getMsgSuccess(-1, title, description);
    }
    
    /**
     * Возвращает сообщение об успеха.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение успеха
     */
    private MessageSystem getMsgSuccess(int code, String title, String description) {
        MessageSystem msgSuccess = new MessageSystem();
        msgSuccess.setTypeMessage(TypeMessage.Success);
        msgSuccess.setCode(code);
        msgSuccess.setTitle(title);
        msgSuccess.setDescription(description);
        return msgSuccess;
    }
}