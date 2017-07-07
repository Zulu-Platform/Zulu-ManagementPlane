/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package libMessage.client;

import java.io.Serializable;
import libMessage.client.types.TypeMessage;

/**
 * Абстракция для работы с сообщениями.
 * @author Носов А.В.
 */
public class Message implements Serializable {
    
    // Variables declaration
    private static final long serialVersionUID = 1L;
    /** Тип сообщения. */
    private TypeMessage typeMessage;
    /** IP  адрес. */
//    private String ip;
    // End of variables declaration
    
    /**
     * Возвращает тип сообщения.
     * @return тип сообщения
     * @see TypeMessage Типы сообщений
     */
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
    
    /**
     * Устанавливает тип сообщения.
     * @param typeMessage тип сообщения
     */
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
}
