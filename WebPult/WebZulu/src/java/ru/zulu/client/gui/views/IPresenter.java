/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import ru.zulu.client.gui.types.TypeMenu;

/**
 * 
 * @author Носов А.В.
 */
public interface IPresenter {
        
    /**
     * Преход на выбранную страницу.
     * @param tm тип меню
     */
    public void goTo(TypeMenu tm);
}
