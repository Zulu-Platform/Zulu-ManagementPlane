/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import libMessage.client.Message;

/**
 * Пользователь.
 * http://rus-linux.net/MyLDP/consol/15-primerov-po-useradd.html
 * @author Носов А.В.
 */
public class User extends Message implements Serializable {
    
    // Variables declaration
    private static final long serialVersionUID = 1L;
    /** Имя пользователя. */
    private String name;
    /** Новое имя пользователя. */
    private String newName;
    /** Пароль. */
    private String password;
    /** Вспомогательная информация о пользователе 
     * (номер телефона, адрес, полное имя и так далее). */
    private String gecos;
    /** Идентификатор пользователя. */
    private int uid;
    /** Идентификатор группы пользователя. */
    private int gid;
    /** Список групп. */ 
    private List<Group> groups;
    /** Дата создания пользователя. */
    private Date createTime;
    /** Дата последнего изменения пароля. */
    private Date lastPasswordChange;
    /** Срок действия пароля. */
    private int passwordExpires;
    /** Дата сброса пароля. */
    private Date passwordInactive;
    /** Срок дейсвия учетной записи. */
    private Date accountExpires;
    
    /** IP  адрес. */
    private String ipv4;
    // End of variables declaration
    
    public User() {
    }

    /**
     * Устанавливает имя пользователя.
     * @return имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает имя пользователя.
     * @param name имя пользователя
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    /**
     * Возвращает пароль пользователя.
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Установить пароль пользователя.
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает идентификатор пользователя.
     * @return идентификатор
     */
    public int getUid() {
        return uid;
    }

    /**
     * Устанавливает идентификатор пользователя.
     * @param uid идентификатор пользователя
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Возвращает идентификатор группы пользователя.
     * @return идентификатор группы
     */
    public int getGid() {
        return gid;
    }

    /**
     * Устанавливает идентификатор группы пользователя.
     * @param gid идентификатор группы
     */
    public void setGid(int gid) {
        this.gid = gid;
    }

    /**
     * Возвращает список групп пользователя.
     * @return список групп
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Устнавливает список групп пользователя.
     * @param groups список групп
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    /**
     * Добавляет группу к списку.
     * @param group группа
     */
    public void addGroup(Group group) {
        if (group == null) return;
        if (groups == null) groups = new ArrayList<Group>();
        groups.add(group);
    }
    
    /**
     * Очистить список групп.
     */
    public void clearGroup() {
        groups = null;
    }
    
    /**
     * Возвращает вспомогательную информацию о пользователе.
     * @return вспомогательная информация
     */
    public String getGecos() {
        return gecos;
    }

    /**
     * Устанавливает вспомогательную информацию о пользователе.
     * @param gecos вспомогательная информация
     */
    public void setGecos(String gecos) {
        this.gecos = gecos;
    }

    /**
     * Возвращает дату создания пользователя.
     * @return дата создания
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Устанавливает дату создания пользователя.
     * @param createUser дата создания
     */
    public void setCreateTime(Date createUser) {
        this.createTime = createUser;
    }

    /**
     * Возвращает дату последнего изменения пароля 
     * @return дата изменения пароля
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * Устанавливает дату последнего изменения пароля.
     * @param lastPasswordChange  дата изменения пароля
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * Возвращает срок дейсвия пароля.
     * @return срок действия пароля
     */
    public int getPasswordExpires() {
        return passwordExpires;
    }

    /**
     * Устанавливает срок действия пароля.
     * @param passwordExpires срок действия пароля
     */
    public void setPasswordExpires(int passwordExpires) {
        this.passwordExpires = passwordExpires;
    }

    /**
     * Возвращает количество дней, через котрое пароль перестает действовать.
     * @return кол-во дней до деактивации пароля
     */
    public Date getPasswordInactive() {
        return passwordInactive;
    }

    /**
     * Устанавливает количество дней, через котрое пароль перестает действовать.
     * @param passwordInactive кол-во дней до деактивации пароля
     */
    public void setPasswordInactive(Date passwordInactive) {
        this.passwordInactive = passwordInactive;
    }

    /**
     * Возвращает срок дейсвия учетной записи
     * @return срок дейсвия учетной записи
     */
    public Date getAccountExpires() {
        return accountExpires;
    }

    /**
     * Устанавливает срок дейсвия учетной записи.
     * @param accountExpires срок дейсвия учетной записи
     */
    public void setAccountExpires(Date accountExpires) {
        this.accountExpires = accountExpires;
    }

    /**
     * Возращает IPv4 авторизации.
     * @return IPv4
     */
    public String getIpv4() {
        return ipv4;
    }

    /**
     * Устанавливает IPv4 авторизации.
     * @param ipv4 IPv4
     */
    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }
}
