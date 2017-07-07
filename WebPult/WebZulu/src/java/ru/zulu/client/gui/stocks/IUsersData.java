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
import java.util.Date;
import libMessage.client.messages.User;

/**
 *
 * @author Носов А.В.
 */
public interface IUsersData extends PropertyAccess<User> {
    
    @Path("uid")
    ModelKeyProvider<User> key();
    
    ValueProvider<User, String> name();
    ValueProvider<User, String> gecos();
    ValueProvider<User, Date> createTime();
    ValueProvider<User, Date> lastPasswordChange();
    ValueProvider<User, Integer> passwordExpires();
    ValueProvider<User, Date> passwordInactive();
    ValueProvider<User, Date> accountExpires();
//    ValueProvider<User, Boolean> status();
//    ValueProvider<User, Integer> gid();
}
