/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.types;

/**
 * Тип используемых даных (режим работы сервера).
 * @author Носов А.В.
 */
public enum TypeMode {

    // Variables declaration
    /** Генерировать свои тестовые сообщения. */
    TEST(0, "Генерировать свои тестовые сообщения."),
    /** Отправлять сообщения внутреннему серверу. */
    INTERNAL(1, "Отправлять сообщения внутреннему серверу.");
    
    /** Описание. */
    private final String description;
    /** Код. */
    private final int code;
    // End of variables declaration

    /**
     * Инициализация типа.
     * @param code код
     * @param description описание
     */
    TypeMode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Возвращает описание.
     * @return описание
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код.
     * @return код
     */
    public int getCode() {
        return code;
    }
}
