/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.places;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Текущее состояние панели .
 * @author Носов А.В.
 */
public class ToolsPlace extends ToolPlace {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    public static final String VIEW_HISTORY_TOKEN = "tools";
    
    private TypeMenu typeMenu;
    // End of variables declaration
    
    public ToolsPlace() {
        this(TypeMenu.Tools);
    }
    
    public ToolsPlace(TypeMenu typeMenu) {
        this.typeMenu = typeMenu;
    }
    
    public TypeMenu getTypeMenu() {
        return typeMenu;
    }
    
    private String getReturnToken() {
        switch (typeMenu) {
            case ToolsBackup:
                return TypeMenu.ToolsBackup.getName();
            case ToolsReboot:
                return TypeMenu.ToolsReboot.getName();
            case ToolsReset:
                return TypeMenu.ToolsReset.getName();
            case ToolsUpdate:
                return TypeMenu.ToolsUpdate.getName();
            default:
                return "";
        }
    } 
    
    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<ToolsPlace> {
        
   	@Override
   	public ToolsPlace getPlace(String token) {
            if (token.equals(TypeMenu.ToolsBackup.getName())) return new ToolsPlace(TypeMenu.ToolsBackup);
            if (token.equals(TypeMenu.ToolsReboot.getName())) return new ToolsPlace(TypeMenu.ToolsReboot);
            if (token.equals(TypeMenu.ToolsReset.getName())) return new ToolsPlace(TypeMenu.ToolsReset);
            if (token.equals(TypeMenu.ToolsUpdate.getName())) return new ToolsPlace(TypeMenu.ToolsUpdate);

            return new ToolsPlace();
   	}

   	@Override
   	public String getToken(ToolsPlace place) {
            return place.getReturnToken();
   	}
    }
}
