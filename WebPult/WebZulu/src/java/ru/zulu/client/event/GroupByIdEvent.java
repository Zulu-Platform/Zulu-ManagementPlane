/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.event;

import com.google.gwt.event.shared.GwtEvent;
import libMessage.client.messages.Group;

/**
 * Событие ошибки.
 * @author Носов А.В.
 */
public class GroupByIdEvent extends GwtEvent<GroupByIdEventHandler> {

    // Variables declaration
    public static Type<GroupByIdEventHandler> TYPE = new Type<GroupByIdEventHandler>();
    
    /** Пользователь. */
    private Group group;
    // End of variables declaration

    public GroupByIdEvent(Group group) {
        this.group = group;
    }
    
    @Override
    public Type<GroupByIdEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(GroupByIdEventHandler handler) {
        handler.group(this);
    }

    /**
     * Возвращает группу из БД.
     * @return группу
     */
    public Group getGroup() {
        return group;
    }
}
