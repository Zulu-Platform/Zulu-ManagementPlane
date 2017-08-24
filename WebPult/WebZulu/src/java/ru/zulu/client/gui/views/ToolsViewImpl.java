/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import ru.zulu.client.gui.types.TypeMenu;
import ru.zulu.client.utils.Utils;

/**
 * Панель .
 * @author Носов А.В.
 */
public class ToolsViewImpl extends Composite implements ToolsView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IToolsPresenter presenter;
    private final String SETTINGS = "Сброс настроек";
    private final String SYSTEM = "Системный сброс";
    
    /** Основная панель. */
    private FramedPanel panel;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public ToolsViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        
        panel.setHeadingText(TypeMenu.Tools.getDescription());
        panel.setBorders(false);
//        panel.add(table, new MarginData(5));
        initWidget(panel);
    }
    
    private Widget initTools() {
        /** Таблица кнопок. */
        FlexTable table = new FlexTable();
        /** Перезапуск. */
        TextButton buttonReboot = new TextButton();
        /** Сброс. */
        TextButton buttonReset = new TextButton();
        /** Резервное копирование. */
        TextButton buttonBackup = new TextButton();
        /** Обновления. */
        TextButton buttonUpdate = new TextButton();
        
        buttonReboot.setHTML(Utils.getSafeHtmlAwesome(
                                        Utils.ImageLayout.LEFT, 
                                        "fa fa-power-off", "font-size: 3em;",
                                        TypeMenu.ToolsReboot.getDescription()));
        buttonReset.setHTML(Utils.getSafeHtmlAwesome(
                                        Utils.ImageLayout.LEFT, 
                                        "fa fa-dot-circle-o", "font-size: 3em;",
                                        TypeMenu.ToolsReset.getDescription()));
        buttonBackup.setHTML(Utils.getSafeHtmlAwesome(
                                        Utils.ImageLayout.LEFT, 
                                        "fa fa-cloud-download", "font-size: 3em;",
                                        TypeMenu.ToolsBackup.getDescription()));
        buttonUpdate.setHTML(Utils.getSafeHtmlAwesome(
                                        Utils.ImageLayout.LEFT, 
                                        "fa fa-refresh", "font-size: 3em;",
                                        TypeMenu.ToolsUpdate.getDescription()));
        
        buttonReboot.setStyleName("buttonTools");
        buttonReset.setStyleName("buttonTools");
        buttonBackup.setStyleName("buttonTools");
        buttonUpdate.setStyleName("buttonTools");
        
        buttonReboot.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.goTo(TypeMenu.ToolsReboot);
            }
        });
        buttonReset.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.goTo(TypeMenu.ToolsReset);
            }
        });
        buttonBackup.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.goTo(TypeMenu.ToolsBackup);
            }
        });
        buttonUpdate.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.goTo(TypeMenu.ToolsUpdate);
            }
        });
        
        table.setWidget(0, 0, buttonReboot);
        table.setWidget(0, 1, buttonReset);
        table.setWidget(1, 0, buttonBackup);
        table.setWidget(1, 1, buttonUpdate);
        
        return (Widget) table;
    }
    
    private Widget initToolsBackup() {
        TextButton fileDownLoad = new TextButton("Файл");
        FileUploadField fileUploadField = new FileUploadField();
        TextButton buttonSubmit = new TextButton("Применить");
        
        HBoxLayoutContainer hlc = new HBoxLayoutContainer();
        hlc.setPadding(new Padding(5));
        hlc.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.TOP);
        hlc.add(buttonSubmit, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.add(new FieldLabel(fileDownLoad, "Сохранить файл настроек"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(fileUploadField, "Загрузить файл настроек"), new VerticalLayoutData(1, 100));
        vlc.add(hlc, new VerticalLayoutData(1, -1));
        
        vlc.setWidth(400);
        
        return (Widget) vlc;
    }
    
    private Widget initToolsReboot() {
        /** Внутреняя панель. */
        ContentPanel contentPanel = new ContentPanel();
        /** Надпись. */
        HTML htmlTxt = new HTML();
        /** Перезапуск. */
        TextButton buttonReboot = new TextButton(TypeMenu.ToolsReboot.getDescription());
    
        htmlTxt.setHTML("Вы действительно хотите продолжить перезапуск системы?<br> "
                + "Если <b>Да</b>, то нажмите кнопку <b><i>«Перезапуск».</i></b>");
        
        buttonReboot.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.reboot();
            }
        });
        
        contentPanel.setHeaderVisible(false);
        contentPanel.setWidth(500);
        contentPanel.add(htmlTxt);
        contentPanel.addButton(buttonReboot);
        
        return (Widget) contentPanel;
    }
    
    private Widget initToolsReset() {
        /** Разметка оконо. */
        VerticalPanel verticalPanel = new VerticalPanel();
        /** Сброс настроек. */
        ContentPanel contentPanelSettings = new ContentPanel();
        /** Надпись сброса настроек. */
        HTML htmlTxtSettings = new HTML();
        /** Перезапуск. */
        TextButton buttonRebootSettings = new TextButton(SETTINGS);
        /** Сброс настроек. */
        ContentPanel contentPanelSystem = new ContentPanel();
        /** Надпись системного сброса. */
        HTML htmlTxtSystem = new HTML();
        /** Системный сброс. */
        TextButton buttonRebootSystem = new TextButton(SYSTEM);
    
        htmlTxtSettings.setHTML("Все настройки будут сброшены, за исключением IP.<br>"
                + "Вы уверены, что хотите продолжить сброс настроек?<br>"
                + "Если <b>Да</b>, то нажмите кнопку <b><i>«"+SETTINGS+"».</i></b>");
        
        buttonRebootSettings.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.resetSettings();
            }
        });
        
        contentPanelSettings.setHeadingText(SETTINGS);
        contentPanelSettings.setWidth(500);
        contentPanelSettings.add(htmlTxtSettings);
        contentPanelSettings.addButton(buttonRebootSettings);
        
        htmlTxtSystem.setHTML("<b>ВНИМАНИЕ!</b> Коммутатор будет сброшен в заводские настройки с последующей перезагрузкой. <br>"
                + "Вы уверены, что Вы хотите продолжить системный сброс? <br>"
                + "Если <b>Да</b>, то нажмите кнопку <b><i>«"+SYSTEM+"».</i></b>");
        
        buttonRebootSystem.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                presenter.resetSystem();
            }
        });
        
        contentPanelSystem.setHeadingHtml(SYSTEM);
        contentPanelSystem.setWidth(500);
        contentPanelSystem.add(htmlTxtSystem);
        contentPanelSystem.addButton(buttonRebootSystem);
        
        verticalPanel.add(contentPanelSettings);
        verticalPanel.add(contentPanelSystem);
        
        return (Widget) verticalPanel;
    }
    
    private Widget initToolsUpdate() {
        TextButton fileDownLoad = new TextButton("Файл");
        FileUploadField fileUploadField = new FileUploadField();
        TextButton buttonSubmit = new TextButton("Обновить");
        
        HBoxLayoutContainer hlc = new HBoxLayoutContainer();
        hlc.setPadding(new Padding(5));
        hlc.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.TOP);
        hlc.add(buttonSubmit, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.add(new FieldLabel(fileDownLoad, "Резервное копирование ПО"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(fileUploadField, "Обновление ПО"), new VerticalLayoutData(1, 100));
        vlc.add(hlc, new VerticalLayoutData(1, -1));
        
        vlc.setWidth(400);
        
        return (Widget) vlc;
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IToolsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setTypeMenu(TypeMenu typeMenu) {
        for (int i=0; i<panel.getWidgetCount(); i++)
            panel.remove(i);
        
        switch (typeMenu) {
            case ToolsBackup:
                panel.setHeadingText(TypeMenu.ToolsBackup.getDescription());
                panel.add(initToolsBackup(), new MarginData(5));
                break;
            case ToolsReboot:
                panel.setHeadingText(TypeMenu.ToolsReboot.getDescription());
                panel.add(initToolsReboot(), new MarginData(5));
                break;
            case ToolsReset:
                panel.setHeadingText(TypeMenu.ToolsReset.getDescription());
                panel.add(initToolsReset(), new MarginData(5));
                break;
            case ToolsUpdate:
                panel.setHeadingText(TypeMenu.ToolsUpdate.getDescription());
                panel.add(initToolsUpdate(), new MarginData(5));
                break;
            default:
                panel.setHeadingText(TypeMenu.Tools.getDescription());
                panel.add(initTools(), new MarginData(5));
        }
    }
}
