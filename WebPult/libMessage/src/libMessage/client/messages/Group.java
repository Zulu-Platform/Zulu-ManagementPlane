/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import java.io.Serializable;
import libMessage.client.Message;

/**
 *
 * @author Носов А.В.
 */
public class Group extends Message implements Serializable {
    
    // Variables declaration
    private static final long serialVersionUID = 1L;
    /** Идентификатор группы. */
    private int gid;
    /** Название группы. */
    private String name;
    /** Описание группы. */
    private String description;
    // End of variables declaration

    public Group() {
    }

    public Group(int gid, String name) {
        this.gid = gid;
        this.name = name;
    }
    
    /**
     * Возвращает идентификатор группы.
     * @return идентификатор группы
     */
    public int getGid() {
        return gid;
    }

    /**
     * Устанавливает идентификатор группы.
     * @param gid идентификатор группы
     */
    public void setGid(int gid) {
        this.gid = gid;
    }

    /**
     * Возвращает название группы.
     * @return название группы
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название группы.
     * @param name название группы
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает описание группы.
     * @return описание группы
     */
    public String getDescription() {
        return name + "desr";
        //return description;
    }

    /** 
     * Устанавливает описание группы.
     * @param description описание группы
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
