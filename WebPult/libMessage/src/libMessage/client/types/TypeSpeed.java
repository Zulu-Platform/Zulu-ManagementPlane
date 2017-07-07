package libMessage.client.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Типы скоростей.
 * @author Носов А.В.
 */
public enum TypeSpeed {
    
    // Variables declaration
    /** Auto. */
    AUTO(0, "Авто"),
    /** 10 Мб. */
    S10MB(1, "10 Мб"),
    /** 100 Мб. */
    S100BM(2, "100 Мб"),
    /** 1 Гб. */
    S1GB(3, "1 Гб"),
    /** 10 Гб. */
    S10GB(4, "10 Гб"),
    /** 100 Гб. */
    S100GB(5, "100 Гб");

    /** Описание скорости. */
    private final String description;
    /** Код скорости. */
    private final int code;
    // End of variables declaration

    /**
     * Инициализация скорости.
     * @param code код скорости
     * @param description описание скорости
     */
    TypeSpeed(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    @Override
    public String toString() {
      return description;
    }
    
    /**
     * Возвращает описание скорости.
     * @return описание скорости
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код скорости.
     * @return код скорости
     */
    public int getCode() {
        return code;
    }
    
    /**
     * Возвращает список скоростей.
     * @return список скоростей
     */
    public List<TypeSpeed> getSpeedList() {
        List<TypeSpeed> list = new ArrayList<TypeSpeed>();
        TypeSpeed[] tss = TypeSpeed.values();
        list.addAll(Arrays.asList(tss));
        return list;
    }
    
    /**
     * Возвращает тип скорости по описанию.
     * Поумолчанию = AUTO.
     * @param description описание
     * @return тип скорости
     */
    public static TypeSpeed parseString(String description) {
        TypeSpeed[] tss = TypeSpeed.values();
        for (TypeSpeed ts : tss)
            if (ts.getDescription().equals(description))
                return ts;
        
        return TypeSpeed.AUTO;
    }
    
}
