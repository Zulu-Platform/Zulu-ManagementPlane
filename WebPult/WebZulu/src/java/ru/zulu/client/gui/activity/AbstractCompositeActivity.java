package ru.zulu.client.gui.activity;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.History;
import ru.zulu.client.gui.places.EthernetListPlace;
import ru.zulu.client.gui.places.PageDevPlace;
import ru.zulu.client.gui.places.SystemsPlace;
import ru.zulu.client.gui.places.SystemsSettingsPlace;
import ru.zulu.client.gui.places.ToolsPlace;
import ru.zulu.client.gui.places.UsersGroupsPlace;
import ru.zulu.client.gui.places.UsersListPlace;
import ru.zulu.client.gui.types.TypeMenu;
import ru.zulu.client.utils.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Носов А.В.
 */
public abstract class AbstractCompositeActivity extends AbstractActivity {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    // End of variables declaration
    
    /**
     * Преход на выбранную страницу.
     * @param tm тип меню
     */
    public void goTo(TypeMenu tm) {
        Utils.goTo(tm);
    }
}
