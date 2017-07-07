/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.zulu.client.gui.activity.UserEditActivity;
import ru.zulu.client.gui.activity.UsersListActivity;
import ru.zulu.client.gui.places.UserEditPlace;
import ru.zulu.client.gui.places.UsersListPlace;

/**
 * 
 * @author Носов А.В.
 */
public class UserGroupActivityMapper implements ActivityMapper {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public UserGroupActivityMapper() {
        super();
    }

    @Override
    public Activity getActivity(Place place) {
        
        if (place instanceof UsersListPlace) {
            return new UsersListActivity( (UsersListPlace) place);
        } else if (place instanceof UserEditPlace) {
            return new UserEditActivity( (UserEditPlace) place);
        }
        
        return null;
    }
    
    
}
