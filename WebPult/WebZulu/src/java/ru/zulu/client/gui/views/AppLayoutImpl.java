/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import ru.zulu.client.gui.ClientFactory;

/**
 * Реализация разметки сайта.
 * @author Носов А.В.
 */
public final class AppLayoutImpl implements AppLayout {

    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    interface AppLayoutUiBinder extends UiBinder<DockLayoutPanel, AppLayoutImpl> {
    }

    private static final AppLayoutUiBinder BINDER = GWT.create(AppLayoutUiBinder.class);
    
    /** Основная разметка. */
    private final DockLayoutPanel contentLayoutPanel;
    
    /** Панель заголовка. */
    @UiField(provided = true)
    HeaderViewImpl headerView;
    
    /** Панель меню. */
    @UiField(provided = true)
    MenuViewImpl menuView;
    
    /** Цетральная разметка. */
    @UiField
    SplitLayoutPanel splitLayoutPanel;
    
    /** Цетральная панель. */
    @UiField
    SimplePanel mainPanel;
    // End of variables declaration
    
    public AppLayoutImpl() {
        headerView = (HeaderViewImpl) ClientFactory.getInstance().getHeaderView();
        menuView = (MenuViewImpl) ClientFactory.getInstance().getMenuView();
        contentLayoutPanel = BINDER.createAndBindUi( (AppLayoutImpl)this );
        setLoginLayout();
    }
    
    @Override
    public DockLayoutPanel getContentContainer() {
        return contentLayoutPanel;
    }

    @Override
    public AcceptsOneWidget getMainContainer() {
        return new AcceptsOneWidget() {
        @Override
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                mainPanel.setWidget(widget);
            }
        };
    }

    @Override
    public void setLoginLayout() {
        contentLayoutPanel.setWidgetHidden(headerView, true);
        splitLayoutPanel.setWidgetHidden(menuView, true);
    }

    @Override
    public void setMainLayout() {
        contentLayoutPanel.setWidgetHidden(headerView, false);
        splitLayoutPanel.setWidgetHidden(menuView, false);
    }
    
}
