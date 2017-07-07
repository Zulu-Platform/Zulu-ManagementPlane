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
import libMessage.client.messages.Ethernet;

/**
 *
 * @author Носов А.В.
 */
public interface IEthernetsData extends PropertyAccess<Ethernet> {
    
    @Path("id")
    ModelKeyProvider<Ethernet> key();
    
//    @Path("name")
//    LabelProvider<Ethernet> nameLabel();
    
    ValueProvider<Ethernet, Short> number();
    ValueProvider<Ethernet, String> name();
    ValueProvider<Ethernet, Boolean> status();
    ValueProvider<Ethernet, String> speed();
    ValueProvider<Ethernet, String> speedDescription();
    ValueProvider<Ethernet, String> duplex();
    ValueProvider<Ethernet, String> duplexDescription();
    ValueProvider<Ethernet, Boolean> security();
    ValueProvider<Ethernet, Boolean> flowControl();
    ValueProvider<Ethernet, String> comment();
}
