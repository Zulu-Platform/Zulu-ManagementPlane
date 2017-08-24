/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import java.util.List;
import libMessage.client.messages.Group;

/**
 * Событие ошибки.
 * @author Носов А.В.
 */
public class GroupListEvent extends GwtEvent<GroupListEventHandler> {

    // Variables declaration
    public static Type<GroupListEventHandler> TYPE = new Type<GroupListEventHandler>();
    
    /** Список групп. */
    private List<Group> groups;
    // End of variables declaration

    public GroupListEvent(List<Group> groups) {
        this.groups = groups;
    }
    
    @Override
    public Type<GroupListEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(GroupListEventHandler handler) {
        handler.groupsList(this);
    }

    /**
     * Возвращает список групп.
     * @return группы
     */
    public List<Group> getGroups() {
        return groups;
    }
}
