package ru.zulu.client.gui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import ru.zulu.client.event.LoginEvent;
import ru.zulu.client.event.LoginEventHandler;
import ru.zulu.client.gui.places.LoginPlace;
import ru.zulu.client.gui.places.SystemsPlace;
import ru.zulu.client.gui.views.AppLayout;
import ru.zulu.client.gui.views.HeaderView;
import ru.zulu.client.gui.views.HeaderView.IHeaderPresenter;
import ru.zulu.client.gui.views.MenuView;
import ru.zulu.client.gui.views.MenuView.IMenuPresenter;
import libMessage.client.types.TypeMessage;
import ru.zulu.client.utils.Utils;
import libMessage.client.messages.User;
import ru.zulu.client.gui.types.TypeMenu;

public class AppController implements HeaderView.IHeaderPresenter,
                                      MenuView.IMenuPresenter {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private final HeaderView headerView;
    private final MenuView menuView;
    // End of variables declaration

    public AppController(final AppLayout appLayout) {
//        placeController = ClientFactory.getInstance().getPlaceController();
        headerView = ClientFactory.getInstance().getHeaderView();
        menuView = ClientFactory.getInstance().getMenuView();
        headerView.setPresenter((IHeaderPresenter)this);
        menuView.setPresenter((IMenuPresenter)this);
        EventBus eventBus = ClientFactory.getInstance().getEventBus();
        eventBus.addHandler(PlaceChangeEvent.TYPE, new HandlePlaceLayout(appLayout));
        eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            @Override
            public void login(LoginEvent event) {
                LogInOut(event.getUser());
            }

        });
    }

    /**
     * Авторизация пользователя.
     * @param u пользователь
     */
    private void LogInOut(User u) {
        if ( u != null) {
            Utils.infoMessage("Авторизация.", "Имя: "+u.getName()+"; ip:"+u.getIpv4());
//            placeController.goTo(new SystemsPlace());
//            placeController.goTo(new MonitoringPlace());
            History.newItem(SystemsPlace.VIEW_HISTORY_TOKEN+":");
            headerView.setUser(u);
        } else {
//            String name = (u == null) ? null : u.getName();
//            Utils.infoMessage("Ошибка аутентификации.",
//                             "Не верное имя пользователя или пароль.");
            History.newItem(LoginPlace.VIEW_HISTORY_TOKEN+":");
        }
    }

    @Override
    public void logout(User user) {
        user.setTypeMessage(TypeMessage.Logout);
        ClientFactory.getInstance().getTxRx().sendMessage(user);
    }

    @Override
    public void goTo(TypeMenu tm) {
        Utils.goTo(tm);
    }

    private static class HandlePlaceLayout implements PlaceChangeEvent.Handler {
        private final AppLayout appLayout;

        public HandlePlaceLayout(AppLayout appLayout) {
            this.appLayout = appLayout;
        }

        @Override
        public void onPlaceChange(PlaceChangeEvent event) {
            Place newPlace = event.getNewPlace();
            if (newPlace instanceof LoginPlace) {
                appLayout.setLoginLayout();
            } else {
                appLayout.setMainLayout();
            }
        }
    }
}
