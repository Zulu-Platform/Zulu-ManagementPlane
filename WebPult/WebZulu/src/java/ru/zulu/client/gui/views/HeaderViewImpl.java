/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import libMessage.client.messages.User;

/**
 * Панель шапки.
 * @author Носов А.В.
 */
public class HeaderViewImpl extends Composite implements HeaderView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IHeaderPresenter presenter;
    private User user;
    
    /** Основная разметка панели. */
    private HorizontalPanel panelHeader;
    /** Правая панель. */
    private HorizontalPanel panelRight;
    /** Панель с информацией. */
    private VerticalPanel panelInfo;
    private Label labelIconZulu;
    /** Иконка пользователя. */
    private HTML htmlIconUser;
    /** Имя пользователя. */
    private Label labelLogin;
    /** IP-адрес пользователя. */
    private Label labelIP;
    /** Выход. */
    private Button buttonExit;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public HeaderViewImpl() {
        user = new User();
        
        panelHeader = new HorizontalPanel();
        panelRight = new HorizontalPanel();
        panelInfo = new VerticalPanel();
        labelIconZulu = new Label("ZULU! icon gwt");
        htmlIconUser = new HTML();
        labelLogin = new Label("Имя: " + user.getName());
        labelIP = new Label("IP-адрес: " + user.getIpv4());
        buttonExit = new Button("Выход");
        
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        sb.appendHtmlConstant("<i class=\"fa fa-user\" "
                + "style=\"font-size: 300%; color:green; padding: 0px 15px\"></i>");
        htmlIconUser.setHTML(sb.toSafeHtml());
        
        buttonExit.setStyleName("buttonExit");
        buttonExit.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenter.logout(user);
            }
        });
        buttonExit.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    presenter.logout(user);
                }
            }
        });
        
        panelInfo.add(labelLogin);
        panelInfo.add(labelIP);
        panelInfo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        panelInfo.add(buttonExit);
        
        panelRight.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        panelRight.add(htmlIconUser);
        panelRight.add(panelInfo);
        
        panelHeader.setWidth("100%");
        panelHeader.setStyleName("headerPadding");
        panelHeader.add(labelIconZulu);
        panelHeader.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        panelHeader.add(panelRight);
        
        initWidget(panelHeader);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void setPresenter(IHeaderPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setUser(User user) {
        this.user = (user == null) ? new User() : user;
        
        labelLogin.setText("Имя: " + user.getName());
        labelIP.setText("IP-адрес: " + user.getIpv4());
    }
}
