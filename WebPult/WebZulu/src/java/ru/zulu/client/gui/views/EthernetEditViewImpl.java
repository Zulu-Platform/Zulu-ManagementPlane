/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import ru.zulu.client.gui.stocks.IDuplexData;
import ru.zulu.client.gui.stocks.ISpeedData;
import ru.zulu.client.gui.types.TypeButtons;
import libMessage.client.types.TypeDuplex;
import ru.zulu.client.gui.types.TypeMenu;
import libMessage.client.types.TypeSpeed;
import libMessage.client.types.TypeMessage;
import libMessage.client.messages.Ethernet;

/**
 * Панель настроек Ethernet порта.
 * @author Носов А.В.
 */
public class EthernetEditViewImpl extends Composite implements EthernetEditView  {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IEthernetEditPresenter presenter;
    
    
    /** Панель для редактирования групп. */
    private FramedPanel panel;
    /** Применить. */
    private TextButton buttonApply;
    /** Отменить. */
    private TextButton buttonCansel;
    
    /** Название Ethernet интерфейса. */
    private Label labelName;
    
    /** Статус. */ 
    private CheckBox checkBoxStatus;
    /** Список скоростей. */
    private ListStore<TypeSpeed> listSpeeds;
    /** Скорость. */ 
    private ComboBox<TypeSpeed> comboBoxSpeed;
    /** Список дуплекса. */
    private ListStore<TypeDuplex> listDuplex;
    /** Дуплекс. */
    private ComboBox<TypeDuplex> comboBoxDuplex;
    /** Защита. */
    private CheckBox checkBoxSecurity;
    /** Flow control. */
    private CheckBox checkBoxFlowControl;
    /** Комментарий. */
    private TextArea textAreaComment;
    
    private Ethernet ethernet;
    // End of variables declaration
    
    /**
     * Создает новую панель настроек Ethernet порта.
     */
    public EthernetEditViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        buttonApply = new TextButton(TypeButtons.APPLY.getDescription());
        buttonCansel = new TextButton(TypeButtons.CANSEL.getDescription());
        
        labelName = new Label();
        checkBoxStatus = new CheckBox();
        final ISpeedData propSpeed = GWT.create(ISpeedData.class);
        listSpeeds = new ListStore<TypeSpeed>(propSpeed.key());
        comboBoxSpeed = new ComboBox<TypeSpeed>(listSpeeds, propSpeed.name());
        checkBoxStatus = new CheckBox();
        final IDuplexData propDuplex = GWT.create(IDuplexData.class);
        listDuplex = new ListStore<TypeDuplex>(propDuplex.key());
        comboBoxDuplex = new ComboBox<TypeDuplex>(listDuplex, propDuplex.name());
        checkBoxSecurity = new CheckBox();
        checkBoxFlowControl = new CheckBox();
        textAreaComment = new TextArea();
        
        buttonApply.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                validData();
            }
        });
        buttonCansel.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                presenter.cansel();
            }
        });
        
        panel.setBodyStyle("background: none; padding: 15px");
        comboBoxSpeed.setAllowBlank(true);
        comboBoxSpeed.setForceSelection(true);
        comboBoxSpeed.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        comboBoxDuplex.setAllowBlank(true);
        comboBoxDuplex.setForceSelection(true);
        comboBoxDuplex.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        listSpeeds.replaceAll(TypeSpeed.AUTO.getSpeedList());
        listDuplex.replaceAll(TypeDuplex.AUTO.getDuplexList());
        comboBoxSpeed.setValue(TypeSpeed.AUTO);
        comboBoxDuplex.setValue(TypeDuplex.AUTO);
        textAreaComment.addValidator(new MaxLengthValidator(255));
        
        HBoxLayoutContainer hlc = new HBoxLayoutContainer();
        hlc.setPadding(new Padding(5));
        hlc.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
        hlc.add(buttonApply, new BoxLayoutData(new Margins(0, 5, 0, 0)));
        hlc.add(buttonCansel, new BoxLayoutData(new Margins(0)));
        
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.add(new FieldLabel(labelName, "Наименование"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(checkBoxStatus, "Статус"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(comboBoxSpeed, "Скороть"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(comboBoxDuplex, "Дуплекс"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(checkBoxSecurity, "Защита"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(checkBoxFlowControl, "Flow control"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(textAreaComment, "Комментарий"), new VerticalLayoutData(1, 100));
        vlc.add(hlc, new VerticalLayoutData(1, -1));
        
        vlc.setWidth(400);
        
        panel.setHeadingText(TypeMenu.InterfaceEthernetEdit.getDescription());
        panel.setBorders(false);
        panel.add(vlc, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IEthernetEditPresenter presenter) {
        this.presenter = presenter;
    }
    
    /**
     * Проверка введенных данных.
     */
    private void validData() {
        ethernet.setStatus(checkBoxStatus.getValue());
        ethernet.setSecurity(checkBoxSecurity.getValue());
        ethernet.setFlowControl(checkBoxFlowControl.getValue());
        ethernet.setComment(textAreaComment.getValue());
        ethernet.setSpeed(comboBoxSpeed.getValue().name());
        ethernet.setDuplex(comboBoxDuplex.getValue().name());
        
//        if (ethernet.getId() < 1) ethernet.setTypeMessage(TypeMessage.EthernetAdd);
//        else ethernet.setTypeMessage(TypeMessage.EthernetEdit);
        
        if (ethernet.getId() < 1) presenter.cansel();
        
        ethernet.setTypeMessage(TypeMessage.EthernetEdit);
        presenter.save(ethernet);
    }

    @Override
    public void setEthernet(Ethernet ethernet) {
        if (ethernet == null) return;
        if (ethernet.getId() < 1) panel.setHeadingText(TypeMenu.InterfaceEthernetAdd.getDescription());
        else panel.setHeadingText(TypeMenu.InterfaceEthernetEdit.getDescription() + " ");
        
        this.ethernet = ethernet;
        labelName.setText(ethernet.getName() + " №" + ethernet.getNumber());
        checkBoxStatus.setValue(ethernet.getStatus());
        checkBoxSecurity.setValue(ethernet.getSecurity());
        checkBoxFlowControl.setValue(ethernet.getFlowControl());
        textAreaComment.setValue(ethernet.getComment());
        String speed = ethernet.getSpeed();
        String duplex = ethernet.getDuplex();
        TypeSpeed ts = (speed == null) ? TypeSpeed.AUTO : TypeSpeed.valueOf(speed);
        TypeDuplex ds = (duplex == null) ? TypeDuplex.AUTO : TypeDuplex.valueOf(duplex);
        comboBoxSpeed.setValue(ts);
        comboBoxDuplex.setValue(ds);
    }
}
