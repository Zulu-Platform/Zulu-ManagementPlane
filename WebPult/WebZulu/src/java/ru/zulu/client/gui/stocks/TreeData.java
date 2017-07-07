/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.stocks;

import ru.zulu.client.gui.types.TypeMenu;

/**
 * Данные дерева.
 * @author Носов А.В.
 */
public class TreeData {
    
    // Variables declaration
    /** Идентификатор ноды. */
    private Integer id;
    /** Название. */
    private String name;
    /** Тип меню. */
    private TypeMenu typeMenu;
    /** Активность. */
    private boolean enable;
    // End of variables declaration

    public TreeData(TypeMenu typeMenu, boolean enable) {
        super();
        this.id = typeMenu.getCode();
        this.name = typeMenu.getDescription();
        this.typeMenu = typeMenu;
        this.enable = enable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
