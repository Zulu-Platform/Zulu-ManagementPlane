/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.client.utils;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import ru.zulu.client.gui.places.EthernetListPlace;
import ru.zulu.client.gui.places.PageDevPlace;
import ru.zulu.client.gui.places.SystemsPlace;
import ru.zulu.client.gui.places.SystemsSettingsPlace;
import ru.zulu.client.gui.places.ToolsPlace;
import ru.zulu.client.gui.places.UsersGroupsPlace;
import ru.zulu.client.gui.places.UsersListPlace;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Класс полезных утилиток.
 * @author Носов А.В.
 */
public class Utils {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    // End of variables declaration

    /**
     * Показывает информационное окно.
     * @param title заголовок
     * @param text сообщение
     */
    public static void infoMessage(String title, String text) {
        DefaultInfoConfig config = new DefaultInfoConfig(title, text);
        config.setDisplay(5000);
        Info.display(config);
    }
    
    /**
     * Создание поля FieldLabel из GXT.
     * @param widget виджет
     * @param label название
     * @param width длинна названия
     * @return FieldLabel
     */
    public static FieldLabel createField(Widget widget, String label, 
                                       int width) {
        return createField(widget, label, width, null);
    }
    
    /**
     * Создание поля FieldLabel из GXT.
     * @param widget виджет
     * @param label название
     * @param width длинна названия
     * @param separator сепаратор
     * @return FieldLabel
     */
    public static FieldLabel createField(Widget widget, String label, 
                                       int width, String separator) {
        FieldLabel fl = new FieldLabel();
        fl.setWidget(widget);
        fl.setText(label);
        if (width > 1) fl.setLabelWidth(width);
        if (separator != null) fl.setLabelSeparator(separator);
        fl.setLabelAlign(LabelAlign.LEFT);
        fl.setLabelWordWrap(false);
        return fl;
    }
    
    /**
     * Возвращает октет.
     * @param srt строка
     * @return октет
     */
    public static short getOctet(String srt) {
        try {
            return Short.valueOf(srt);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    /**
     * Проверка корректности ввода даты рождения.
     * @param birthday дата рождения
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateBirthday(String birthday) {
        return true;
    }
    
    /**
     * Проверка корректности ввода логина.
     * @param name логин
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateName(String name) {
        return !((name == null) || (name.trim().length() < 1));
    }
    
    /**
     * Проверка корректности ввода логина.
     * @param login логин
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateLogin(String login) {
        return ((login == null) || (login.trim().length() == 0) || (login.trim().length() >= 3));
    }
    
    /**
     * Проверка корректности ввода пароля.
     * @param pass пароль
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validatePassword(String pass) {
        return !((pass == null) || (pass.trim().length() < 3));
    }
    
    /**
     * Проверка корректности ввода электронной почты.
     * @param email электронная почта
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateEmail(String email) {
        return !((email == null) || (email.indexOf("@") < 1));
    }
    
    /**
     * Проверка ip адреса.
     * @param ip ip адрес
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateIP(String ip) {
        try {
            if (ip == null) return false;
            String[] ipo = ip.split("\\.");
            if (ipo.length != 4) return false;
            for (String s : ipo) {
                int i = Integer.valueOf(s);
                if ( (i < 1) || (i > 255) ) return false;
            }
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    /**
     * Проверка MAC адреса.
     * @param mac MAC адрес
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateMAC(String mac) {
        try {
            if (mac == null) return false;
            String[] ipo = mac.split("\\:");
            if (ipo.length != 6) return false;
//            for (String s : ipo) {
//                int i = Integer.valueOf(s);
//                if ( (i < 1) || (i > 255) ) return false;
//            }
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    /**
     * Варианты расположения рисунка.
     */
    public static enum ImageLayout {
        TOP,
        BUTTON,
        LEFT,
        RIGHT
    }
    
    /**
     * Возвращает HTML разметку с использованием рисунков Awesome.
     * @param il расположение рисунка
     * @param imageName имя рикунка
     * @param text текст
     * @return HTML разметка
     */
    public static SafeHtml getSafeHtmlAwesome(ImageLayout il, String imageName, String text) {
        return getSafeHtmlAwesome(il, imageName, null, text);
    }
    
    /**
     * Возвращает HTML разметку с использованием рисунков Awesome и стиля 
     * для Awesome.
     * @param il расположение рисунка
     * @param imageName имя рикунка
     * @param styleImg стиль
     * @param text текст
     * @return HTML разметка
     */
    public static SafeHtml getSafeHtmlAwesome(ImageLayout il, String imageName, 
            String styleImg, String text    ) {
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        if (styleImg == null) styleImg = "";
        String img;

        String txt = null;
        if (text != null)
            txt = "<span style=\"display: inline-block;" // width: 80%;"
                + " text-decoration: underline; font-size: 2em;\">" + text + "</span>";

        switch (il) {
            case BUTTON:
                styleImg = "margin-top:5px; " + styleImg;
                img = "<i style=\"" + styleImg + "\" class=\"" + imageName + "\"></i>";
                txt = (txt == null) ? "" : txt + "<br />";
                sb.appendHtmlConstant("<div style=\"text-align:left\">" + txt + img + "</div>");
                break;
            case RIGHT:
                styleImg = "margin-left:5px; " + styleImg;
                img = "<i style=\"" + styleImg + "\" class=\"" + imageName + "\"></i>";
                sb.appendHtmlConstant("<div style=\"text-align:left\">" + txt + img + "</div>");
                break;
            case TOP:
                styleImg = "margin-button:5px; " + styleImg;
                img = "<i style=\"" + styleImg + "\" class=\"" + imageName + "\"></i>";
                txt = (txt == null) ? "" : "<br />" + txt;
                sb.appendHtmlConstant("<div style=\"text-align:left\">" + img + txt + "</div>");
                break;
            default:
                styleImg = "margin-right:5px; " + styleImg;
                img = "<i style=\"" + styleImg + "\" class=\"" + imageName + "\"></i>";
                sb.appendHtmlConstant("<div>" + img + txt + "</div>");
        }

        return sb.toSafeHtml();
    }
    
    /**
     * Преход на выбранную страницу.
     * @param tm тип меню
     */
    public static void goTo(TypeMenu tm) {
        if (tm == null) return;
//        Log.debug("GoTO:"+tm.getName());
        switch (tm) {
            case Systems:
                History.newItem(SystemsPlace.VIEW_HISTORY_TOKEN+":");
                break;
            case SystemsSettings:
                History.newItem(SystemsSettingsPlace.VIEW_HISTORY_TOKEN+":");
                break;
            case InterfaceEthernet:
                History.newItem(EthernetListPlace.VIEW_HISTORY_TOKEN+":");
                break;
            case UserGroup:
                History.newItem(UsersGroupsPlace.VIEW_HISTORY_TOKEN+":");
                break;
            case Users:
                History.newItem(UsersListPlace.VIEW_HISTORY_TOKEN+":");
                break;
            case Tools:
                History.newItem(ToolsPlace.VIEW_HISTORY_TOKEN+":");
                break;
            case ToolsReboot:
            case ToolsBackup:
            case ToolsReset:
            case ToolsUpdate:
                History.newItem(ToolsPlace.VIEW_HISTORY_TOKEN+":"+tm.getName());
                break;
            default:
                History.newItem(PageDevPlace.VIEW_HISTORY_TOKEN+":");
        }
    }
}
