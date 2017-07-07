/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.ShortField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import java.util.Date;
import ru.zulu.client.gui.types.TypeButtons;
import ru.zulu.client.gui.types.TypeMenu;
import ru.zulu.client.utils.Utils;
import libMessage.client.messages.SystemsSettings;

/**
 * Панель системные настройки.
 * @author Носов А.В.
 */
public class SystemsSettingsViewImpl extends Composite implements SystemsSettingsView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private final int WIDTH_PANEL = 390;
    private final int WIDTH_FIELDLABEL = 110;
    
    private ISystemsSettingsPresenter presenter;
    
    /** Панель для отображения системных настроек. */
    private FramedPanel panel;
    /** Основная разметка. */
    private VerticalLayoutContainer verticalLayout;
    /** Настройка сети управления. */
    private FramedPanel panelNetwork;
    /** Разметка сети управления. */
    private VerticalLayoutContainer layoutNetwork;
    /** IP адрес. */
    private ShortField textFieldIPA0;
    private ShortField textFieldIPA1;
    private ShortField textFieldIPA2;
    private ShortField textFieldIPA3;
    /** Маска. */
    private ShortField textFieldMaskA0;
    private ShortField textFieldMaskA1;
    private ShortField textFieldMaskA2;
    private ShortField textFieldMaskA3;
    /** Шлюз. */
    private ShortField textFieldGetwayA0;
    private ShortField textFieldGetwayA1;
    private ShortField textFieldGetwayA2;
    private ShortField textFieldGetwayA3;
    /** MAC. */
    private TextField textFieldMACA0;
    private TextField textFieldMACA1;
    private TextField textFieldMACA2;
    private TextField textFieldMACA3;
    private TextField textFieldMACA4;
    private TextField textFieldMACA5;
    
    private Label labelErr;
    
    /** Общие настройки устройства. */
    private FramedPanel panelOther;
    /** Разметка общих настроек. */
    private VerticalLayoutContainer layoutOther;
    private TextField textFieldHostName;
    private TextField textFieldContacts;
    private TextField textFieldLocation;
    /** Системное время. */
    private DateField dateField;
    /** Вкл/Выкл SSH. */
    private CheckBox checkBoxSSH;
    /** Вкл/Выкл Telnet. */
    private CheckBox checkBoxTelnet;
    /** Вкл/Выкл SNMP. */
    private CheckBox checkBoxSNMP;
    
    /** Применить. */
    private TextButton buttonApply;
    /** Отменить. */
//    private TextButton buttonCansel;
    // End of variables declaration
    
    /**
     * Создает новую панель системных настроек.
     */
    public SystemsSettingsViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        verticalLayout = new VerticalLayoutContainer();
        
        panelNetwork = new FramedPanel();
        layoutNetwork = new VerticalLayoutContainer();
        textFieldIPA0 = new ShortField();
        textFieldIPA1 = new ShortField();
        textFieldIPA2 = new ShortField();
        textFieldIPA3 = new ShortField();
        textFieldMaskA0 = new ShortField();
        textFieldMaskA1 = new ShortField();
        textFieldMaskA2 = new ShortField();
        textFieldMaskA3 = new ShortField();
        textFieldGetwayA0 = new ShortField();
        textFieldGetwayA1 = new ShortField();
        textFieldGetwayA2 = new ShortField();
        textFieldGetwayA3 = new ShortField();
        textFieldMACA0 = new TextField();
        textFieldMACA1 = new TextField();
        textFieldMACA2 = new TextField();
        textFieldMACA3 = new TextField();
        textFieldMACA4 = new TextField();
        textFieldMACA5 = new TextField();
        labelErr = new Label();
        panelOther = new FramedPanel();
        layoutOther = new VerticalLayoutContainer();
        textFieldHostName = new TextField();
        textFieldContacts = new TextField();
        textFieldLocation = new TextField();
        dateField = new DateField();
        buttonApply = new TextButton(TypeButtons.APPLY.getDescription());
