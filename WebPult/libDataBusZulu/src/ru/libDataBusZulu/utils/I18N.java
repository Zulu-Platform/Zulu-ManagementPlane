/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.libDataBusZulu.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import org.apache.log4j.Logger;

/**
 *
 * @author Носов А.В.
 */
public class I18N {

    // Variables declaration
    private static final Logger log = Logger.getLogger(I18N.class);
    // End of variables declaration

    /**
     * Создание объекта класса JButton.
     * @param b ресурс
     * @param name название
     * @return объект JButton
     */
    public static JButton mkButton(ResourceBundle b, String name) {
        String label;
        try {
            Locale locale = b.getLocale();
            if (locale == null || locale.getLanguage().equals("")) {
                locale = new Locale(System.getProperty("user.language"));
            }
            label = b.getString(name+"."+locale.getLanguage());
        } catch (MissingResourceException ex) {
            label = name;
        }
        return new JButton(label);
    }

    /**
     * Возвращает
     * @param b ресурс
     * @param name название
     * @return
     */
    public static String getString(ResourceBundle b, String name) {
        String result;
        try {
            Locale locale = b.getLocale();
            if (locale == null || locale.getLanguage().equals("")) {
                locale = new Locale(System.getProperty("user.language"));
            }
            result = b.getString(name+"."+locale.getLanguage());
        } catch (MissingResourceException ex) {
            result = name;
        }
        return result;
    }
}
