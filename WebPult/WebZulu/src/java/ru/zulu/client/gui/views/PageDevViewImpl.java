/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.MarginData;

/**
 * Панель пользователей и групп.
 * @author Носов А.В.
 */
public class PageDevViewImpl extends Composite implements PageDevView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IPageDevPresenter presenter;
    private FramedPanel panel;
    private HTML html;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     * @param selectionPanel
     * @param layoutCard
     */
    public PageDevViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        html = new HTML("Эта страница находится в разработке.");
        panel.setHeadingText("Страница в разработке.");
        panel.setBorders(false);
        panel.add(html, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IPageDevPresenter presenter) {
        this.presenter = presenter;
    }
}
