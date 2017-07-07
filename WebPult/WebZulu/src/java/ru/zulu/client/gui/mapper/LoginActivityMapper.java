/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.zulu.client.gui.activity.LoginActivity;
import ru.zulu.client.gui.places.LoginPlace;

/**
 * 
 * @author Носов А.В.
 */
public class LoginActivityMapper implements ActivityMapper {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public LoginActivityMapper() {
        super();
    }

    @Override
    public Activity getActivity(Place place) {
        
        if (place instanceof LoginPlace) {
            return new LoginActivity((LoginPlace)place);
        }
        
        return null;
    }
    
    
}
