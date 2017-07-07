/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.MarginData;
import ru.zulu.client.gui.types.TypeMenu;
import libMessage.client.messages.SystemsSettings;

/**
 * Панель настроек.
 * @author Носов А.В.
 */
public class SystemsViewImpl extends Composite implements SystemsView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private ISystemsPresenter presenter;
    
    /** Основная панель. */
    private FramedPanel panel;
    /** Основная разметка. */
    private FlexTable flexTable;
    
    /** Панель информации. */
    private FramedPanel panelInfo;
    /** Разметка панели информации. */
    private FlexTable layoutInfo;
    
    /** Имя. */
    private Label labelHostName;
    /** Идентификатор продукта. */
    private Label labelProductID;
    /** IP  адрес. */
    private Label labelIP;
    /** MAC адрес. */
    private Label labelMAC;
    /** Серийный номер. */
    private Label labelSerialNumber;
    /** Версия ПО. */
    private Label labelSoftware;
    /** Контакт. */
    private Label labelContact;
    /** Местонахждение. */
    private Label labelLocation;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public SystemsViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        flexTable = new FlexTable();
        
        /** Панель информации. */
        panelInfo = new FramedPanel();
        layoutInfo = new FlexTable();
        labelHostName = new Label();
        labelProductID = new Label();
        labelIP = new Label();
        labelMAC = new Label();
        labelSerialNumber = new Label();
        labelSoftware = new Label();
        labelContact = new Label();
        labelLocation = new Label();
        
        layoutInfo.setWidget(0, 0, new Label("Название:"));
        layoutInfo.setWidget(1, 0, new Label("Идентификатор:"));
        layoutInfo.setWidget(2, 0, new Label("IP адрес:"));
        layoutInfo.setWidget(3, 0, new Label("MAC адрес:"));
        layoutInfo.setWidget(4, 0, new Label("Серийный номер:"));
        layoutInfo.setWidget(5, 0, new Label("Версия ПО:"));
        layoutInfo.setWidget(6, 0, new Label("Контактное лицо:"));
        layoutInfo.setWidget(7, 0, new Label("Местоположение:"));
        layoutInfo.setWidget(0, 1, labelHostName);
        layoutInfo.setWidget(1, 1, labelProductID);
        layoutInfo.setWidget(2, 1, labelIP);
        layoutInfo.setWidget(3, 1, labelMAC);
        layoutInfo.setWidget(4, 1, labelSerialNumber);
        layoutInfo.setWidget(5, 1, labelSoftware);
        layoutInfo.setWidget(6, 1, labelContact);
        layoutInfo.setWidget(7, 1, labelLocation);
        FlexTable.FlexCellFormatter cellFlexInfo = layoutInfo.getFlexCellFormatter();
        for (int row=0; row<layoutInfo.getRowCount(); row++) {
            cellFlexInfo.setWidth(row, 0, "120px");
            cellFlexInfo.setWidth(row, 1, "278px");
            int colC = layoutInfo.getCellCount(row);
            for (int col=0; col<colC; col++) {
                cellFlexInfo.setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_LEFT);
                cellFlexInfo.setVerticalAlignment(row, col, HasVerticalAlignment.ALIGN_TOP);
            }
        }
        
        panelInfo.setHeadingText("Общая информация");
        panelInfo.setWidth(400);
        panelInfo.setBorders(true);
        panelInfo.add(layoutInfo, new MarginData(3));
        
        flexTable.setWidget(0, 0, panelInfo);
        FlexTable.FlexCellFormatter cellFlex = flexTable.getFlexCellFormatter();
        cellFlex.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        cellFlex.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setWidth(0, 0, "400px");
//        cellFlex.setColSpan(0, 0, 2);
        
        panel.setHeadingText(TypeMenu.Systems.getDescription());
        panel.setBorders(false);
        panel.add(flexTable, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(ISystemsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setSystemsSettings(SystemsSettings systemsSettings) {
        if (systemsSettings == null) return;
        labelHostName.setText(systemsSettings.getHostname());
        labelProductID.setText(systemsSettings.getProductID());
        labelIP.setText(systemsSettings.getIpv4());
        labelMAC.setText(systemsSettings.getMac());
        labelSerialNumber.setText(systemsSettings.getSerialnumber());
        labelSoftware.setText(systemsSettings.getSoftware());
        labelContact.setText(systemsSettings.getContact());
        labelLocation.setText(systemsSettings.getLocation());
    }
    
    private HBoxLayoutContainer getColumn(String name, Widget w) {
        BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        BoxLayoutData flex2 = new BoxLayoutData(new Margins(0));
        flex2.setFlex(2);
        
        HBoxLayoutContainer hbl = new HBoxLayoutContainer();
        hbl.setPadding(new Padding(2));
        hbl.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        hbl.setPack(BoxLayoutPack.START);
        Label label = new Label(name);
        label.setWidth("130px");
        hbl.add(label, flex);
        hbl.add(w, flex2);
        return hbl;
    }
}
