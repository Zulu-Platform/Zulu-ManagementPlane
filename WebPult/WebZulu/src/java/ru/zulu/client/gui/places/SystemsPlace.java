/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.places;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Текущее состояние панели .
 * @author Носов А.В.
 */
public class SystemsPlace extends SystemPlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    
    public String test;
    
    //private static final String name = TypeMenu.Systems.getName();
    public static final String VIEW_HISTORY_TOKEN = "systems";
    // End of variables declaration
    
    public SystemsPlace() {
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<SystemsPlace> {
        
   	@Override
   	public SystemsPlace getPlace(String token) {
            return new SystemsPlace();
   	}

   	@Override
   	public String getToken(SystemsPlace place) {
            //Log.debug(CLASS_NAME, place.);
            return "";
   	}
    }
}
