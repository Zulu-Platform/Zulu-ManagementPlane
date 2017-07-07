/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
 * Разметка сайта.
 * @author Носов А.В.
 */
public interface AppLayout {
    
    /**
     * Возвращает основную разметку приложения.
     * @return основная разметка
     */
    public DockLayoutPanel getContentContainer();
    
    /**
     * Возвращает основной контейнер.
     * @return основной
     */
    public AcceptsOneWidget getMainContainer();
    
//    /**
//     * Возвращает контейнер шапки.
//     * @return шапка
//     */
//    public AcceptsOneWidget getHeaderContainer();
//
//    /**
//     * Возвращает контейнер с меню.
//     * @return меню
//     */
//    public AcceptsOneWidget getMenuContainer();

    /**
     * Устанавливает разметку для авторизации.
     */
    public void setLoginLayout();
    
    /**
     * Устанавливает разметку после авторизации.
     */
    public void setMainLayout();
}
