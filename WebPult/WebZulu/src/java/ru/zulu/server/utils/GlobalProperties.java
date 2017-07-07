/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.utils;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import ru.zulu.server.exceptions.ExceptionsWebZulu;
import ru.zulu.server.types.TypeMode;

/**
 * Чтение настроек.
 * @author Носов А.В.
 */
public class GlobalProperties {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(GlobalProperties.class);
    private static String path = System.getProperty("user.dir") + File.separator + 
                                "Global.properties";
    /** Настройки. */
    private static Properties prop;
    // End of variables declaration
    
    /**
     * Возвращает тип режима работы web сервера.
     * @return режим работы web сервера
     */
    public static TypeMode getTypeMode() {
        try {
            if (prop == null)
                prop = ReadProperties.loadProperties(path);
            TypeMode tm = TypeMode.INTERNAL;
        
            String mode = prop.getProperty("mode", tm.name());
            return TypeMode.valueOf(mode);
        } catch (IllegalArgumentException ex) {
            return TypeMode.INTERNAL;
        } catch (NullPointerException ex) {
            return TypeMode.INTERNAL;
        } catch (ExceptionsWebZulu ex) {
            log.error(ex.getMessage());
            try {
                prop = createDefaultGlobalProperties();
                ReadProperties.createDefaultProperties(path, prop, getComments());
                return TypeMode.INTERNAL;
            } catch (ExceptionsWebZulu ep) {
                log.error(ep.getMessage());
                return TypeMode.INTERNAL;
            }
        }
    }

    /**
     * Создание настроек по умолчанию.
     * @param f файл
     * @return файл настроек
     */
    private static Properties createDefaultGlobalProperties() {
        Properties properties = new Properties();
        properties.put("mode", TypeMode.INTERNAL.name());
        return properties;
    }

    /**
     * Возвращает коментарии для файла настроек.
     * @return коментарии
     */
    private static String getComments() {
        String str;
        str = " Create Global properties.\n"
                + "   mode=" + TypeMode.INTERNAL.name() + ","
                             + TypeMode.TEST.name() + "\n"
                ;
        return str;
    }
}
