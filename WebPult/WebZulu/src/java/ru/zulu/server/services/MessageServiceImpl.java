/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.security.Principal;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import libMessage.client.Message;
import ru.zulu.client.messages.txrx.MessageService;
import libMessage.client.messages.Group;
import libMessage.client.messages.User;
import libMessage.client.systems.MessageSystem;
import libMessage.client.systems.MessageSystems;
import libMessage.client.messages.MessageUsers;
import ru.zulu.client.messages.types.TypeSuccess;
import libMessage.client.types.TypeMessage;
import libJAASPAM.jaas.UsernamePasswordCallbackHandler;
import libJAASPAM.jaas.principals.UserPamPrincipal;
import libMessage.client.messages.MessageGroups;
import libMessage.utils.SystemMsg;
import ru.zulu.server.HttpSessionCollector;
import ru.zulu.server.utils.I18N;
import libMessage.client.messages.SystemsSettings;
import libMessage.exceptions.ErrorsMessage;
import static libMessage.utils.SystemMsg.getMsgError;
import ru.libDataBusZulu.messages.UserGroup;
import ru.libDataBusZulu.messages.Systems;
import ru.zulu.client.messages.types.TypeErrors;
import ru.zulu.server.exceptions.ErrorsWebZulu;

public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MessageServiceImpl.class);
    
    /** Загрузчик языковых ресурсов. */
    private final ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getCanonicalName());
    
    /** Системная шина. */
