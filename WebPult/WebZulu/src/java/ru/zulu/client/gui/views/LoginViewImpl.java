/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import ru.zulu.client.utils.Utils;

/**
 * Панель авторизации.
 * @author Носов А.В.
 */
public class LoginViewImpl extends Composite implements LoginView {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private ILoginPresenter presenter;
    
    private FramedPanel panel;
    /** Разметка внутри окна. */
    private VerticalLayoutContainer containerVertical;
    /** Имя пользователя. */
    private TextField textFieldLogin;
    /** Пароль. */
    private PasswordField passwordField; 
    /** Кнопка входа. */
    private TextButton buttonLogin;
    /** Сообщение об ошибке. */
    private Label labelErr;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     * @param login имя
     */
    public LoginViewImpl(String login) {
        initComponents();
        if (login != null) textFieldLogin.setText(login);
    }
    
    private void initComponents() {
        
        panel = new FramedPanel();
        containerVertical = new VerticalLayoutContainer();
        textFieldLogin = new TextField();
        passwordField = new PasswordField();
        buttonLogin = new TextButton();
        labelErr = new Label();
        
        textFieldLogin.setAllowBlank(false);
        textFieldLogin.addValidator(new MinLengthValidator(3));
        textFieldLogin.addValidator(new MaxLengthValidator(100));
        
        textFieldLogin.setAllowBlank(false);
        textFieldLogin.setEmptyText("Имя пользователя...");
        
        passwordField.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    if (!textFieldLogin.isValid() && 
                        !passwordField.isValid()) return;
                    
                    presenter.login(textFieldLogin.getText(),
                                    passwordField.getText());
                }
            }
        });
        
        buttonLogin.setText("Вход");
        buttonLogin.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                if (!textFieldLogin.isValid() && 
                    !passwordField.isValid()) return;
                
                presenter.login(textFieldLogin.getText(),
                                passwordField.getText());
            }
        });
//        buttonLogin.addKeyDownHandler(new KeyDownHandler() {
//
//            @Override
//            public void onKeyDown(KeyDownEvent event) {
//                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//                    if (!textFieldLogin.isValid() && 
//                        !passwordField.isValid()) return;
//                    
//                    presenter.login(textFieldLogin.getText(),
//                                    passwordField.getText());
//                }
//            }
//        });
        
        labelErr.setVisible(false);
        
        containerVertical.setLayoutData(HasHorizontalAlignment.ALIGN_LEFT);
        containerVertical.add(labelErr, new VerticalLayoutData(1, -1));
        containerVertical.add(Utils.createField(textFieldLogin, "Логин", 55), 
                              new VerticalLayoutData(1, -1));
        containerVertical.add(Utils.createField(passwordField, "Пароль", 55), 
                              new VerticalLayoutData(1, -1));
        
        panel.setWidth(350);
        panel.addStyleName("mycenter");
        panel.setHeadingText("Вход в систему");
        panel.add(containerVertical, new MarginData(5));
        panel.addButton(buttonLogin);
        initWidget(panel);
    }
    
    @Override
    public Widget asWidget() {
        return this;
    } 
    
    @Override
    public void setPresenter(ILoginPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void setLogin(String login) {
        labelErr.setText("Ошибка авторизации.");
        if (login != null) textFieldLogin.setText(login);
        passwordField.clear();
    }
}
