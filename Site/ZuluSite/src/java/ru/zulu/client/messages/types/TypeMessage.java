package ru.zulu.client.messages.types;

/**
 * Типы сообщений внутреннего протокола.
 * @author Носов А.В.
 */
public enum TypeMessage {
    
    /** Новая сессия. */
    NewSession,
    
    /** Сообщение ошибки. */
    Error,
    
    /** Сообщение успеха. */
    Success,
    
    /** Список системных сообщений. */
    Systems,
}
