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
public class MainPlace extends MainMenuPlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "main";
    // End of variables declaration
    
    public MainPlace() {
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<MainPlace> {
        
   	@Override
   	public MainPlace getPlace(String token) {
            return new MainPlace();
   	}

   	@Override
   	public String getToken(MainPlace place) {
            return "";
   	}
    }
}
