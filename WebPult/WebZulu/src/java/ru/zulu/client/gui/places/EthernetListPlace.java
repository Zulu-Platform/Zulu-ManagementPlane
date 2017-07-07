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
public class EthernetListPlace extends InterfacePlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "ethernet-list";
    // End of variables declaration
    
    public EthernetListPlace() {
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<EthernetListPlace> {
        
   	@Override
   	public EthernetListPlace getPlace(String token) {
            return new EthernetListPlace();
   	}

   	@Override
   	public String getToken(EthernetListPlace place) {
            return "";
   	}
    }
}
