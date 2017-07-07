/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.stocks;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import libMessage.client.messages.Group;

/**
 *
 * @author Носов А.В.
 */
public interface IGroupsData extends PropertyAccess<Group> {
    
    @Path("gid")
    ModelKeyProvider<Group> key();
    
//    @Path("name")
    LabelProvider<Group> name();
    
    @Path("name")
    ValueProvider<Group, String> nameProp();
}
