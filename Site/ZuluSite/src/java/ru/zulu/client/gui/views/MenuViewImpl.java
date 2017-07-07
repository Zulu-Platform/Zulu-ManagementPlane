/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import ru.zulu.client.gui.stocks.TreeData;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Панель настройки правил.
 * @author Носов А.В.
 */
public class MenuViewImpl extends Composite implements MenuView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private IMenuPresenter presenter;
    
    /** Дерево меню. */
    private Tree treeMenu;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public MenuViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        treeMenu = new Tree();
        
        
        creatTreeMenu();
        
        initWidget(treeMenu);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IMenuPresenter presenter) {
        this.presenter = presenter;
    }
    
    /**
     * Создает меню дерево.
     */
    private void creatTreeMenu() {
        treeMenu.clear();
        
        TreeItem tiSystem = getTreeItem(TypeMenu.Main, true);
        TreeItem tiUsersAuth = getTreeItem(TypeMenu.ResourcesAbout, true);
        TreeItem tiUsersGroups = getTreeItem(TypeMenu.Resources, true);
        
        tiUsersGroups.addItem(tiUsersAuth);
        
        treeMenu.addItem(tiSystem);
        treeMenu.addItem(tiUsersGroups);
        
        treeMenu.addSelectionHandler(new SelectionHandler<TreeItem>() {
            @Override
            public void onSelection(SelectionEvent<TreeItem> event) {
                Object obj = event.getSelectedItem().getUserObject();
                if ( (obj == null) || !(obj instanceof TreeData) ) return;
                TreeData td = (TreeData) obj;
                presenter.selectionEvent(td.getTypeMenu());
            }
        });
    }
    
    /**
     * Возвращает элемент меню.
     * @param tm тип
     * @param visible видимость
     * @return элемент меню
     */
    private TreeItem getTreeItem(TypeMenu tm, boolean visible) {
        TreeItem ti = new TreeItem();
        ti.setWidget(new Anchor(tm.getDescription()));
        ti.setUserObject(new TreeData(tm, visible));
        ti.setVisible(visible);
        return ti;
    }     
}
