package ru.zulu.client.gui.types;

/**
 * Типы кнопок.
 * @author Носов А.В.
 */
public enum TypeButtons {
    
    // Variables declaration
    /** Да. */
    YES(0, "Да"),
    /** Нет. */
    NO(1, "Нет"),
    /** Применить. */
    APPLY(2, "Применить"),
    /** Отменить. */
    CANSEL(3, "Отменить"),
    /** Удалить. */
    ADD(4, "Добавить"),
    /** Удалить. */
    EDIT(5, "Редактировать"),
    /** Удалить. */
    DELETE(6, "Удалить"),
    /** Сохранить. */
    SAVE(7, "Сохранить");
    
    /** Описание кнопки. */
    private final String description;
    /** Код кнопки. */
    private final int code;
    // End of variables declaration

    /**
     * Инициализация кнопки.
     * @param description описание кнопки
     */
    TypeButtons(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Возвращает описание кнопки.
     * @return описание кнопки
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код кнопки.
     * @return код кнопки
     */
    public int getCode() {
        return code;
    }
}
