/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.messages;

import java.util.ArrayList;
import java.util.List;
import libMessage.client.messages.Group;
import libMessage.client.messages.User;
import org.apache.log4j.Logger;
import org.jvnet.libpam.PAMException;
import org.jvnet.libpam.UnixUser;
import ru.libDataBusZulu.exceptions.ErrorsLibDataBus;
import ru.libDataBusZulu.exceptions.ExceptionsLibDataBus;
import ru.libDataBusZulu.utils.ReadProperties;
import ru.libDataBusZulu.utils.Shell;
import ru.libDataBusZulu.utils.Utils;

/**
 * Данные пользователей и групп.
 * 
 * Доп данные о пользователе:
 * chfn --help
 * 
 * @author Носов А.В.
 */
public class UserGroup {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(UserGroup.class);
    public static final String path = "/etc/zulu/usergroup.properties";
    private int idAdmin = 1005;
    // End of variables declaration
    
    /** Имена. */
    public static enum names {
        group_default,
        groups;
    }
    
    public UserGroup() {
    }
    
    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>();
        try {
            Shell shell = Utils.getStartRuntime("getent group " + ReadProperties.getDefaultGroup());
            if (shell.getExitVal() != 0) return null;
            
            String str = shell.getOutput();
            if (str == null) return null;
            
            String[] gs = str.split("\\:");
            if (gs.length != 4) return null;

            String[] users = gs[3].split(",");
            for (String user : users) {
                User u = getUserByName(user);
                if (u != null) userList.add(u);
            }
            
        } catch (ExceptionsLibDataBus ex) {
            log.fatal(ex.getMessage());
        }
        return userList;
    }
    
    /**
     * Добавляет пользователя.
     * @param user пользователь
     * @return результат
     */
    public String addUser(User user) {
        if ( (user == null) || (user.getNewName() == null) || (user.getPassword() == null) )
            return ErrorsLibDataBus.ERROR_USERNAMEPASS.getDescription();
        
        if (log.isDebugEnabled()) log.debug("Start add user: " + user.getNewName());
        
        try {
            String groups = ReadProperties.getDefaultGroup();
            if (user.getGroups() != null)
                for (Group g : user.getGroups())
                    groups = groups + "," + g.getName();

            String str = "useradd -G " + groups +
                    " -M -c \"" + user.getGecos() + "\"" +
                    " -p $(openssl passwd -1 " + user.getPassword() + ")" +
                    " " + user.getNewName();
            
            Shell shell = Utils.getStartRuntime(str);
            
            if (shell.getExitVal() != 0) {
                log.fatal(shell.getMessage());
                return shell.getMessage();
            }
            return null;
        } catch (ExceptionsLibDataBus ex) {
            log.fatal(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    /**
     * Редактирование пользователя.
     * @param user пользователь
     * @return результат
     */
    public String editUser(User user) {
        if ( (user == null) || (user.getName() == null) || (user.getNewName() == null) ||
             (user.getName().isEmpty()) || (user.getNewName().isEmpty())) 
            return ErrorsLibDataBus.ERROR_USERNAME.getDescription();
        
        if (log.isDebugEnabled()) log.debug("Start " + user.getName() + " edit.");
        
        try {
            // Замена имени
            String newName = (!user.getName().equals(user.getNewName())) ? "" : " -l \"" + user.getNewName() + "\"";
            // Замена групп
            String groups = "";
            if (!isAdmin(user.getName()) || user.getGroups() != null) {
                groups = " -G " + ReadProperties.getDefaultGroup();
                for (Group g : user.getGroups())
                    groups = groups + "," + g.getName();
            }
            // Замена коментария
            String comment = ( (user.getGecos() == null) || (user.getGecos().isEmpty()) ) ? "" : " -c \"" + user.getGecos() + "\"";
            // Замена пароля
            String pass = ( (user.getPassword() == null) || (user.getPassword().isEmpty()) )  ? "" : 
                    " -p $(openssl passwd -1 "+ user.getPassword() + ")";
            // Срок действия пароля
            String inactive = (user.getPasswordExpires() < 1) ? "" : 
                               " -f " + user.getPasswordExpires();
            // Дата сброса пароля
            // Срок действия учетной записи
            String expiredate = (user.getAccountExpires() == null) ? "" : 
                                " -e " + user.getAccountExpires().toString();
            
            String str = "usermod " + newName + groups + comment + inactive + 
                         expiredate + pass + " " + user.getName();
            if (log.isDebugEnabled()) log.debug(str);
            
            Shell shell = Utils.getStartRuntime(str);
            
            if (shell.getExitVal() != 0) {
                log.fatal(shell.getMessage());
                return shell.getMessage();
            }
            return null;
        } catch (ExceptionsLibDataBus ex) {
            log.fatal(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    /**
     * Удаляет пользователя из системы.
     * @param username имя пользователя
     * @return ответ
     */
    public String removeUser(String username) {
        if (username == null) return ErrorsLibDataBus.ERROR_USERNAME.getDescription();
        if (isAdmin(username)) return ErrorsLibDataBus.ERROR_ADMIN_REMOVE.getDescription();
        
        String str = "deluser --force " + username;
        Shell shell = Utils.getStartRuntime(str);

        if (shell.getExitVal() != 0) {
            log.fatal(shell.getMessage());
            return shell.getMessage();
        }
        return null;
        
    }
    
    /**
     * Возвращает пользователя по его имени.
     * @param name имя пользователя
     * @return пользователь
     */
    public User getUserByName(String name) {
        UnixUser unix;
        try {
            unix = new UnixUser(name);
        } catch (PAMException ex) {
            log.error(ex.getMessage());
            return null;
        }
        
        User user = new User();
        user.setUid(unix.getUID());
        user.setGid(unix.getGID());
        user.setName(unix.getUserName());
        user.setGecos(unix.getGecos());
        for (String str : unix.getGroups()) {
            Group group = getGroupByName(str);
            if (group != null) 
                user.addGroup(getGroupByName(str));
        }
        
        return user;
    }
    
    /**
     * Возвращает список групп.
     * @return список групп
     */
    public List<Group> getGroupList() {
        List<Group> groups = new ArrayList<Group>();
        try {
            String[] groupList = ReadProperties.getGroupList();
            for (String name : groupList) {
                Group group = getGroupByName(name);
                if (group != null) 
                    groups.add(group);
            }
        } catch (ExceptionsLibDataBus ex) {
            log.fatal(ex.getMessage());
        }
        return groups;
    }
    
    /**
     * Возвращает группу по ее имени.
     * @param name имя группы
     * @return группа
     */
    private Group getGroupByName(String name) {
        Group group;
        try {
            String def = ReadProperties.getDefaultGroup();
            if (name.equals(def)) return null;
            
            Shell shell = Utils.getStartRuntime("getent group " + name);
            if (shell.getExitVal() != 0) return null;
            
            String str = shell.getOutput();
            if (str == null) return null;
            
            String[] gs = str.split("\\:");
            if (gs.length != 4) return null;
            int gid = Integer.valueOf(gs[2]);
            group = new Group(gid, name);
            return group;
        } catch (NumberFormatException | ExceptionsLibDataBus ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Проверяет пользователя на пренадлежность к администратору.
     * @param username имя пользователя
     * @return <b>true</b> - администратор
     * <b>false</b> - не администратор
     */
    private boolean isAdmin(String username) {
        Shell shell = Utils.getStartRuntime("id " + username);
        if (shell.getExitVal() != 0) return true;
        String str = shell.getOutput();

        if ( (str == null) || (!str.startsWith("uid=")) ) return true;
        int i = str.indexOf("(");
        if (i < 3) return true;
        str = str.substring(4, i);
        try {
            int id = Integer.valueOf(str);
            return id == idAdmin;
        } catch (NumberFormatException ex) {
            return true;
        }
    }
}
