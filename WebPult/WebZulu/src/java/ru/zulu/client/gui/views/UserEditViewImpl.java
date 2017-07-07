 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.DualListField.Mode;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import java.util.Date;
import java.util.List;
import ru.zulu.client.gui.stocks.IGroupsData;
import ru.zulu.client.gui.types.TypeButtons;
import libMessage.client.messages.Group;
import libMessage.client.messages.User;
import ru.zulu.client.gui.types.TypeMenu;
import libMessage.client.types.TypeMessage;
import ru.zulu.client.utils.Utils;

/**
 * Панель настройки пользователей.
 * @author Носов А.В.
 */
public class UserEditViewImpl extends Composite implements UserEditView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private IUsersEditPresenter presenter;
    
    /** Панель для редактирования пользоватлей. */
    private FramedPanel panel;
    /** Применить. */
    private TextButton buttonApply;
    /** Отменить. */
    private TextButton buttonCansel;
    
    /** Логин. */
    private TextField textFieldLogin;
    /** Комментарий. */
    private TextArea textAreaComments;
    /** Пароль. */
    private PasswordField textFieldPass1;
    /** Подтверждение пароля. */
    private PasswordField textFieldPass2;
    /***/
    private final IGroupsData propGroups;
    /** Полный список групп. */
    private final ListStore<Group> listGroups;
    /** Выбранный список. */
    private final ListStore<Group> listGroupsSelect;
    /** Список групп. */
