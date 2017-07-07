/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.stocks;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import ru.zulu.client.gui.types.TypeMenu;

/**
 *
 * @author Носов А.В.
 */
public interface ITreeData extends PropertyAccess<TreeData> {
    
    @Path("id")
    ModelKeyProvider<TreeData> key();
    ValueProvider<TreeData, Integer> id();
    ValueProvider<TreeData, String> name();
    ValueProvider<TreeData, TypeMenu> typeMenu();
    ValueProvider<TreeData, Boolean> enable();
}
