/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.system;

import java.util.ArrayList;
import java.util.List;
import ru.zulu.client.messages.Message;
import ru.zulu.client.messages.types.TypeMessage;

/**
 * Системное сообщение.
 * Тут передаются системные сообщения следующих типов:
 Error - действие завершено с ошибкой
 Success - действие завершено корректно
 Поумолчанию тип сообщения Error.
 * @see TypeMessage
 * @author Носов А.В.
 */
public class MessageSystems extends Message {

    // Variables declaration
    /** Код сообщения. */
    private List<MessageSystem> listMsg;
    // End of variables declaration

    public MessageSystems() {
        setTypeMessage(TypeMessage.Systems);
    }

    /**
     * Возвращает список системных сообщений.
     * @return список сообщений
     */
    public List<MessageSystem> getListMsg() {
        return listMsg;
    }

    /**
     * Устанавливает список системных сообщений.
     * @param listMsg список сообщений
     */
    public void setListMsg(List<MessageSystem> listMsg) {
        this.listMsg = listMsg;
    }
    
    /**
     * Добавляет в список системное сообщение.
     * @param messageSystem системное сообщение
     */
    public void addMsg(MessageSystem messageSystem) {
        if (listMsg == null)
            listMsg = new ArrayList<MessageSystem>();
        listMsg.add(messageSystem);
    }
}