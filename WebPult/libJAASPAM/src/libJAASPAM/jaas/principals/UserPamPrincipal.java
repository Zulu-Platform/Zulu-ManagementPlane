// $Id$

package libJAASPAM.jaas.principals;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;
import org.jvnet.libpam.UnixUser;

/**
 * Unix пользователь.
 * @author Носов А.В.
 */
public class UserPamPrincipal extends Object implements Principal {
    
    // Variables declaration
    /** Имя пользователя. */
    private String userName;
    /** Вспомогательная информация о пользователе 
     * (номер телефона, адрес, полное имя и так далее). */
    private String gecos;
    /** Домашняя директория. */
    private String homeDir;
    /** Запускаемая командная строка. */
    private String shell;
    /** Идентификатор пользователя. */
    private int uid;
    /** Идентификатор группы пользователя. */
    private int gid;
    /** Список групп. */ 
    private Set<String> groups;
    // End of variables declaration
    
    /**
     * Create class.
     * @param user Unix пользователь
     */
    public UserPamPrincipal(UnixUser user) {
        super();
        if (user == null) throw new NullPointerException("NULL UnixUser.");
        userName = user.getUserName();
        gecos = user.getGecos();
        homeDir = user.getDir();
        shell = user.getShell();
        uid = user.getUID();
        gid = user.getGID();
        groups = Collections.unmodifiableSet(user.getGroups());
    }

    @Override
    public String getName() {
        return userName;
    }
    
    /**
     * Возвращает вспомогательную информацию о пользователе.
     * @return вспомогательная информация
     */
    public String getGecos() {
        return gecos;
    }

    /**
     * Возвращает путь домашней дирректории пользователя.
     * @return путь домашней директории
     */
    public String getHomeDir() {
        return homeDir;
    }

    /**
     * Возвращает путь до оболочки командной строки.
     * @return командная строка
     */
    public String getShell() {
        return shell;
    }

    /**
     * Возвращает идентификатор пользователя.
     * @return идентификатор
     */
    public int getUid() {
        return uid;
    }

    /**
     * Возвращает идентификатор группы пользователя.
     * @return идентификатор группы
     */
    public int getGid() {
        return gid;
    }

    /**
     * Возвращает список групп пользователя.
     * @return список групп
     */
    public Set<String> getGroups() {
        return groups;
    }
}
