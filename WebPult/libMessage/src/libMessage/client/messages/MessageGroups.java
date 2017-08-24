/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import libMessage.client.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Запросить/Отправить/Принять список групп.
 * @author Носов А.В.
 */
public class MessageGroups extends Message implements Serializable {
    
    // Variables declaration
    /** Список групп. */
    private List<Group> groups;
    // End of variables declaration
    
    /**
     * Создание сообщения .
     */
    public MessageGroups() {
    }

    /**
     * Возвращает список групп.
     * @return список групп
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Устанавливает список групп.
     * @param groups список групп
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    /**
     * Добавляет в список группу.
     * @param group группа
     */
    public void addGroup(Group group) {
        if (group == null) return;
        if (groups == null) groups = new ArrayList<Group>();
        groups.add(group);
    }
}