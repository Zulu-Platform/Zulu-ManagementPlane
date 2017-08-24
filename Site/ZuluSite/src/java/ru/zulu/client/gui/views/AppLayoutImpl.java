/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import static com.google.gwt.dom.client.Style.Unit.PCT;
import static com.google.gwt.dom.client.Style.Unit.PX;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import ru.zulu.client.gui.ClientFactory;

/**
 * Реализация разметки сайта.
 * @author Носов А.В.
 */
public class AppLayoutImpl implements AppLayout {

    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private static final int HEADER_HEIGHT = 10;
    private static final int MENU_WIDHT = 20;
    
    private static final int HEADER_HEIGHT_PX = 200;
    private static final int MENU_WIDHT_PX = 200;
    
    interface AppLayoutUiBinder extends UiBinder<LayoutPanel, AppLayoutImpl> {
    }

    private static AppLayoutUiBinder binder = GWT.create(AppLayoutUiBinder.class);
    
    /** Основная разметка. */
    private final LayoutPanel contentLayoutPanel;
    
    /** Панель заголовка. */
    @UiField
    SimplePanel headerPanel;
    
    @UiField(provided = true)
    HeaderViewImpl headerView;
    
    /** Панель меню. */
    @UiField
    SimplePanel menuPanel;
    
    @UiField(provided = true)
    MenuViewImpl menuView;
    
    /** Панель авторизации. */
    @UiField
    SimplePanel mainPanel;
    // End of variables declaration
    
    /**
     * http://95.110.143.4/layoutmvp/layoutmvp.html#inbox:list
     */
    public AppLayoutImpl() {
        headerView = (HeaderViewImpl) ClientFactory.getInstance().getHeaderView();
        menuView = (MenuViewImpl) ClientFactory.getInstance().getMenuView();
        contentLayoutPanel = binder.createAndBindUi( (AppLayoutImpl)this );
        setMainLayout();
    }
    
    @Override
    public LayoutPanel getContentContainer() {
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
    public void setMainLayout() {
        contentLayoutPanel.setWidgetTopHeight(headerPanel, 0, PCT, 0, PCT);
        contentLayoutPanel.setWidgetTopHeight(menuPanel, 0, PCT, 0, PCT);
        contentLayoutPanel.setWidgetLeftWidth(menuPanel, 0, PCT, 0, PCT);
        contentLayoutPanel.setWidgetTopHeight(mainPanel, 0, PCT, 100, PCT);
        contentLayoutPanel.setWidgetLeftWidth(mainPanel, 0, PCT, 100, PCT);
        contentLayoutPanel.animate(500);
    }

    @Override
    public void setHeaderLayout() {
        contentLayoutPanel.setWidgetTopHeight(headerPanel, 0, PCT, HEADER_HEIGHT_PX, PX);
        contentLayoutPanel.setWidgetTopHeight(menuPanel, 0, PCT, 0, PCT);
        contentLayoutPanel.setWidgetLeftWidth(menuPanel, 0, PCT, 0, PCT);
        contentLayoutPanel.setWidgetTopHeight(mainPanel, HEADER_HEIGHT_PX, PX, 100, PCT);
        contentLayoutPanel.setWidgetLeftWidth(mainPanel, 0, PCT, 100, PCT);
        contentLayoutPanel.animate(500);
    }

    @Override
    public void setLeftLayout() {
        contentLayoutPanel.setWidgetTopHeight(headerPanel, 0, PCT, 0, PCT);
        contentLayoutPanel.setWidgetTopHeight(menuPanel, 0, PCT, 100, PCT);
        contentLayoutPanel.setWidgetLeftWidth(menuPanel, 0, PCT, MENU_WIDHT_PX, PX);
        contentLayoutPanel.setWidgetTopHeight(mainPanel, 0, PCT, 100, PCT);
        contentLayoutPanel.setWidgetLeftWidth(mainPanel, MENU_WIDHT_PX, PX, 100, PCT);
        contentLayoutPanel.animate(500);
    }

    @Override
    public void setHeaderLeftLayout() {
        contentLayoutPanel.setWidgetTopHeight(headerPanel, 0, PCT, HEADER_HEIGHT_PX, PX);
        contentLayoutPanel.setWidgetTopHeight(menuPanel, HEADER_HEIGHT_PX, PX, 100, PCT);
        contentLayoutPanel.setWidgetLeftWidth(menuPanel, 0, PCT, MENU_WIDHT_PX, PX);
        contentLayoutPanel.setWidgetTopHeight(mainPanel, HEADER_HEIGHT_PX, PX, 100, PCT);
        contentLayoutPanel.setWidgetLeftWidth(mainPanel, MENU_WIDHT_PX, PX, 100, PCT);
        contentLayoutPanel.animate(500);
    }
    
}
