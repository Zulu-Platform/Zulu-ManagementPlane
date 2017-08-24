/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
import ru.zulu.server.utils.I18N;

/**
 * Слушатель сессий.
 * Хранит список активных сессий.
 * @author Носов А.В.
 */
public class HttpSessionCollector implements HttpSessionListener {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(HttpSessionCollector.class);
    /** Список активных сессий. */
    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
    
    /** Загрузчик языковых ресурсов. */
    private final ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getCanonicalName());
    // End of variables declaration
    
    public HttpSessionCollector() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setMaxInactiveInterval(600);
        sessions.put(session.getId(), session);
        log.debug(I18N.getString(rb, "DEBUG_SESSION_CREATE") + " "
                + I18N.getString(rb, "DEBUG_SESSION_ID") + session.getId() + "; "
                + I18N.getString(rb, "DEBUG_SESSION_INTERVAL") + session.getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
        log.debug(I18N.getString(rb, "DEBUG_SESSION_DESTROYED") + " "
                + I18N.getString(rb, "DEBUG_SESSION_ID") + event.getSession().getId());
    }

    /**
     * Добавляет сесию в карту.
     * @param httpSession сессия
     */
//    public static void addSession(HttpSession httpSession) {
//        if (!isInRange(httpSession.getId())) {
//            sessions.put(httpSession.getId(), httpSession);
//            log.debug("Add session. ID = " + httpSession.getId() + "; "
//                    + "MaxInterval: " + httpSession.getMaxInactiveInterval());
//        }
//    }
    
    /**
     * Возвращает количество активных сессий.
     * @return количество активных сессий
     */
    public static int getActiveSessionsCount() {
        return sessions.size();
    }
    
    /** 
     * Возвращает активную сессию по идентификатору.
     * @param sessionId идентификатор сесси
     * @return сессия
     */
    public static HttpSession getSessionByID(String sessionId) {
        return sessions.get(sessionId);
    }
    
    /**
     * Возвращает коллекцию активных сессий.
     * @return коллекция активных сессий
     */
    public static Collection<HttpSession> getSessions() {
        return sessions.values();
    }
    
    /**
     * Возвращает true если эта сессия есть в карте.
     * @param sessionId идентификатор сессий
     * @return <b>true</b> - сессия найдена, <b>false</b> - сессия не найдета.
     */
    public static boolean isInRange(String sessionId) {
        return sessions.containsKey(sessionId);
    }
}
