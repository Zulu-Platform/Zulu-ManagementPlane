/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Панель .
 * @author Носов А.В.
 */
public class AAAViewImpl extends Composite implements AAAView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IAAAPresenter presenter;
    
    /** Основная панель. */
    private FramedPanel panel;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public AAAViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        
              
        panel.setHeadingText(TypeMenu.SystemsSettings.getDescription());
        panel.setBorders(false);
//        panel.add(layoutContainer, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IAAAPresenter presenter) {
        this.presenter = presenter;
    }
}