//        buttonCansel = new TextButton(TypeButtons.CANSEL.getDescription());
        checkBoxSSH = new CheckBox();
        checkBoxTelnet = new CheckBox();
        checkBoxSNMP = new CheckBox();
        
        settingsShortField(textFieldIPA0);
        settingsShortField(textFieldIPA1);
        settingsShortField(textFieldIPA2);
        settingsShortField(textFieldIPA3);
        settingsShortField(textFieldMaskA0);
        settingsShortField(textFieldMaskA1);
        settingsShortField(textFieldMaskA2);
        settingsShortField(textFieldMaskA3);
        settingsShortField(textFieldGetwayA0);
        settingsShortField(textFieldGetwayA1);
        settingsShortField(textFieldGetwayA2);
        settingsShortField(textFieldGetwayA3);
        
        settingsTextField(textFieldMACA0);
        settingsTextField(textFieldMACA1);
        settingsTextField(textFieldMACA2);
        settingsTextField(textFieldMACA3);
        settingsTextField(textFieldMACA4);
        settingsTextField(textFieldMACA5);
        
        textFieldHostName.addValidator(new MaxLengthValidator(45));
        textFieldContacts.addValidator(new MaxLengthValidator(45));
        textFieldLocation.addValidator(new MaxLengthValidator(45));
        
        dateField.setPropertyEditor(new DateTimePropertyEditor(
                DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_FULL)));
        dateField.addValidator(new MinDateValidator(new Date()));
        dateField.addParseErrorHandler(new ParseErrorEvent.ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " не может быть распозднано.");
            }
        });
        dateField.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                String v = event.getValue() == null ? "nothing"
                    : DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_FULL).format(event.getValue());
                Info.display("Выбрано", "Выбраная дата " + v);
            }
        });
        
        HBoxLayoutContainer hlcIP = new HBoxLayoutContainer();
        hlcIP.setPadding(new Padding(3));
        hlcIP.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.BOTTOM);
        hlcIP.add(textFieldIPA0, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcIP.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcIP.add(textFieldIPA1, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcIP.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcIP.add(textFieldIPA2, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcIP.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcIP.add(textFieldIPA3, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        HBoxLayoutContainer hlcMask = new HBoxLayoutContainer();
        hlcMask.setPadding(new Padding(3));
        hlcMask.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.BOTTOM);
        hlcMask.add(textFieldMaskA0, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMask.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMask.add(textFieldMaskA1, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMask.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMask.add(textFieldMaskA2, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMask.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMask.add(textFieldMaskA3, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        HBoxLayoutContainer hlcGW = new HBoxLayoutContainer();
        hlcGW.setPadding(new Padding(3));
        hlcGW.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.BOTTOM);
        hlcGW.add(textFieldGetwayA0, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcGW.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcGW.add(textFieldGetwayA1, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcGW.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcGW.add(textFieldGetwayA2, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcGW.add(new Label("."), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcGW.add(textFieldGetwayA3, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        HBoxLayoutContainer hlcMAC = new HBoxLayoutContainer();
        hlcMAC.setPadding(new Padding(3));
        hlcMAC.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.BOTTOM);
        hlcMAC.add(textFieldMACA0, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(new Label(":"), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(textFieldMACA1, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(new Label(":"), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(textFieldMACA2, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(new Label(":"), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(textFieldMACA3, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(new Label(":"), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(textFieldMACA4, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(new Label(":"), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 2, 0, 0)));
        hlcMAC.add(textFieldMACA5, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
//        layoutNetwork.set
        layoutNetwork.add(Utils.createField(hlcIP, "IP адрес", WIDTH_FIELDLABEL), 
                          new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutNetwork.add(Utils.createField(hlcMask, "Маска", WIDTH_FIELDLABEL), 
                          new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutNetwork.add(Utils.createField(hlcGW, "Шлюз", 110), 
                          new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutNetwork.add(Utils.createField(hlcMAC, "MAC адрес", WIDTH_FIELDLABEL), 
                          new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutNetwork.add(labelErr, new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        
        panelNetwork.setHeadingText("Настройка сети управления:");
        panelNetwork.setBorders(true);
        panelNetwork.setWidth(WIDTH_PANEL);
        panelNetwork.add(layoutNetwork, new MarginData(3, 1, 3, 5));
        
        layoutOther.add(Utils.createField(textFieldHostName, "Имя", WIDTH_FIELDLABEL),
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutOther.add(Utils.createField(textFieldContacts, "Контактное лицо", WIDTH_FIELDLABEL),
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutOther.add(Utils.createField(textFieldLocation, "Местоположение", WIDTH_FIELDLABEL), 
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutOther.add(Utils.createField(dateField, "Системное время", WIDTH_FIELDLABEL), 
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutOther.add(Utils.createField(checkBoxSSH, "Служба SSH", WIDTH_FIELDLABEL), 
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutOther.add(Utils.createField(checkBoxTelnet, "Служба Telnet", WIDTH_FIELDLABEL), 
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        layoutOther.add(Utils.createField(checkBoxSNMP, "Служба SNMP", WIDTH_FIELDLABEL), 
                        new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        
        panelOther.setHeadingText("Общие настройки устройства");
        panelOther.setBorders(true);
        panelOther.setWidth(WIDTH_PANEL);
        panelOther.add(layoutOther, new MarginData(3, 1, 3, 5));
        
        buttonApply.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                validation();
            }
        });
        
        HBoxLayoutContainer hlcButtons = new HBoxLayoutContainer();
        hlcButtons.setPadding(new Padding(15));
        hlcButtons.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.TOP);
        hlcButtons.add(buttonApply, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 5, 0, 0)));
//        hlcButtons.add(buttonCansel, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        Label label = new Label();
        label.setHeight("5px");
        verticalLayout.add(panelNetwork);
        verticalLayout.add(label);
        verticalLayout.add(panelOther);
        verticalLayout.add(hlcButtons);
        
        panel.setHeadingText(TypeMenu.SystemsSettings.getDescription());
        panel.setBorders(false);
        panel.add(verticalLayout, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(ISystemsSettingsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setSystemsSettings(SystemsSettings systemsSettings) {
        if (systemsSettings == null) return;
        String[] ip = (systemsSettings.getIpv4()== null) ? new String[0]:
                systemsSettings.getIpv4().split("\\.");
        if (ip.length == 4) {
            textFieldIPA0.setValue(Utils.getOctet(ip[0]));
            textFieldIPA1.setValue(Utils.getOctet(ip[1]));
            textFieldIPA2.setValue(Utils.getOctet(ip[2]));
            textFieldIPA3.setValue(Utils.getOctet(ip[3]));
        }
        String[] mask = (systemsSettings.getMask() == null) ? new String[0] :
                systemsSettings.getMask().split("\\.");
        if (mask.length == 4) {
            textFieldMaskA0.setValue(Utils.getOctet(mask[0]));
            textFieldMaskA1.setValue(Utils.getOctet(mask[1]));
            textFieldMaskA2.setValue(Utils.getOctet(mask[2]));
            textFieldMaskA3.setValue(Utils.getOctet(mask[3]));
        }
        String[] gw = (systemsSettings.getGetway() == null) ? new String[0] :
                systemsSettings.getGetway().split("\\.");
        if (gw.length == 4) {
            textFieldGetwayA0.setValue(Utils.getOctet(gw[0]));
            textFieldGetwayA1.setValue(Utils.getOctet(gw[1]));
            textFieldGetwayA2.setValue(Utils.getOctet(gw[2]));
            textFieldGetwayA3.setValue(Utils.getOctet(gw[3]));
        }
        String[] mac = (systemsSettings.getMac() == null) ? new String[0] :
                systemsSettings.getMac().split("\\:");
        if (mac.length == 6) {
            textFieldMACA0.setValue(mac[0]);
            textFieldMACA1.setValue(mac[1]);
            textFieldMACA2.setValue(mac[2]);
            textFieldMACA3.setValue(mac[3]);
            textFieldMACA4.setValue(mac[4]);
            textFieldMACA5.setValue(mac[5]);
        }
        
        textFieldHostName.setValue(systemsSettings.getHostname());
        textFieldContacts.setValue(systemsSettings.getContact());
        textFieldLocation.setValue(systemsSettings.getLocation());
        dateField.setValue(systemsSettings.getDate());
        
        checkBoxSSH.setValue(systemsSettings.isSsh());
        checkBoxTelnet.setValue(systemsSettings.isTelnet());
        checkBoxSNMP.setValue(systemsSettings.isSnmp());
    }
    
    private void setText(TextField tf, String str) {
        //tf.setText(str);
        tf.setValue(str, true);
    }
    
    private void settingsShortField(ShortField sf) {
        sf.setWidth(35);
//        sf.addValidator(new MinNumberValidator<Short>((short) 0));
//        sf.addValidator(new MaxNumberValidator<Short>((short) 255));
    }
    
    private void settingsTextField(TextField tf) {
        tf.setWidth(35);
//        tf.addValidator(new MaxLengthValidator(2));
    }
    
    /**
     * Проверка введенных данных.
     */
    private void validation() {
        Boolean b = false;
        String ip = textFieldIPA0.getValue()+ "." +
                    textFieldIPA1.getValue() + "." +
                    textFieldIPA2.getValue() + "." +
                    textFieldIPA0.getValue();
        if (!Utils.validateIP(ip)) {
            Utils.infoMessage("Ошибка", "Не вернно введен IP адрес.");
            return;
        }
        
        String mask = textFieldMaskA0.getValue() + "." +
                    textFieldMaskA1.getValue() + "." +
                    textFieldMaskA2.getValue() + "." +
                    textFieldMaskA3.getValue();
        if (!Utils.validateIP(mask)) {
            Utils.infoMessage("Ошибка", "Не вернно введена маска.");
            return;
        }
        
        String gw = textFieldGetwayA0.getValue() + "." +
                    textFieldGetwayA1.getValue() + "." +
                    textFieldGetwayA2.getValue() + "." +
                    textFieldGetwayA3.getValue();
        if (!Utils.validateIP(gw)) {
            Utils.infoMessage("Ошибка", "Не вернно введен шлюз.");
            return;
        }
        
        String mac = textFieldMACA0.getValue() + ":" +
                     textFieldMACA1.getValue() + ":" +
                     textFieldMACA2.getValue() + ":" +
                     textFieldMACA3.getValue() + ":" +
                     textFieldMACA4.getValue() + ":" +
                     textFieldMACA5.getValue();
        if (!Utils.validateMAC(mac)) {
            Utils.infoMessage("Ошибка", "Не вернно введен MAC адрес.");
            return;
        }
        
        if (!textFieldHostName.isValid()) {
            Utils.infoMessage("Ошибка", "Длинное имя.");
            return;
        }
        if (!textFieldContacts.isValid()) {
            Utils.infoMessage("Ошибка", "Длинное контактное лицо.");
            return;
        }
        if (!textFieldLocation.isValid()) {
            Utils.infoMessage("Ошибка", "Длинное местоположение.");
            return;
        }
        if (!dateField.isValid()) {
            Utils.infoMessage("Ошибка", "Не верно задано время.");
            return;
        }
        SystemsSettings ss = new SystemsSettings();
        ss.setIpv4(ip);
        ss.setMask(mask);
        ss.setGetway(gw);
        ss.setMac(mac);
        ss.setHostname(textFieldHostName.getText());
        ss.setContact(textFieldContacts.getText());
        ss.setLocation(textFieldLocation.getText());
        ss.setDate(dateField.getValue());
        ss.setSsh(checkBoxSSH.getValue());
        ss.setTelnet(checkBoxTelnet.getValue());
        ss.setSnmp(checkBoxSNMP.getValue());
        presenter.save(ss);
    }
    
    
}
