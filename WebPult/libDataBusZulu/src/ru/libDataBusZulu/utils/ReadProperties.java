/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.libDataBusZulu.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import static java.nio.file.Files.newOutputStream;
import java.nio.file.Path;
import java.util.Properties;
import org.apache.log4j.Logger;
import ru.libDataBusZulu.exceptions.ErrorsLibDataBus;
import ru.libDataBusZulu.exceptions.ExceptionsLibDataBus;
import ru.libDataBusZulu.messages.UserGroup;

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
     * @param filename путь к файлу
     * @return настройки
     * @throws ExceptionsLibDataBus ошибка чтения файла
     */
    public static Properties loadProperties(String filename) throws ExceptionsLibDataBus {
        Properties properties = new Properties();
        try {
            Path path = FileSystems.getDefault().getPath(filename);
            InputStream stream = Files.newInputStream(path);
            properties.load(stream);
        } catch (IOException ex) {
            throw new ExceptionsLibDataBus(ErrorsLibDataBus.ERROR_LOAD_FILE, ex.getMessage());
        }
        return properties;
    }

    /**
     * Сохранение настроект приложения.
     * @param filename путь к файлу
     * @param prop настройки
     * @param comment соментарии
     * @throws ExceptionsLibDataBus ошибка чтения файла
     */
    public static void saveProperties(
            String filename, Properties prop, String comment) 
                    throws ExceptionsLibDataBus {
        
        if ( (filename == null) || (prop == null)) {
            throw new ExceptionsLibDataBus(ErrorsLibDataBus.ERROR_SAVE_FILE);
        }
        if (comment == null) comment = "";
        
        Path path = FileSystems.getDefault().getPath(filename);
        OutputStream out = null;
        try {
            out = newOutputStream(path);
            prop.store(out, comment);
        } catch (IOException ex) {
            throw new ExceptionsLibDataBus(ErrorsLibDataBus.ERROR_LOAD_FILE);
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException ex) {
                throw new ExceptionsLibDataBus(ErrorsLibDataBus.ERROR_CLOSE_FILE);
            }
        }
    }

    /**
     * Возвращает группу поумолчанию.
     * @return группа поумолчанию
     * @throws ExceptionsLibDataBus ошибка чтения настроек
     */
    public static String getDefaultGroup() throws ExceptionsLibDataBus {
        try {
            Properties p = loadProperties(UserGroup.path);
            String group = p.getProperty(UserGroup.names.group_default.name());
            if (group == null)
                throw new ExceptionsLibDataBus(ErrorsLibDataBus.FATAL_DEFAULT_GROUP);
            return group;
        } catch (ExceptionsLibDataBus ex) {
            throw new ExceptionsLibDataBus(ex.getError(), UserGroup.path);
        }
    }
    
    /**
     * Возвращает массив всех групп.
     * @return массив групп
     * @throws ExceptionsLibDataBus ошибка чтения настроек
     */
    public static String[] getGroupList() throws ExceptionsLibDataBus {
        try {
            Properties p = loadProperties(UserGroup.path);
            String groups = p.getProperty(UserGroup.names.groups.name());
            if ( (groups == null) || (groups.length() < 2) || (groups.indexOf(",") < 1) ) 
                throw new ExceptionsLibDataBus(ErrorsLibDataBus.FATAL_DEFAULT_GROUP);
            return groups.split(",");
        } catch (ExceptionsLibDataBus ex) {
            throw new ExceptionsLibDataBus(ex.getError(), UserGroup.path);
        }
    }
    
    /**
     * Создание настроек по умолчанию.
     * @param path путь к файлу
     * @param prop настройки
     * @param comment соментарии
     * @throws ExceptionsLibDataBus ошибка чтения файла
     */
    public static void createDefaultProperties(
            String path, Properties prop, String comment) 
                    throws ExceptionsLibDataBus {
        
        if ( (path == null) || (prop == null)) return;
        if (comment == null) comment = "";
        
        try {
            File f = new File(path);
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            prop.store(out, comment);
        } catch (IOException ex) {
            throw new ExceptionsLibDataBus(ErrorsLibDataBus.ERROR_CANNOT_SAVE);
        }
    }
}
