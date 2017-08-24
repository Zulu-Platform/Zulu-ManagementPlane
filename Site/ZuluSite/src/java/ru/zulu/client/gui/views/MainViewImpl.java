/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Панель .
 * @author Носов А.В.
 */
public class MainViewImpl extends Composite implements MainView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IMainPresenter presenter;
    
    /** Основная панель. */
    private SimplePanel panel;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     * @param login имя
     */
    public MainViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new SimplePanel();
//        
//              
//        panel.setHeadingText(TypeMenu.SystemsSettings.getDescription());
//        panel.setBorders(false);
//        panel.add(layoutContainer, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IMainPresenter presenter) {
        this.presenter = presenter;
    }
}