//    private DualListField<Group, String> dualListGroups;
    /** Срок действия пароля. */
    private IntegerField passwordExpires;
    /** Срок действия пароля. */
    private DateField passwordInactive;
    /** Срок действия учетной записи. */
    private DateField accountExpires;
    /** Пользователь. */
    private User user;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public UserEditViewImpl() {
        propGroups = GWT.create(IGroupsData.class);
        listGroups = new ListStore<Group>(propGroups.key());
        listGroupsSelect = new ListStore<Group>(propGroups.key());
        initComponents();
    }
    
    private void initComponents() {
        panel = new FramedPanel();
        buttonApply = new TextButton(TypeButtons.APPLY.getDescription());
        buttonCansel = new TextButton(TypeButtons.CANSEL.getDescription());
        
        textFieldLogin = new TextField();
        textAreaComments = new TextArea();
        textFieldPass1 = new PasswordField();
        textFieldPass2 = new PasswordField();
        passwordExpires = new IntegerField();
        passwordInactive = new DateField();
        accountExpires = new DateField();
        
        
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
        textFieldLogin.setAllowBlank(false);
        textFieldLogin.addValidator(new MinLengthValidator(5));
        textFieldLogin.addValidator(new MaxLengthValidator(32));
//        textAreaComments.setAllowBlank(false);
        textAreaComments.addValidator(new MaxLengthValidator(255));
        textFieldPass1.addValidator(new MinLengthValidator(5));
        textFieldPass1.addValidator(new MaxLengthValidator(25));
        textFieldPass2.addValidator(new MinLengthValidator(5));
        textFieldPass2.addValidator(new MaxLengthValidator(25));
        
        final DualListField<Group, String> 
                dualListGroups = new DualListField<Group, String>(listGroups, listGroupsSelect, 
                                              propGroups.nameProp(), new TextCell());
        dualListGroups.addValidator(new EmptyValidator<List<Group>>());
        dualListGroups.setEnableDnd(true);
        dualListGroups.setMode(Mode.INSERT);
        
        passwordExpires.setAllowBlank(false);
        passwordExpires.addParseErrorHandler(new ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a number");
            }
        });
        passwordInactive.addValidator(new MinDateValidator(new Date()));
        passwordInactive.addParseErrorHandler(new ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a date");
            }
        });
        passwordInactive.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                String v = event.getValue() == null ? "nothing"
                    : DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(event.getValue());
                Info.display("Selected", "You selected " + v);
            }
        });
        accountExpires.addValidator(new MinDateValidator(new Date()));
        accountExpires.addParseErrorHandler(new ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a date");
            }
        });
        accountExpires.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                String v = event.getValue() == null ? "nothing"
                    : DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(event.getValue());
                Info.display("Selected", "You selected " + v);
            }
        });
        
        HBoxLayoutContainer hlc = new HBoxLayoutContainer();
        hlc.setPadding(new Padding(5));
        hlc.setHBoxLayoutAlign(HBoxLayoutContainer.HBoxLayoutAlign.TOP);
        hlc.add(buttonApply, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 5, 0, 0)));
        hlc.add(buttonCansel, new BoxLayoutContainer.BoxLayoutData(new Margins(0)));
        
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.add(new FieldLabel(textFieldLogin, "Логин (Имя)"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(textAreaComments, "Комментарий"), new VerticalLayoutData(1, 100));
        vlc.add(new FieldLabel(textFieldPass1, "Пароль"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(textFieldPass2, "Подтверждение"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(dualListGroups, "Группа"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(passwordExpires, "Срок действия пароля"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(passwordInactive, "Дата сброса пароля"), new VerticalLayoutData(1, -1));
        vlc.add(new FieldLabel(accountExpires, "Срок действия аккаунта"), new VerticalLayoutData(1, -1));
        vlc.add(hlc, new VerticalLayoutData(1, -1));
        
        vlc.setWidth(400);
        
        panel.setHeadingText(TypeMenu.UserAdd.getDescription());
        panel.setBorders(false);
        panel.add(vlc, new MarginData(5));
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(IUsersEditPresenter presenter) {
        this.presenter = presenter;
    }
    
    /**
     * Проверка введенных данных.
     */
    private void validData() {
        Boolean b = textFieldLogin.isValid();
        b = (b) ? textAreaComments.isValid() : false;
        
        if ( !(textFieldPass1.getText().trim().isEmpty()) ||
             !(textFieldPass2.getText().trim().isEmpty()) ) {
            
            b = (b) ? textFieldPass1.isValid() : false;
            b = (b) ? textFieldPass2.isValid() : false;
            if (!textFieldPass1.getText().trim().equals(textFieldPass2.getText().trim()))
                b = false;
        }
        
        if (!b) {
            Utils.infoMessage(panel.getHeader().getText(), "Не все данные введены верно.");
            return;
        }
        
        user.setNewName(textFieldLogin.getText().trim());
        user.setGecos(textAreaComments.getText().trim());
        user.setPassword(textFieldPass1.getText().trim());
        user.clearGroup();
        for (Group g : listGroupsSelect.getAll())
            user.addGroup(g);
        user.setPasswordExpires(passwordExpires.getValue());
        user.setPasswordInactive(passwordInactive.getValue());
        user.setAccountExpires(accountExpires.getValue());
        
        if (user.getUid() < 1) user.setTypeMessage(TypeMessage.UserAdd);
        else user.setTypeMessage(TypeMessage.UserEdit);
        
        presenter.save(user);
    }

    @Override
    public void setUser(User user) {
        if (user == null) user = new User();
        if (user.getUid() < 1) panel.setHeadingText(TypeMenu.UserAdd.getDescription());
        else panel.setHeadingText(TypeMenu.UserEdit.getDescription());
        
        this.user = user;
        textFieldLogin.setText(user.getName());
        textFieldLogin.setValue(user.getName());
        textAreaComments.setText(user.getGecos());
        passwordExpires.setValue(user.getPasswordExpires());
        passwordInactive.setValue(user.getPasswordInactive());
        accountExpires.setValue(user.getAccountExpires());
        List<Group> groups = user.getGroups();
        listGroupsSelect.clear();
        if (groups != null) {
            listGroupsSelect.replaceAll(groups);
            updateDualListGroup();
        }
    }

    @Override
    public void setGroups(List<Group> groups) {
        if (groups == null) return;
        listGroups.replaceAll(groups);
        updateDualListGroup();
    }
    
    private void updateDualListGroup() {
        if ( (listGroups == null) || (listGroups.size() < 1) ) return;
        if ( (listGroupsSelect == null) || (listGroupsSelect.size() < 1) ) return;
        
//        Log.debug(CLASS_NAME, "List groups:"+listGroups.size());
//        Log.debug(CLASS_NAME, "List groups Selected:"+listGroupsSelect.size());
        
        for (Group group : listGroupsSelect.getAll()) {
            Group key1 = listGroups.findModelWithKey(String.valueOf(group.getGid()));
//            Log.debug(CLASS_NAME, "Key:"+key1);
            if (key1 != null) listGroups.remove(key1);
        }
    }
}
