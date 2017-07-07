/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import java.io.Serializable;
import java.util.Date;
import libMessage.client.Message;

/**
 * Системные настройки.
 * @author Носов А.В.
 */
public class SystemsSettings extends Message implements Serializable {
    
    // Variables declaration
    private static final long serialVersionUID = 1L;
    /** Идентификатор продукта. */
    private String productID;
    /** Серийный номер. */
    private String serialnumber;
    /** Версия прошивки. */
    private String firmware;
    /** Версия железа. */
    private String hardware;
    /** Версия ПО. */
    private String software;
    /** IP  адрес. */
    private String ipv4;
    /** MAC адрес. */
    private String mac;
    /** Маска. */
    private String mask;
    /** Шлюз. */
    private String getway;
    /** Имя. */
    private String hostname;
    /** Контактное лицо. */
    private String contact;
    /** Описание. */
    private String location;
    /** Дата. */
    private Date date;
    /** SSH сервер. */
    private Boolean ssh;
    /** Telnet сервер. */
    private Boolean telnet;
    /** SNMP сервер. */
    private Boolean snmp;
    // End of variables declaration
    
    public SystemsSettings() {
        this.ssh = false;
        this.telnet = false;
        this.snmp = false;
    }

    /**
     * Возращает IPv4.
     * @return IPv4
     */
    public String getIpv4() {
        return ipv4;
    }

    /**
     * Устанавливает IPv4.
     * @param ipv4 IPv4
     */
    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    /**
     * Возвращает маску.
     * @return маска
     */
    public String getMask() {
        return mask;
    }

    /**
     * Устанавливает маску.
     * @param mask маска
     */
    public void setMask(String mask) {
        this.mask = mask;
    }

    /**
     * Возвращает шлюз.
     * @return шлюз
     */
    public String getGetway() {
        return getway;
    }

    /**
     * Устанавливает шлюз.
     * @param getway шлюз
     */
    public void setGetway(String getway) {
        this.getway = getway;
    }

    /**
     * Возвращает имя.
     * @return имя
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Устнавливает имя.
     * @param hostname имя
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Возвращает контактное лицо.
     * @return контактное лицо
     */
    public String getContact() {
        return contact;
    }

    /**
     * Устанавливает контактное лицо.
     * @param contact контактное лицо
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Возвращает описание местоположения устройства.
     * @return местоположение устройства
     */
    public String getLocation() {
        return location;
    }

    /**
     * Устанавливает описание местоположения устройства.
     * @param location местоположение
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Возвращает дату.
     * @return дата
     */
    public Date getDate() {
        return date;
    }

    /**
     * Устанавливает дату.
     * @param date дата
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Возвращает идентификатор продукта.
     * @return ижентификатор продукта
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Устанавливает идентификатор продукта.
     * @param productID идентификатор продукта
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Возвращает серийный номер.
     * @return серийный номер
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * Устанавливает серийный номер.
     * @param serialnumber серийный номер
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    /**
     * Возвращает версию прошивки.
     * @return версия прошивки
     */
    public String getFirmware() {
        return firmware;
    }

    /**
     * Устанавливает версю прошивки.
     * @param firmware версия прошивки
     */
    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    /**
     * Возвращает версию железа.
     * @return версия железа
     */
    public String getHardware() {
        return hardware;
    }

    /**
     * Устанавливает версию железа.
     * @param hardware версия железа
     */
    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    /**
     * Возвращает версию ПО.
     * @return версия ПО
     */
    public String getSoftware() {
        return software;
    }

    /**
     * Устнавливает версию ПО.
     * @param software версия ПО
     */
    public void setSoftware(String software) {
        this.software = software;
    }

    /**
     * Возвращает MAC адрес.
     * @return MAC адрес
     */
    public String getMac() {
        return mac;
    }

    /**
     * Устанавливает MAC адрес.
     * @param mac MAC адрес
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * Возвращает состояние SSH сервера.
     * @return <b>true</b> - включен, <br>
     * <b>false</b> - выключен
     */
    public Boolean isSsh() {
        return ssh;
    }

    /**
     * Устанавливает состояние SSH сервера.
     * @param ssh состояние SSH сервера
     */
    public void setSsh(Boolean ssh) {
        this.ssh = ssh;
    }

    /**
     * Возвращает состояние telnet сервера
     * @return <b>true</b> - включен, <br>
     * <b>false</b> - выключен
     */
    public Boolean isTelnet() {
        return telnet;
    }

    /**
     * Устанавливае состояние Telnet сервера.
     * @param telnet состояние telnet сервера
     */
    public void setTelnet(Boolean telnet) {
        this.telnet = telnet;
    }

    /**
     * Возвращает состояние SNMP сервера.
     * @return <b>true</b> - включен, <br>
     * <b>false</b> - выключен
     */
    public Boolean isSnmp() {
        return snmp;
    }

    /**
     * Устнавливает состояние SNMP сервера.
     * @param snmp состояние SNMP сервер
     */
    public void setSnmp(Boolean snmp) {
        this.snmp = snmp;
    }
    
}
