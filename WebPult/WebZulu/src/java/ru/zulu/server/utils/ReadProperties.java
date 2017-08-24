/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import ru.zulu.server.exceptions.ErrorsWebZulu;
import ru.zulu.server.exceptions.ExceptionsWebZulu;

/**
 * Загрузка/Сохранение настроек.
 * @author Носов А.В.
 */
public class ReadProperties {

    // Variables declaration
    private static final Logger log = Logger.getLogger(ReadProperties.class);
//    private static String res = System.getProperty("user.dir") + File.separator + "WebPult.properties";
    // End of variables declaration

    /**
     * Загрузка файла настроек.
     * @param path путь к файлу
     * @return настройки
     * @throws ExceptionsWebZulu ошибка чтения файла
     */
    public static Properties loadProperties(String path) throws ExceptionsWebZulu {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException ex) {
            throw new ExceptionsWebZulu(ErrorsWebZulu.ERROR_LOAD_FILE);
        }
        return properties;
    }

    /**
     * Сохранение настроект приложения.
     * @param path путь к файлу
     * @param prop настройки
     * @param comment соментарии
     * @throws ExceptionsWebZulu ошибка чтения файла
     */
    public static void saveProperties(
            String path, Properties prop, String comment) 
                    throws ExceptionsWebZulu {
        
        if ( (path == null) || (prop == null)) {
            throw new ExceptionsWebZulu(ErrorsWebZulu.ERROR_SAVE_FILE);
        }
        if (comment == null) comment = "";
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            prop.store(out, comment);
        } catch (IOException ex) {
            throw new ExceptionsWebZulu(ErrorsWebZulu.ERROR_LOAD_FILE);
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException ex) {
                throw new ExceptionsWebZulu(ErrorsWebZulu.ERROR_CLOSE_FILE);
            }
        }
    }

    /**
     * Создание настроек по умолчанию.
     * @param path путь к файлу
     * @param prop настройки
     * @param comment соментарии
     * @throws ExceptionsWebZulu ошибка чтения файла
     */
    public static void createDefaultProperties(
            String path, Properties prop, String comment) 
                    throws ExceptionsWebZulu {
        
        if ( (path == null) || (prop == null)) return;
        if (comment == null) comment = "";
        
        try {
            File f = new File(path);
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            prop.store(out, comment);
        } catch (IOException ex) {
            throw new ExceptionsWebZulu(ErrorsWebZulu.ERROR_CANNOT_SAVE);
        }
    }
}
