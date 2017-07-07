/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.zulu.client.gui.stocks.IUsersData;
import ru.zulu.client.gui.types.TypeButtons;
import libMessage.client.messages.User;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Панель настройки пользователей.
 * @author Носов А.В.
 */
public class UsersListViewImpl extends Composite implements UsersListView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IUsersListPresenter presenter;
    
    /** Панель для отображения списка пользоватлей. */
    private FramedPanel panel;
    /** Вертикальный контейнер. */
    private VerticalLayoutContainer layoutContainer;
    /** Список пользователей. */
    private Grid<User> tableUsers;
    
    /** Контейнер для кнопок. */
    private ToolBar toolBar;
    /** Добавить пользователя. */
    private TextButton buttonAddUser;
    /** Редактировать пользователя. */
    private TextButton buttonEditUser;
    /** Удалить пользователя. */
    private TextButton buttonDelUser;
    /** Да. */
    private TextButton buttonYES;
    /** Нет. */
    private TextButton buttonNO;
    /** Предупреждение. */
    private MessageBox messageBox;
    
    /** Список пользователей. */
    private ListStore<User> listUsers;
    /** Идентфикатор. */
    private IdentityValueProvider<User> identity;
    /** Модель выделнных объектов. */
    private CheckBoxSelectionModel<User> selectionModel;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public UsersListViewImpl() {
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        final IUsersData propUsers = GWT.create(IUsersData.class);
        identity = new IdentityValueProvider<User>();
        selectionModel = new CheckBoxSelectionModel<User>(identity);
        selectionModel.setSelectionMode(Style.SelectionMode.MULTI);
        
        layoutContainer = new VerticalLayoutContainer();
        toolBar = new ToolBar();
        buttonAddUser = new TextButton(TypeButtons.ADD.getDescription());
        buttonEditUser = new TextButton(TypeButtons.EDIT.getDescription());
        buttonDelUser = new TextButton(TypeButtons.DELETE.getDescription());
        buttonYES = new TextButton(TypeButtons.YES.getDescription());
        buttonNO = new TextButton(TypeButtons.NO.getDescription());
        listUsers = new ListStore<User>(propUsers.key());
        
        buttonAddUser.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                presenter.goToEditUser("");
            }
        });
        buttonEditUser.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                List<User> edit = selectionModel.getSelectedItems();
                if ( (edit==null) || (edit.size() < 1) ) return;
                User u = edit.get(0);
                presenter.goToEditUser(u.getName());
            }
        });
        buttonDelUser.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                confirmDeleteUsers();
            }
        });
        buttonYES.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                presenter.goToDeleteUsers(selectionModel.getSelectedItems());
            }
        });
        buttonNO.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                if (messageBox != null) messageBox.hide();
            }
        });
        toolBar.add(buttonAddUser);
        toolBar.add(buttonEditUser);
        toolBar.add(buttonDelUser);
        
        ColumnConfig<User, String> colName = 
                new ColumnConfig<User, String>(propUsers.name(), 100);
        ColumnConfig<User, String> colGecos = 
                new ColumnConfig<User, String>(propUsers.gecos(), 130);
        ColumnConfig<User, Date> colCreate = 
                new ColumnConfig<User, Date>(propUsers.createTime(), 145);
        ColumnConfig<User, Date> colLastPasswordChange = 
                new ColumnConfig<User, Date>(propUsers.lastPasswordChange(), 190);
        ColumnConfig<User, Integer> colPasswordExpires = 
                new ColumnConfig<User, Integer>(propUsers.passwordExpires(), 150);
        ColumnConfig<User, Date> colPasswordInactive = 
                new ColumnConfig<User, Date>(propUsers.passwordInactive(), 190);
        ColumnConfig<User, Date> colAccountExpires = 
                new ColumnConfig<User, Date>(propUsers.accountExpires(), 160);
//        ColumnConfig<User, Boolean> colStatus = 
//                new ColumnConfig<User, Boolean>(propUsers.status(), 15);
        
        colName.setHeader("Имя");
        colGecos.setHeader("Коментарий");
        colCreate.setHeader("Время создания");
        colLastPasswordChange.setHeader("Дата изменения пароля");
        colPasswordExpires.setHeader("Окончание пароля");
        colPasswordInactive.setHeader("Суток до смены пароля");
        colAccountExpires.setHeader("Окончание аккаунта");
//        colStatus.setHeader("Статус");
//        colPasswordExpires.setHeader("Название группы");
        
        colCreate.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT)));
        colLastPasswordChange.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT)));
        colPasswordInactive.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT)));
        colAccountExpires.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT)));
        
        List<ColumnConfig<User, ?>> columns = new ArrayList<ColumnConfig<User, ?>>();
        columns.add(selectionModel.getColumn());
        columns.add(colName);
        columns.add(colGecos);
        columns.add(colCreate);
        columns.add(colLastPasswordChange);
        columns.add(colPasswordExpires);
        columns.add(colPasswordInactive);
        columns.add(colAccountExpires);
        
        final ColumnModel<User> cm = new ColumnModel<User>(columns);
        
//        final GroupingView<User> groupingView = new GroupingView<User>();
//        groupingView.setShowGroupedColumn(false);
//        groupingView.setForceFit(true);
//        groupingView.groupBy(colPasswordExpires);
//        tableUsers = new Grid<User>(listUsers, cm, groupingView);
        
        tableUsers = new Grid<User>(listUsers, cm);
        tableUsers.setHeight("600px");
        tableUsers.setSelectionModel(selectionModel);
        //tableUsers.getView().setAutoExpandColumn(colGroupName);
        tableUsers.setBorders(false);
        tableUsers.getView().setStripeRows(true);
        tableUsers.getView().setColumnLines(true);
        
        layoutContainer.add(toolBar, new VerticalLayoutData(1, -1));
        layoutContainer.add(tableUsers, new VerticalLayoutData(1, 1));
        
        panel.setHeadingText(TypeMenu.UserList.getDescription());
        panel.setBorders(false);
        panel.add(layoutContainer, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IUsersListPresenter presenter) {
        this.presenter = presenter;
    }
    
    /**
     * Запрос удаления пользователей.
     */
    private void confirmDeleteUsers() {
        List<User> del = selectionModel.getSelectedItems();
        if ( (del==null) || (del.size() < 1) ) return;
        
        String msg = "<html>Вы уверены, что хотите удалить следующих пользователей?<ul>";
        for (User u : del)
            msg = msg + "<li><b>" + u.getName()+ "</b></li>";
        
        msg = msg + "</ul></html>";
        messageBox = new MessageBox("Подтверждение", msg);
        messageBox.setPredefinedButtons();
        messageBox.setIcon(MessageBox.ICONS.question());
        messageBox.addButton(buttonYES);
        messageBox.addButton(buttonNO);
        messageBox.center();
        messageBox.show();
    }
    
    @Override
    public void setUsers(List<User> users) {
        if (users == null) return;
        listUsers.replaceAll(users);
    }
}
