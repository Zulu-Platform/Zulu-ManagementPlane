package libMessage.client.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Типы дуплекса.
 * @author Носов А.В.
 */
public enum TypeDuplex {
    
    // Variables declaration
    /** Auto. */
    AUTO(0, "Авто"),
    /** Half. */
    HALF(1, "Половина"),
    /** Full. */
    FULL(2, "Целое");
    
    /** Описание дуплекса. */
    private final String description;
    /** Код дуплекса. */
    private final int code;
    // End of variables declaration

    /**
     * Инициализация дуплекса.
     * @param code код дуплекса
     * @param description описание дуплекса
     */
    TypeDuplex(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Возвращает описание дуплекса.
     * @return описание дуплекса
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код дуплекса.
     * @return код дуплекса
     */
    public int getCode() {
        return code;
    }
    
    /**
     * Возвращает список дуплексов.
     * @return список дуплексов
     */
    public List<TypeDuplex> getDuplexList() {
        List<TypeDuplex> list = new ArrayList<TypeDuplex>();
        TypeDuplex[] tss = TypeDuplex.values();
        list.addAll(Arrays.asList(tss));
        return list;
    }
    
    /**
     * Возвращает тип дуплекса по описанию.
     * Поумолчанию = AUTO.
     * @param description описание
     * @return тип дуплекса
     */
    public static TypeDuplex parseString(String description) {
        TypeDuplex[] tss = TypeDuplex.values();
        for (TypeDuplex ts : tss)
            if (ts.getDescription().equals(description))
                return ts;
        
        return TypeDuplex.AUTO;
    }
}
