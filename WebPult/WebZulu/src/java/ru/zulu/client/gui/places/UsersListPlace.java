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
public class UsersListPlace extends UserGroupPlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "users-list";
    // End of variables declaration
    
    public UsersListPlace() {
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<UsersListPlace> {
        
   	@Override
   	public UsersListPlace getPlace(String token) {
            return new UsersListPlace();
   	}

   	@Override
   	public String getToken(UsersListPlace place) {
            return "";
   	}
    }
}
