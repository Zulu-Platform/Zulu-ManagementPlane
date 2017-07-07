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
import libMessage.client.types.TypeDuplex;

/**
 *
 * @author Носов А.В.
 */
public interface IDuplexData extends PropertyAccess<TypeDuplex> {
    
    @Path("code")
    ModelKeyProvider<TypeDuplex> key();
    @Path("description")
    LabelProvider<TypeDuplex> name();
    
    ValueProvider<TypeDuplex, Integer> code();
    ValueProvider<TypeDuplex, String> description();
}
