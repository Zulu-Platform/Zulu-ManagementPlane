/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libJAASPAM.jaas.principals;

import java.security.Principal;
import java.util.Set;

/**
 * Группа.
 * @author Носов А.В.
 */
public class GroupPamPrincipal  extends Object implements Principal {
    
    // Variables declaration
    /** Имя группы. */
    private String name;
    /** Идентификатор группы. */
    private int gid;
    /** Список пользоватлей. */ 
    private Set<String> users;
    // End of variables declaration
    
    /**
     * Create class.
     */
    public GroupPamPrincipal() {
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает идентификатор группы.
     * @return идентификатор группы
     */
    public int getGid() {
        return gid;
    }

    /**
     * Возвращает список пользователей в группе.
     * @return список пользователей
     */
    public Set<String> getUsers() {
        return users;
    }
}
