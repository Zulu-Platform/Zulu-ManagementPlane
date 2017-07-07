/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import ru.zulu.client.gui.stocks.IEthernetsData;
import ru.zulu.client.gui.types.TypeButtons;
import libMessage.client.types.TypeDuplex;
import ru.zulu.client.gui.types.TypeMenu;
import libMessage.client.types.TypeSpeed;
import libMessage.client.messages.Ethernet;

/**
 * Панель отображения Ethernet интерфейсов.
 * @author Носов А.В.
 */
public class EthernetListViewImpl extends Composite implements EthernetListView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IEthernetListPresenter presenter;
    
    /** Основная панель. */
    private FramedPanel panel;
    /** Вертикальный контейнер. */
    private VerticalLayoutContainer layoutContainer;
    /** Список ethernet. */
    private Grid<Ethernet> tableEthernets;
    
    /** Список ethernets. */
    private ListStore<Ethernet> listEthernets;
    /** Идентфикатор. */
    private IdentityValueProvider<Ethernet> identity;
    /** Модель выделнных объектов. */
//    private CheckBoxSelectionModel<Ethernet> selectionModel;
    
    /** Контейнер для кнопок. */
    private ToolBar toolBar;
    /** Отменить. */
    private TextButton buttonCansel;
    /** Применить. */
    private TextButton buttonApply;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public EthernetListViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        final IEthernetsData propEthernets = GWT.create(IEthernetsData.class);
        identity = new IdentityValueProvider<>();
//        selectionModel = new CheckBoxSelectionModel<Ethernet>(identity);
//        selectionModel.setSelectionMode(Style.SelectionMode.SINGLE);
        
        layoutContainer = new VerticalLayoutContainer();
        toolBar = new ToolBar();
        buttonCansel = new TextButton(TypeButtons.CANSEL.getDescription());
        buttonApply = new TextButton(TypeButtons.APPLY.getDescription());
        listEthernets = new ListStore<>(propEthernets.key());
        
        buttonCansel.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                listEthernets.rejectChanges();
            }
        });
        buttonApply.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                Collection<Store<Ethernet>.Record> list = listEthernets.getModifiedRecords();
                if (list.size() < 1) return;
                List<Ethernet> ethernets = new ArrayList<>();
                Iterator itr = list.iterator();
                while(itr.hasNext()) {
                    Object objR = itr.next();
                    if (objR instanceof Record) {
                        Record record = (Record)objR;
                        Object objEth = record.getModel();
                        if (objEth instanceof Ethernet) {
                            Ethernet eth = (Ethernet) objEth;
                            ethernets.add(eth);
                        }
                    }
                }
                presenter.updateEthernet(ethernets);
                listEthernets.commitChanges();
            }
        });
        
        toolBar.add(buttonApply);
        toolBar.add(buttonCansel);
        
        ColumnConfig<Ethernet, Short> colNumber = new ColumnConfig<>(propEthernets.number(), 30);
        ColumnConfig<Ethernet, String> colName = new ColumnConfig<>(propEthernets.name(), 100);
        ColumnConfig<Ethernet, Boolean> colStatus = new ColumnConfig<>(propEthernets.status(), 100);
        ColumnConfig<Ethernet, String> colSpeed = new ColumnConfig<>(propEthernets.speedDescription(), 100);
        ColumnConfig<Ethernet, String> colDuplex = new ColumnConfig<>(propEthernets.duplexDescription(), 100);
        ColumnConfig<Ethernet, Boolean> colSecurity = new ColumnConfig<>(propEthernets.security(), 100);
        ColumnConfig<Ethernet, Boolean> colFlowControl = new ColumnConfig<>(propEthernets.flowControl(), 100);
        ColumnConfig<Ethernet, String> colComment = new ColumnConfig<>(propEthernets.comment(), 300);
        colNumber.setHeader("№");
        colName.setHeader("Имя");
        colStatus.setHeader("Статус");
        colSpeed.setHeader("Скорость");
        colDuplex.setHeader("Дуплекс");
        colSecurity.setHeader("Защита");
        colFlowControl.setHeader("Flow Control");
        colComment.setHeader("Комментарий");
        
        List<ColumnConfig<Ethernet, ?>> columns = new ArrayList<>();
        columns.add(colNumber);
        columns.add(colName);
        columns.add(colStatus);
        columns.add(colSpeed);
        columns.add(colDuplex);
        columns.add(colSecurity);
        columns.add(colFlowControl);
        columns.add(colComment);
        
        final ColumnModel<Ethernet> cm = new ColumnModel<>(columns);
        
        tableEthernets = new Grid<>(listEthernets, cm);
        tableEthernets.setHeight("600px");
