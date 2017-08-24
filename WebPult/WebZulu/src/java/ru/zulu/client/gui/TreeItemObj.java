/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui;

import ru.zulu.client.gui.types.TypeMenu;

/**
 * Объект для управления объектами дерева.
 * @author Носов А.В.
 */
public class TreeItemObj {
    
    // Variables declaration
    /** Тип меню. */
    private TypeMenu typeMenu;
    /** Активность. */
    private boolean enable;
    // End of variables declaration

    public TreeItemObj(TypeMenu typeMenu, boolean enable) {
        this.typeMenu = typeMenu;
        this.enable = enable;
    }

    public TypeMenu getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(TypeMenu typeMenu) {
        this.typeMenu = typeMenu;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    
    
}
