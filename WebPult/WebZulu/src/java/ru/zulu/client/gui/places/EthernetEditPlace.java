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
public class EthernetEditPlace extends InterfacePlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "ethernet-edit";
    
    private int id;
    // End of variables declaration
    
    public EthernetEditPlace() {
        this(0);
    }
    
    public EthernetEditPlace(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<EthernetEditPlace> {
        
   	@Override
   	public EthernetEditPlace getPlace(String token) {
            try {
                return new EthernetEditPlace(Integer.valueOf(token));
            } catch (NumberFormatException ex) {
                return new EthernetEditPlace();
            }
   	}

   	@Override
   	public String getToken(EthernetEditPlace place) {
            return String.valueOf(place.getId());
   	}
    }
}
