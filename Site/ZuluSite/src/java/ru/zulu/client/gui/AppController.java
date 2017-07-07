package ru.zulu.client.gui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import ru.zulu.client.gui.places.AAAPlace;
import ru.zulu.client.gui.places.MainPlace;
import ru.zulu.client.gui.views.AppLayout;
import ru.zulu.client.gui.views.HeaderView;
import ru.zulu.client.gui.views.HeaderView.IHeaderPresenter;
import ru.zulu.client.gui.views.MenuView;
import ru.zulu.client.gui.views.MenuView.IMenuPresenter;
import ru.zulu.client.gui.types.TypeMenu;

public class AppController implements HeaderView.IHeaderPresenter,
                                      MenuView.IMenuPresenter {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private final PlaceController placeController;
    private HeaderView headerView;
    private MenuView menuView;
    // End of variables declaration

    public AppController(final AppLayout appLayout) {
        placeController = ClientFactory.getInstance().getPlaceController();
        headerView = ClientFactory.getInstance().getHeaderView();
        menuView = ClientFactory.getInstance().getMenuView();
        headerView.setPresenter((IHeaderPresenter)this);
        menuView.setPresenter((IMenuPresenter)this);
        EventBus eventBus = ClientFactory.getInstance().getEventBus();
        eventBus.addHandler(PlaceChangeEvent.TYPE, new HandlePlaceLayout(appLayout));
    }

    @Override
    public void selectionEvent(TypeMenu tm) {
        if (tm == null) return;
        switch (tm) {
            case Main:
                History.newItem(MainPlace.VIEW_HISTORY_TOKEN+":");
                break;
            default:
                History.newItem(AAAPlace.VIEW_HISTORY_TOKEN+":");
        }
    }
    
    
    private static class HandlePlaceLayout implements PlaceChangeEvent.Handler {
        private final AppLayout appLayout;

        public HandlePlaceLayout(AppLayout appLayout) {
            this.appLayout = appLayout;
        }

        @Override
        public void onPlaceChange(PlaceChangeEvent event) {
            Place newPlace = event.getNewPlace();
//            if (newPlace instanceof LoginPlace)
//                appLayout.setMainLayout();
//            else
                appLayout.setHeaderLayout();
            
        }
    }
}
