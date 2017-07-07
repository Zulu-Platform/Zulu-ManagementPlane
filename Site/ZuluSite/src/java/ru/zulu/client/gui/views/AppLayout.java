/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * Разметка сайта.
 * @author Носов А.В.
 */
public interface AppLayout {
    
    /**
     * Возвращает основную разметку приложения.
     * @return основная разметка
     */
    public LayoutPanel getContentContainer();
    
    /**
     * Возвращает основной контейнер.
     * @return основной
     */
    public AcceptsOneWidget getMainContainer();
    
    /**
     * Устанавливает разметку с цетром.
     */
    public void setMainLayout();
    
    /**
     * Устанавливает разметку с шапкой.
     */
    public void setHeaderLayout();
    
    /**
     * Устанавливает разметку с левой вертикальной панелью.
     */
    public void setLeftLayout();
    
    /**
     * Устанавливает разметку с шапкой и
     * левой вертикальной панелью.
     */
    public void setHeaderLeftLayout();
}
