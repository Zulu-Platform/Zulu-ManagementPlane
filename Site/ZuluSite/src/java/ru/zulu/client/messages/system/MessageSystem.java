/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.messages.system;

import ru.zulu.client.messages.Message;
import ru.zulu.client.messages.types.TypeMessage;

/**
 * Системное сообщение.
 * Тут передаются системные сообщения следующих типов:
 Error - действие завершено с ошибкой
 Success - действие завершено успешно (корректно)
 Поумолчанию тип сообщения Error.
 * @see TypeMessage
 * @author Носов А.В.
 */
public class MessageSystem extends Message {

    // Variables declaration
    /** Код сообщения. */
    private int code;
    /** Заголовок сообщения. */
    private String title;
    /** Описание сообщения. */
    private String description;
    // End of variables declaration

    public MessageSystem() {
        setTypeMessage(TypeMessage.Error);
    }

    /**
     * Возвращает заголовок сообщения.
     * @return заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок сообщения.
     * @param title заголовок
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание сообщения.
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание сообщения.
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает код сообщения.
     * @return код
     */
    public int getCode() {
        return code;
    }

    /**
     * Устанавливает код сообщения.
     * @param code код
     */
    public void setCode(int code) {
        this.code = code;
    }
}