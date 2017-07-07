/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.utils;

import com.google.gson.Gson;
import libMessage.client.systems.MessageSystem;
import libMessage.client.types.TypeMessage;
import libMessage.exceptions.ErrorsMessage;

/**
 * Системные сообщения.
 * @author Носов А.В.
 */
public class SystemMsg {
    
    /**
     * Возвращает сообщение об ошибки.
     * @return сообщение ошибки
     */
    public static MessageSystem getMsgError() {
        return getMsgError(-1);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @return сообщение ошибки
     */
    public static MessageSystem getMsgError(int code) {
        return getMsgError(code, null);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @param title заголовок
     * @return сообщение ошибки
     */
    public static MessageSystem getMsgError(int code, String title) {
        return getMsgError(code, title, null);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    public static MessageSystem getMsgError(String title, String description) {
        return getMsgError(-1, title, description);
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение ошибки
     */
    public static MessageSystem getMsgError(int code, String title, String description) {
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
     * @return сообщение успеха
     */
    public static MessageSystem getMsgSuccess(int code, String title) {
        return getMsgSuccess(code, title, null);
    }
    
    /**
     * Возвращает сообщение успеха.
     * @param title заголовок
     * @param description описание
     * @return сообщение успеха
     */
    public static MessageSystem getMsgSuccess(String title, String description) {
        return getMsgSuccess(-1, title, description);
    }
    
    /**
     * Возвращает сообщение об успеха.
     * @param code код
     * @param title заголовок
     * @param description описание
     * @return сообщение успеха
     */
    public static MessageSystem getMsgSuccess(int code, String title, String description) {
        MessageSystem msgSuccess = new MessageSystem();
        msgSuccess.setTypeMessage(TypeMessage.Success);
        msgSuccess.setCode(code);
        msgSuccess.setTitle(title);
        msgSuccess.setDescription(description);
        return msgSuccess;
    }
    
    /**
     * Возвращает сообщение с null параметром.
     * @return системное сообщение
     */
    public static String getMsgNull() {
        MessageSystem msg = SystemMsg.getMsgError(0, "NULL message", "Error IO.");
        return new Gson().toJson(msg, msg.getClass());
    }
    
    /**
     * Возвращает сообщение об ошибки.
     * @return сообщение ошибки
     */
    public static MessageSystem getMsgErrorType() {
        return SystemMsg.getMsgError(
                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getCode(), 
                        ErrorsMessage.ERROR_DATA_REQUEST.getDescription(), 
                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription());
    }
    
    /**
     * Возвращает информационное сообщение.
     * @param title заголовок
     * @param description описание
     * @return информационное сообщение
     */
    public static MessageSystem getMsgInfo(String title, String description) {
        MessageSystem ms = new MessageSystem();
        ms.setTypeMessage(TypeMessage.Info);
        ms.setTitle(title);
        ms.setDescription(description);
        return ms;
    }
}
