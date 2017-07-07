/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.mapper;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.zulu.client.gui.activity.SystemsActivity;
import ru.zulu.client.gui.activity.SystemsSettingsActivity;
import ru.zulu.client.gui.places.SystemsPlace;
import ru.zulu.client.gui.places.SystemsSettingsPlace;
import ru.zulu.client.gui.places.ToolPlace;

/**
 * 
 * @author Носов А.В.
 */
public class SystemActivityMapper implements ActivityMapper {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public SystemActivityMapper() {
        super();
    }

    @Override
    public Activity getActivity(Place place) {
        
        if (place instanceof SystemsPlace) {
            return new SystemsActivity( (SystemsPlace)place );
        } if (place instanceof SystemsSettingsPlace) {
            return new SystemsSettingsActivity( (SystemsSettingsPlace) place );
        }
        return null;
    }
    
    
}
