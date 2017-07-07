/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.Message;

/**
 * Событие отправки сообщения серверу.
 * Не используется.
 * @author Носов А.В.
 */
public class TxMessageEvent extends GwtEvent<TxMessageEventHandler> {

    // Variables declaration
    public static Type<TxMessageEventHandler> TYPE = new Type<TxMessageEventHandler>();
    /* Сообщение. */
    private Message msg;
    /* Заголовок окна диалога. */
    private String dialogHeading = "Ожидайте...";
    /* Сообщение окна диалога. */
    private String dialogMessage = "";
    // End of variables declaration
    
    /**
     * Отправить собщение серверу.
     * @param msg сообщение
     */
    public TxMessageEvent(Message msg) {
        this(msg, null, null);
    }
    
    /**
     * Отправить собщение серверу.
     * @param msg сообщение
     * @param dialogMessage сообщение окна диалога
     */
    public TxMessageEvent(Message msg, String dialogMessage) {
        this(msg, null, dialogMessage);
    }

    /**
     * Отправить собщение серверу.
     * @param msg сообщение
     * @param dialogHeading заголовок окна диалога
     * @param dialogMessage сообщение окна диалога
     */
    public TxMessageEvent(Message msg, String dialogHeading, String dialogMessage) {
        this.msg = msg;
        this.dialogHeading = dialogHeading;
        this.dialogMessage = dialogMessage;
    }
    
    @Override
    public Type<TxMessageEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TxMessageEventHandler handler) {
        handler.TxMessage(this);
    }

    /**
     * Возвращает сообщение в формате JSON.
     * @return сообщение
     */
    public Message getMsg() {
        return msg;
    }

    /**
     * Возвращает заголовок диалогового окна.
     * По умолчанию - "Ожидайте...";
     * @return заголовок
     */
    public String getDialogHeading() {
        return dialogHeading;
    }

    /**
     * Возвращает информационное сообщение диалогового окна.
     * По умолчанию - "";
     * @return информационное сообщение
     */
    public String getDialogMessage() {
        return dialogMessage;
    }
}