//        tableEthernets.setSelectionModel(selectionModel);
//        tableEthernets.getView().setAutoExpandColumn(colComment);
        tableEthernets.setBorders(false);
        tableEthernets.getView().setStripeRows(true);
        tableEthernets.getView().setColumnLines(true);
        
        // Редактор //
        SimpleComboBox<TypeSpeed> comboBoxSpeed = new SimpleComboBox<>(new StringLabelProvider<TypeSpeed>());
        comboBoxSpeed.setClearValueOnParseError(false);
        comboBoxSpeed.setPropertyEditor(new PropertyEditor<TypeSpeed>() {
          @Override
          public TypeSpeed parse(CharSequence text) {
                return TypeSpeed.parseString(text.toString());
          }

          @Override
          public String render(TypeSpeed object) {
            return object == null ? TypeSpeed.AUTO.toString() : object.toString();
          }
        });
        comboBoxSpeed.setTriggerAction(TriggerAction.ALL);
        comboBoxSpeed.add(TypeSpeed.AUTO);
        comboBoxSpeed.add(TypeSpeed.S10MB);
        comboBoxSpeed.add(TypeSpeed.S100BM);
        comboBoxSpeed.add(TypeSpeed.S1GB);
        comboBoxSpeed.add(TypeSpeed.S10GB);
        comboBoxSpeed.add(TypeSpeed.S100GB);
        
        Converter<String, TypeSpeed> speedConverter = new Converter<String, TypeSpeed>() {
            @Override
            public String convertFieldValue(TypeSpeed object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public TypeSpeed convertModelValue(String object) {
                return TypeSpeed.parseString(object);
            }
        };
        
        SimpleComboBox<TypeDuplex> comboBoxDuplex = new SimpleComboBox<>(new StringLabelProvider<TypeDuplex>());
        comboBoxDuplex.setClearValueOnParseError(false);
        comboBoxDuplex.setPropertyEditor(new PropertyEditor<TypeDuplex>() {
          @Override
          public TypeDuplex parse(CharSequence text) {
                return TypeDuplex.parseString(text.toString());
          }

          @Override
          public String render(TypeDuplex object) {
            return object == null ? TypeDuplex.AUTO.toString() : object.toString();
          }
        });
        comboBoxDuplex.setTriggerAction(TriggerAction.ALL);
        comboBoxDuplex.add(TypeDuplex.AUTO);
        comboBoxDuplex.add(TypeDuplex.HALF);
        comboBoxDuplex.add(TypeDuplex.FULL);
        
        Converter<String, TypeDuplex> duplexConverter = new Converter<String, TypeDuplex>() {
            @Override
            public String convertFieldValue(TypeDuplex object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public TypeDuplex convertModelValue(String object) {
                return TypeDuplex.parseString(object);
            }
        };
        
        GridRowEditing<Ethernet> rowEditing = new GridRowEditing<>(tableEthernets);
        ColumnConfig<Ethernet, Short> numberColumn = tableEthernets.getColumnModel().getColumn(0);
        rowEditing.addRenderer(numberColumn, new AbstractSafeHtmlRenderer<Short>() {
            INumberTamplate numberTemplate = GWT.create(INumberTamplate.class);
            @Override
            public SafeHtml render(Short object) {
                return numberTemplate.render(object);
            }
        });
        ColumnConfig<Ethernet, String> nameColumn = tableEthernets.getColumnModel().getColumn(1);
        rowEditing.addRenderer(nameColumn, new AbstractSafeHtmlRenderer<String>() {
            INameTamplate nameTemplate = GWT.create(INameTamplate.class);
            @Override
            public SafeHtml render(String object) {
                return nameTemplate.render(object);
            }
        });
        final GridEditing<Ethernet> editingTable = rowEditing;
        editingTable.addEditor(colStatus, new CheckBox());
        editingTable.addEditor(colSpeed, speedConverter, comboBoxSpeed);
        editingTable.addEditor(colDuplex, duplexConverter, comboBoxDuplex);
        editingTable.addEditor(colSecurity, new CheckBox());
        editingTable.addEditor(colFlowControl, new CheckBox());
        editingTable.addEditor(colComment, new TextField());
        // Редактор //
        
        layoutContainer.add(toolBar, new VerticalLayoutData(1, -1));
        layoutContainer.add(tableEthernets, new VerticalLayoutData(1, 1));
        
//        panel.setButtonAlign(BoxLayoutPack.CENTER);
//        panel.addButton(buttonApply);
        panel.setHeadingText(TypeMenu.InterfaceEthernetList.getDescription());
        panel.setBorders(false);
        panel.add(layoutContainer, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IEthernetListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setEthernets(List<Ethernet> ethernets) {
        if (ethernets == null) return;
        listEthernets.replaceAll(ethernets);
    }
}
