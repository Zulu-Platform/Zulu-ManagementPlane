/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.places;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Текущее состояние панели логин.
 * @author Носов А.В.
 */
public class LoginPlace extends Place {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "login";
    
    private String login;
    // End of variables declaration
    
    public LoginPlace() {
        this(null);
    }
    
    public LoginPlace(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return login;
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<LoginPlace> {
        
   	@Override
   	public LoginPlace getPlace(String token) {
            return new LoginPlace(token);
   	}

   	@Override
   	public String getToken(LoginPlace place) {
            return (place.getLogin() == null) ? "" : place.getLogin();
   	}
    }
}
