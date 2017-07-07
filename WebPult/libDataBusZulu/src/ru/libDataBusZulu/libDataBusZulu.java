/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu;

import java.util.List;
import libMessage.client.messages.Group;
import libMessage.client.messages.SystemsSettings;
import libMessage.client.messages.User;
import org.apache.log4j.Logger;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import ru.libDataBusZulu.messages.UserGroup;
import ru.libDataBusZulu.receives.DBusReceive;
import ru.libDataBusZulu.messages.Systems;

/**
 *
 * @author Носов А.В.
 */
public class libDataBusZulu implements DBusReceive {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(libDataBusZulu.class);
    public static final String name = "ru.libDataBusZulu";
    
    private final UserGroup userGroup;
    private final Systems systems;
    // End of variables declaration
    
    public static void main(String[] args) {
        if (log.isDebugEnabled()) 
            log.debug("MyPath:"+System.getProperty("java.library.path"));
        DBusConnection conn = null;
        try {
            if (log.isDebugEnabled()) 
                log.debug("Connect to session bus.");
            conn = DBusConnection.getConnection(DBusConnection.SESSION);
        } catch (DBusException ex) {
            log.error(ex.getMessage(), ex);
            System.exit(0);
        }
        try {
            if (log.isDebugEnabled()) 
                log.debug("Register name:" + name);
            conn.requestBusName(name);
        } catch (DBusException ex) {
            log.error(ex.getMessage(), ex);
            System.exit(0);
        }
        try {
            if (log.isDebugEnabled()) 
                log.debug("Export.");
            conn.exportObject("/", new libDataBusZulu());
        } catch (DBusException ex) {
            log.error(ex.getMessage(), ex);
            System.exit(0);
        }
        libDataBusZulu libDataBusZulu = new libDataBusZulu();
        if (log.isDebugEnabled()) 
                log.debug("Wait message:");
    }

    public libDataBusZulu() {
        userGroup = new UserGroup();
        systems = new Systems();
    }
    
    @Override
    public boolean isRemote() {
        return false;
    }

    @Override
    public List<User> getUsersList() {
        List<User> users = userGroup.getUserList();
        return users;
    }

    @Override
    public SystemsSettings getSystemsSettings() {
        SystemsSettings ss = systems.getSystemsSettings();
        return ss;
    }
    
    @Override
    public String addUser(User user) {
        return userGroup.addUser(user);
    }
    
    @Override
    public String editUser(User user) {
        return userGroup.editUser(user);
    }
    
    @Override
    public String removeUser(String username) {
        return userGroup.removeUser(username);
    }
    
    @Override
    public User getUserByName(String name) {
        return userGroup.getUserByName(name);
    }
    
    @Override
    public List<Group> getGroupList() {
        return userGroup.getGroupList();
    }

    @Override
    public String editSystemSettings(SystemsSettings ss) {
        return systems.editSystemSettings(ss);
    }
}