//    private DBusConnection conn;
    // End of variables declaration
    
    @Override
    public Message getMessage(Message msg) {
        TypeMessage tm;
        if ( (msg == null) || (msg.getTypeMessage() == null) ) tm = TypeMessage.Error;
        else tm = msg.getTypeMessage();
        
        if (log.isDebugEnabled())
            log.debug(I18N.getString(rb, "DEBUG_TYPE_INPUT_MESSAGE") + tm.name());
        
        HttpSession session = this.getThreadLocalRequest().getSession();
        if (session.isNew()) {
//            int interval = 60; //1min
//            session.setMaxInactiveInterval(interval);
            if (tm != TypeMessage.Login)
                tm = TypeMessage.NewSession;
        }
        
        Message msgRx = (Message) SystemMsg.getMsgErrorType();
        if ( !isAuthorization(session, tm) ) return msgRx;
        switch (tm) {
            case NewSession:
                msgRx = (Message) SystemMsg.getMsgError(ErrorsMessage.WARNING_NEW_SESSION.getCode(), 
                        null, ErrorsMessage.WARNING_NEW_SESSION.getDescription());
                break;
            case Login:
                msgRx = (Message) isAuthentication(session, (User) msg);
                break;
            case RqSystemsSettings:
                isAuthorization(session, tm);
                msgRx = (Message) getSystemsSettings();
                break;
            case SystemsSettingsEdit:
                SystemsSettings ss = (SystemsSettings) msg;
                msgRx = (Message) editSystemsSettings(ss);
                break;
            case RqUsers:
                msgRx = getAllUsers();
                break;
            case RqUserByName:
                User userByName = (User) msg;
                msgRx = getUserByName(userByName);
                break;
            case UserAdd:
                msgRx = addUser((User) msg);
                break;
            case UserEdit:
                msgRx = editUser((User) msg);
                break;
            case UserRemove:
                msgRx = removeUser((User) msg);
                break;
            case UsersRemove:
                MessageUsers muds = (MessageUsers) msg;
                msgRx = removeUsers(muds);
                break;
            case RqGroups:
                msgRx = getAllGroups();
                break;
            case Error:
//                msgRx = msg;
                break;
            case isLoginName:
//                if (!(msg instanceof User)) break;
//                msgRx = isUserLogin((User) msg);
                break;
            case Logout:
//                User uout = (User) msg;
                session.invalidate();
//                CallbackHandler handler = new UsernamePasswordCallbackHandler(user.getName(), user.getPassword());
//                LoginContext context = new LoginContext("pam-login", handler);
//                context.logout();
                msgRx = (Message) SystemMsg.getMsgSuccess(
                        TypeSuccess.LOGOUT.getCode(),
                        TypeSuccess.LOGOUT.getDescription());
                break;
            case RqEthernets:
//                MessageEthernets mes = (MessageEthernets) msg;
//                if (mes == null) mes = new MessageEthernets();
//                mes.setTypeMessage(TypeMessage.RxEthernets);
//                mes.setEthernets(getAllEthernets());
//                msgRx = (Message) mes;
                break;
            case EthernetsEdit:
//                MessageEthernets ethEs = (MessageEthernets) msg;
//                if (ethEs == null) ethEs = new MessageEthernets();
//                msgRx = (Message) editEthernets(ethEs.getEthernets());
                break;
            case EthernetEdit:
//                Ethernet ethE = (Ethernet) msg;
//                msgRx = (Message) editEthernet(ethE);
                break;
            default:
                msgRx = (Message) SystemMsg.getMsgErrorType();
        }
        
        return msgRx;
    }
    
    /**
     * Проверка подлинности имени и пароля.
     * @param session сессия
     * @param msg сообщение
     * @return пользователь
     */
    private Message isAuthentication(HttpSession session, User user) {
        if ( (user == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_USERNAME.getCode(),
                                        TypeErrors.ERROR_USERNAME.getHeadingText(),
                                        TypeErrors.ERROR_USERNAME.getDescription());
        
        user.setUid(-1);
        user.setTypeMessage(TypeMessage.LoginInfo);
        if ((user.getName() == null) || (user.getPassword() == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_USERNAMEPASS.getCode(),
                                        TypeErrors.ERROR_USERNAMEPASS.getHeadingText(),
                                        TypeErrors.ERROR_USERNAMEPASS.getDescription());
        
        try {
            if (log.isDebugEnabled()) {
                log.debug("Login DLL:"+System.getProperty("java.security.auth.login.config"));
                log.debug("User:" + user.getName() + "; Pass:" + user.getPassword());
            }
            CallbackHandler handler = new UsernamePasswordCallbackHandler(user.getName(), user.getPassword());
            LoginContext context = new LoginContext("pam-login", handler);
            context.login();
            
            Subject subject = context.getSubject();
            Set principals = subject.getPrincipals();
            Iterator i = principals.iterator();
            while (i.hasNext()) {
                Principal p = (Principal) i.next();
                if (p instanceof UserPamPrincipal) {
                    UserPamPrincipal up = (UserPamPrincipal) p;
                    if (!up.getName().equals(user.getName())) continue;
                    // Если пользователь не входит в группу monitoring
                    // то это не тот пользователь
                    user.setUid(up.getUid());
                    user.setGid(up.getGid());
                    user.setGecos(up.getGecos());
                    user.setPassword(null);
                    int gid = 510;
                    for (String str : up.getGroups())
                        user.addGroup(new Group(gid++, str));
                    session.setAttribute("UserPamPrincipal", up);
                }
            }
//            HttpSessionCollector.addSession(session);
        } catch (LoginException ex) {
            log.error("LoginException:"+ex.getMessage());
//            user.setPassword(null);
            return SystemMsg.getMsgError(TypeErrors.ERROR_AUTHENTICATION.getCode(),
                                        TypeErrors.ERROR_AUTHENTICATION.getHeadingText(),
                                        TypeErrors.ERROR_AUTHENTICATION.getDescription());
        }
        
        if (user.getUid() > 0) {
            user.setIpv4(this.getThreadLocalRequest().getRemoteAddr());
        }
        
        return user;
    }
    
    /**
     * Проверка прав на выполнение.
     * @param session сессия
     * @return <b>true</b> - предоставить доступ,
     * <b>false</b> - отказать в доступе
     */
    private boolean isAuthorization(HttpSession session, TypeMessage tm) {
        switch (tm) {
            case Login:
            case Logout:
            case Error:
            case RqSystemsSettings:
            case NewSession:
                return true;
        }
        
        Object objSession = session.getAttribute("UserPamPrincipal");
        HttpSession saveSession = HttpSessionCollector.getSessionByID(session.getId());
        if (saveSession == null) return false;
        Object objSave = saveSession.getAttribute("UserPamPrincipal");
        UserPamPrincipal userSession = null;
        UserPamPrincipal userSave = null;
        if (objSave instanceof UserPamPrincipal)
            userSave = (UserPamPrincipal)objSave;
        if (objSession instanceof UserPamPrincipal)
            userSession = (UserPamPrincipal)objSession;
        
        if ((userSession == null) || (userSave == null)) {
            log.fatal("Оп-па! " + userSession + " " + userSave);
            return false;
        }
        
        if ( (userSession.getName() == null) || (userSave.getName() == null) ||
             (userSession.getHomeDir() == null) || (userSave.getHomeDir() == null) ||
             (userSession.getGroups() == null) || (userSave.getGroups() == null) ) {
            log.fatal(ErrorsWebZulu.FATAL_AUTH_SESSION.getDescription() + " ( " + userSave.getUid() + " )");
            if (log.isDebugEnabled())
                log.debug("Name:" + userSession.getName() + ":" + userSave.getName() + "; "
                        + "HomeDir:" + userSession.getHomeDir() + ":" + userSave.getHomeDir() + "; "
                        + "Groups:" + userSession.getGroups() + ":" + userSave.getGroups());
            return false;
        }
        
        if (log.isDebugEnabled())
            log.debug("isAuthorization:" + userSession.getName() + " "
                      + userSave.getName());
        
        if (userSession.getUid() != userSave.getUid()) {
            log.fatal(ErrorsWebZulu.FATAL_AUTH_SESSION.getDescription() + 
                    " ( " + userSession.getUid()+":"+userSave.getUid() + " )");
            return false;
        }
        
        if (userSession.getGid() != userSave.getGid()) {
            log.fatal(ErrorsWebZulu.FATAL_AUTH_SESSION.getDescription() + 
                    " ( " + userSession.getGid()+":"+userSave.getGid()+ " )");
            return false;
        }
        
        if (!userSession.getName().equals(userSave.getName())) {
            log.fatal(ErrorsWebZulu.FATAL_AUTH_SESSION.getDescription() + 
                    " ( " + userSession.getName()+":"+userSave.getName() + " )");
            return false;
        }
        
        if (!userSession.getHomeDir().equals(userSave.getHomeDir())) {
            log.fatal(ErrorsWebZulu.FATAL_AUTH_SESSION.getDescription() + 
                    " ( " + userSession.getHomeDir()+":"+userSave.getHomeDir() + " )");
            return false;
        }
        
        switch (tm) {
            case RqSystemsSettings:
            case SystemsSettingsEdit:
                return true;
            case RqUsers:
            case RqUserByName:
            case UserAdd:
            case UserEdit:
            case UserRemove:
            case UsersRemove:
            case RqGroups:
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Возвращает общую информацию о системе.
     * @return информация о системе
     */
    private SystemsSettings getSystemsSettings() {
        Systems systems = new Systems();
        SystemsSettings ss = systems.getSystemsSettings();
        ss.setTypeMessage(TypeMessage.RxSystemsSettings);
        
        return ss;
    }

    /**
     * Редактировать системные настройки.
     * @param ss системные настройки
     */
    private Message editSystemsSettings(SystemsSettings ss) {
        if ( (ss == null) )
            return SystemMsg.getMsgError(TypeErrors.ERROR_EDIT_SYSTEMSETTINGS.getCode(),
                                        TypeErrors.ERROR_EDIT_SYSTEMSETTINGS.getHeadingText(),
                                        TypeErrors.ERROR_EDIT_SYSTEMSETTINGS.getDescription());
        
        Systems systems = new Systems();
        String str = systems.editSystemSettings(ss);
        str = ( (str == null) || (str.isEmpty())) ? "Системные настройки изменены. Перезагрузка системы." : str;
        
        return SystemMsg.getMsgSuccess(TypeSuccess.EDIT_SYSTEMSSETTIGNS.getCode(),
                                      TypeSuccess.EDIT_SYSTEMSSETTIGNS.getDescription(),
                                      str);
    }
    
//    /**
//     * Возвращает список Ethernet портов.
//     * @return Ethernet портов
//     */
//    private List<Ethernet> getAllEthernets() {
//        List<Ethernet> es = null;
//        try {
//            es = HibernateFactory.getInstance().getEthernetDAO().getAllEthernets();
//            if (es == null) es = new ArrayList<Ethernet>();
//            for (Ethernet e : es) {
//                if (e.getId() == 1) {
//                    es.remove(e);
//                    return es;
//                }
//            }
//        } catch (SQLException ex) {
//            log.fatal(ex);
//        }
//        if (es == null) es = new ArrayList<Ethernet>();
//        return es;
//    }
//    
//    /**
//     * Редактировать Ethernet интерфейс.
//     * @param eth Ethernet интерфейс
//     */
//    private Message editEthernets(List<Ethernet> eths) {
//        if ( (eths == null) || (eths.size() < 1) )
//            return getMsgError("Ошибка удаления групп.", 
//                               "Группы отсутствуют.");
//        
//        MessageSystems mg = new MessageSystems();
//        for (Ethernet eth : eths)
//            mg.addMsg(editEthernet(eth));
//        
//        return mg;
//    }
//    
//    /**
//     * Редактировать Ethernet интерфейс.
//     * @param eth Ethernet интерфейс
//     */
//    private MessageSystem editEthernet(Ethernet eth) {
//        if ( (eth == null) )
//            return getMsgError("Ошибка редактирования Ethernet интерфейса.", 
//                               "Нет изменений.");
//        
//        eth.setId((short)1);
//        try {
//            boolean b = HibernateFactory.getInstance().getEthernetDAO().editEthernet(eth);
//            if (!b)
//                return getMsgError("Ошибка редактирования Ethernet интерфейса.", 
//                                   "Ethernet интерфейс не записаны в БД.");
//            
//        } catch (SQLException ex) {
//            log.error("Code:" + ex.getErrorCode() + "; " + ex.getMessage());
//            return getMsgError("Ошибка редактирования Ethernet интерфейса.", 
//                               "Ethernet интерфейс не изменен в базе.");
//        }
//        
//        return getMsgSuccess(TypeSuccess.EDIT_INTERFACE_ETHERNET.getCode(),
//                             TypeSuccess.EDIT_INTERFACE_ETHERNET.getDescription(),
//                             "Ethernet интерфейс изменен в БД.");
//    }
    
    /**
     * Возвращает список групп.
     * @return список групп
     */
    private Message getAllGroups() {
        MessageGroups groups = new MessageGroups();
        groups.setTypeMessage(TypeMessage.RxGroups);
        
        UserGroup ug = new UserGroup();
        groups.setGroups(ug.getGroupList());
        
        return groups;
    }
    
    /**
     * Возвращает информацию о пользователе по идентификатору.
     * @param user пользователь
     * @return пользователь
     */
    private Message getUserByName(User user) {
        if ( (user == null) || (user.getName() == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_USERNAME.getCode(),
                                        TypeErrors.ERROR_USERNAME.getHeadingText(),
                                        TypeErrors.ERROR_USERNAME.getDescription());
        
        User userInfo = new User();
        UserGroup ug = new UserGroup();
        User u = ug.getUserByName(user.getName());
        if (u != null) userInfo = u;
        userInfo.setTypeMessage(TypeMessage.RxUserByName);
        
        return userInfo;
    }
    
    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    private Message getAllUsers() {
        MessageUsers users = new MessageUsers();
        users.setTypeMessage(TypeMessage.RxUsers);
        
        UserGroup ug = new UserGroup();
        users.setUsers(ug.getUserList());
        
        return users;
    }
    
    /**
     * Добавить нового пользователя.
     * @param user пользователь
     */
    private Message addUser(User user) {
        if ( (user == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_USERNAME.getCode(),
                                        TypeErrors.ERROR_USERNAME.getHeadingText(),
                                        TypeErrors.ERROR_USERNAME.getDescription());
        
        if ((user.getNewName() == null) || (user.getPassword() == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_ADD_USER.getCode(),
                                        TypeErrors.ERROR_ADD_USER.getHeadingText(),
                                        TypeErrors.ERROR_ADD_USER.getDescription());
        
//        log.debug(user.getName()+" "+user.getNewName()+" "+user.getPassword()+" "+user.getGecos()+" "+user.getGroups().size());
        
        UserGroup ug = new UserGroup();
        String str = ug.addUser(user);
        str = ( (str == null) || (str.isEmpty())) ? "Пользователь " + user.getNewName() + " добавленн." : str;
        
        return SystemMsg.getMsgSuccess(TypeSuccess.ADD_USER.getCode(),
                                      TypeSuccess.ADD_USER.getDescription(),
                                      str);
    }
    
    /**
     * Редактировать пользователя.
     * @param user пользователя
     */
    private Message editUser(User user) {
        if ( (user == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_USERNAME.getCode(),
                                        TypeErrors.ERROR_USERNAME.getHeadingText(),
                                        TypeErrors.ERROR_USERNAME.getDescription());
        
        if ((user.getName() == null)) 
            return SystemMsg.getMsgError(TypeErrors.ERROR_EDIT_USER.getCode(),
                                        TypeErrors.ERROR_EDIT_USER.getHeadingText(),
                                        TypeErrors.ERROR_EDIT_USER.getDescription());
        
        UserGroup ug = new UserGroup();
        String str = ug.editUser(user);
        str = ( (str == null) || (str.isEmpty())) ? "Пользователь " + user.getName() + " изменен." : str;
        
        return SystemMsg.getMsgSuccess(TypeSuccess.EDIT_USER.getCode(),
                             TypeSuccess.EDIT_USER.getDescription(),
                             str);
    }
    
    /**
     * Удаляет список пользователей.
     * @param users список пользователей
     * @return результат удаления каждого пользователя списка
     */
    private Message removeUsers(MessageUsers msg) {
        if ( (msg == null) || (msg.getUsers() == null) || (msg.getUsers().size() < 1) )
            return getMsgError(TypeErrors.ERROR_REMOVE_USERBYNAME.getCode(), 
                              TypeErrors.ERROR_REMOVE_USERBYNAME.getHeadingText(),
                              TypeErrors.ERROR_REMOVE_USERBYNAME.getDescription());
        
        MessageSystems mg = new MessageSystems();
        for (User u : msg.getUsers())
            mg.addMsg(removeUser(u));
        
        return mg;
    }
    
    /**
     * Удаляет пользователя.
     * @param user пользователь
     * @return результат удаления пользователя
     */
    private MessageSystem removeUser(User user) {
        if ( (user == null) || (user.getName() == null) )
            return SystemMsg.getMsgError(TypeErrors.ERROR_REMOVE_USERBYNAME.getCode(), 
                                        TypeErrors.ERROR_REMOVE_USERBYNAME.getHeadingText(),
                                        TypeErrors.ERROR_REMOVE_USERBYNAME.getDescription());
        
        UserGroup ug = new UserGroup();
        String msg = ug.removeUser(user.getName());
        
        return SystemMsg.getMsgInfo(TypeSuccess.REMOVE_USER.getDescription() + 
                                   " " + user.getName(), msg);
    }

}