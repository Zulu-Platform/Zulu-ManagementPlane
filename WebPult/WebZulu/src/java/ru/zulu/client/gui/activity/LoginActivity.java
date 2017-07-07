/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.LoginPlace;
import ru.zulu.client.gui.views.LoginView;
import libMessage.client.types.TypeMessage;
import libMessage.client.messages.User;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class LoginActivity extends AbstractActivity implements LoginView.ILoginPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    /** Имя. */
    private String login;
    // End of variables declaration
    
    public LoginActivity(LoginPlace place) {
        this.login = place.getLogin();
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final LoginView view = ClientFactory.getInstance().getLoginView();
        view.setLogin(login);
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    @Override
    public void login(String login, String pass) {
        User user = new User();
        user.setTypeMessage(TypeMessage.Login);
        user.setName(login);
        user.setPassword(pass);
        
        ClientFactory.getInstance().getTxRx().sendMessage(
                user, "Вход в систему", "Проверка имени и пароля.");
    }
}
