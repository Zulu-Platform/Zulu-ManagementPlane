package libMessage.info;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Информация о библиотеки.
 * @author Носов А.В.
 */
public class Info {

    // Variables declaration
    /** Информация о версии библиотеки. */
    private final ResourceBundle rb = ResourceBundle.getBundle("LibMessage");
    // End of variables declaration
    
    /** Получение информации о библиотеки.  */
    private String getRbTok(String propToken) {
        String msg = "";
        try {
            msg = rb.getString(propToken);
        } catch (MissingResourceException e) {
            System.err.println("Описание ".concat(propToken).concat(" не найдено!"));
        }
        return msg;
    }

    /**
     * Получение информации о версии библиотеки.
     */
    public Info() {
    }

    /**
     * Возвращает номер релиза.
     * @return номер релиза
     */
    public String getRelease() {
        return getRbTok("LibMessageGidra.version") + getRbTok("LibMessageGidra.build");
    }

    /**
     * Возвращает дату создания релиза.
     * @return дата создания
     */
    public String getDate() {
        return getRbTok("LibMessageGidra.date");
    }

    /**
     * Возвращает время создания релиза.
     * @return время создания
     */
    public String getTime() {
        return getRbTok("LibMessageGidra.time");
    }

    /**
     * Возвращает полную информацию о библиотеки.
     * @return информация о библиотеки
     */
    public String getVersionInfo() {
        return getRbTok("LibMessageGidra.version") + getRbTok("LibMessageGidra.build") + " DATE=" + 
                getRbTok("LibMessageGidra.date") + " TIME=" + getRbTok("LibMessageGidra.time");
    }

    /**
     * Возвращает набор информации о библиотеки.
     * @param rel возвращает релиз
     * @param date возвращает дату
     * @param time возвращает время
     * @return информация о библиотеки
     */
    public String getVersionInfo(boolean rel, boolean date, boolean time) {
        String info = "";
        
        if (rel)
            info.concat(getRbTok("LibMessageGidra.version") + getRbTok("LibMessageGidra.build"));
        
        if (date)
            info.concat(" DATE=" + getRbTok("LibMessageGidra.date"));
        
        if (time)
            info.concat(" TIME=" + getRbTok("LibMessageGidra.time"));
        
        return info;
    }
}
