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
public class UserEditPlace extends UserGroupPlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "user-edit";
    
    private String name;
    // End of variables declaration
    
    public UserEditPlace() {
        this("");
    }
    
    public UserEditPlace(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<UserEditPlace> {
        
   	@Override
   	public UserEditPlace getPlace(String token) {
            return new UserEditPlace(token);
   	}

   	@Override
   	public String getToken(UserEditPlace place) {
            return String.valueOf(place.getName());
   	}
    }
}
