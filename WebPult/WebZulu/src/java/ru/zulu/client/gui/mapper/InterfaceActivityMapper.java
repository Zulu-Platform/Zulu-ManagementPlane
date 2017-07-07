/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.zulu.client.gui.activity.EthernetEditActivity;
import ru.zulu.client.gui.activity.EthernetListActivity;
import ru.zulu.client.gui.places.EthernetEditPlace;
import ru.zulu.client.gui.places.EthernetListPlace;

/**
 * 
 * @author Носов А.В.
 */
public class InterfaceActivityMapper implements ActivityMapper {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public InterfaceActivityMapper() {
        super();
    }

    @Override
    public Activity getActivity(Place place) {
        
        if (place instanceof EthernetListPlace) {
            return new EthernetListActivity( (EthernetListPlace)place );
        }if (place instanceof EthernetEditPlace) {
            return new EthernetEditActivity( (EthernetEditPlace)place );
        }
        
        return null;
    }
    
    
}
