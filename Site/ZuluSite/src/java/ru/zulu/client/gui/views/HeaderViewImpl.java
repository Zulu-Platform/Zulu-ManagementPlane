/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import ru.zulu.client.gui.types.ITypeMenu;
import ru.zulu.client.gui.SquareButton;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import ru.zulu.client.utils.images.Images;

/**
 * Панель шапки.
 * @author Носов А.В.
 */
public class HeaderViewImpl extends Composite implements HeaderView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IHeaderPresenter presenter;
    private ITypeMenu iTypeMenu = GWT.create(ITypeMenu.class);
    
    /** Основная разметка панели. */
    private VerticalPanel panelHeader;
    /** Панель выбора языка. */
    private HorizontalPanel panelLang;
//    /** Панель информации. */
//    private HorizontalPanel panelInfo;
    
    /** Панель с меню. */
    private HorizontalPanel panelMenu;
    private VerticalPanel panelMenuImage;
    /** Панель с меню. */
    private HorizontalPanel panelMenuList;
    
    /** Выпадающий список проекта. */
    private ListBox listBoxMenu;
    /** Выпадающий список ресурсов проекта. */
    private ListBox listBoxResources;
    /** Загрузка. */
    private SquareButton buttonDownload;
    /** Новости. */
    private SquareButton buttonNews;
    // End of variables declaration
    
    
    /**
     * Создает новую панель.
     */
    public HeaderViewImpl() {
        panelHeader = new VerticalPanel();
        panelLang = new HorizontalPanel();
//        panelInfo = new HorizontalPanel();
        panelMenu = new HorizontalPanel();
        panelMenuImage = new VerticalPanel();
        panelMenuList = new HorizontalPanel();
        listBoxMenu = new ListBox(false);
        listBoxResources = new ListBox(false);
        buttonDownload = new SquareButton(iTypeMenu.download(), Images.INSTANCE.menuDownload());
        buttonNews = new SquareButton(iTypeMenu.news(), Images.INSTANCE.menuResources());
        
        panelLang.setHeight("35px");
        panelLang.setStyleName("lang");
//        panelInfo.setHeight("100px");
        
        listBoxMenu.addItem(iTypeMenu.main());
        listBoxMenu.addItem(iTypeMenu.mainWho());
        listBoxMenu.addItem(iTypeMenu.mainThanks());
        listBoxMenu.addItem(iTypeMenu.mainTeam());
        listBoxMenu.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                int si = listBoxMenu.getSelectedIndex();
                switch (si) {
                    case 1:
                        //presenter.aaa(TypeMenu.MainWho);
                        break;
                    case 2:
                        //presenter.aaa(TypeMenu.MainThanks);
                        break;
                    case 3:
                        //presenter.aaa(TypeMenu.MainTeam);
                        break;
                    default:
                        //presenter.aaa(TypeMenu.Main);
                }
                listBoxMenu.setSelectedIndex(0);
            }
        });
        
        listBoxResources.addItem(iTypeMenu.resources());
        listBoxResources.addItem(iTypeMenu.resourcesAbout());
        listBoxResources.addItem(iTypeMenu.resourcesScreenshots());
        listBoxResources.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                int si = listBoxResources.getSelectedIndex();
                switch (si) {
                    case 1:
                        //presenter.aaa(TypeMenu.ResourcesAbout);
                        break;
                    case 2:
                        //presenter.aaa(TypeMenu.ResourcesScreenshots);
                        break;
                    default:
                        //presenter.aaa(TypeMenu.Resources);
                }
                listBoxResources.setSelectedIndex(0);
            }
        });
        
        buttonDownload.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                //presenter.aaa(TypeMenu.Download);
                
            }
        });
        buttonDownload.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    //presenter.aaa(TypeMenu.Download);
                }
            }
        });
        
        buttonNews.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                //presenter.aaa(TypeMenu.News);
            }
        });
        buttonNews.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    //presenter.aaa(TypeMenu.News);
                }
            }
        });
        
        Image image = new Image();
        image.setResource(Images.INSTANCE.menuMarionnet());
        
        panelMenuList.add(listBoxMenu);
        panelMenuList.add(listBoxResources);
        
        panelMenuImage.setHeight("100%");
        panelMenuImage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panelMenuImage.setStyleName("myColorFirst");
        panelMenuImage.add(image);
        panelMenuImage.add(panelMenuList);
        panelMenuImage.setCellHeight(panelMenuList, "100%");
        
        panelMenu.setStyleName("panelMenu");
        panelMenu.add(panelMenuImage);
        panelMenu.add(buttonDownload);
        panelMenu.add(buttonNews);
//        Label l1 = new Label();
//        l1.setWidth("100%");
//        l1.setStyleName("panelMenu");
//        panelMenu.add(l1);
        
        Label label = new Label("");
        label.setStyleName("lang");
        
        panelHeader.setWidth("100%");
        panelHeader.setHeight("100%");
        panelHeader.setStyleName("globalPadding");
        panelHeader.add(label);
        panelHeader.add(panelMenu);
        panelHeader.setCellHeight(panelMenu, "100%");
        
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
}
