/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import ru.zulu.client.event.ErrorEvent;
import ru.zulu.client.event.ErrorEventHandler;
import ru.zulu.client.event.SuccessEvent;
import ru.zulu.client.event.SuccessEventHandler;
import ru.zulu.client.gui.AppController;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.mapper.MainContentActivityMapper;
import ru.zulu.client.gui.mapper.ZuluPlaceHistoryMapper;
import ru.zulu.client.gui.places.MainPlace;
import ru.zulu.client.gui.views.AppLayout;
import ru.zulu.client.gui.views.AppLayoutImpl;
import ru.zulu.client.messages.system.MessageSystem;

/**
 * Main entry point.
 *
 * @author nosov
 */
public class WelcomeEntryPoint implements EntryPoint {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    // End of variables declaration
    
    /**
     * Creates a new instance of WelcomEntryPoint
     */
    public WelcomeEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        EventBus eventBus = ClientFactory.getInstance().getEventBus();
        eventBus.addHandler(SuccessEvent.TYPE, new SuccessEventHandler() {

            @Override
            public void success(SuccessEvent event) {
                successMsg(event.getMessageSystem());
            }
            
        });
        eventBus.addHandler(ErrorEvent.TYPE, new ErrorEventHandler() {

            @Override
            public void error(ErrorEvent event) {
                errorMsg(event.getMessageSystem());
            }
            
        });
        
        AppLayout appLayout = new AppLayoutImpl();
        AppController controller = new AppController(appLayout);
        
        // Main
        MainContentActivityMapper mainContentContainerActivityMapper = 
                new MainContentActivityMapper();
        ActivityManager mainContentContainerActivityManager = 
                new ActivityManager(mainContentContainerActivityMapper, eventBus);
        mainContentContainerActivityManager.setDisplay(appLayout.getMainContainer());
        
        ZuluPlaceHistoryMapper historyMapper = GWT.create(ZuluPlaceHistoryMapper.class);
        
        PlaceController placeController = ClientFactory.getInstance().getPlaceController();
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new MainPlace());
        
        RootLayoutPanel.get().add(appLayout.getContentContainer());
        //RootPanel.get().add(containerWidget); 
        
        historyHandler.handleCurrentHistory();
//        User msgUser = new User();
//        msgUser.setTypeMessage(TypeMessage.RqUserById);
//        ClientFactory.getInstance().getTxRx().sendMessage(msgUser);
    }
    
    /**
     * Обработчик системных ошибок.
     * @param error системная ошибка
     */
    private void errorMsg(MessageSystem error) {
        if (error == null) return;
        switch (error.getCode()) {
//            case -1: // OTHER
//                Utils.infoMessage(error.getTitle(), error.getDescription());
//                break;
//            case 3: // WARNING_NEW_SESSION
//                ClientFactory.getInstance().getEventBus().fireEvent(new LoginEvent(null));
//                break;
//            case 7: // ERROR_REGISTRATION
//                break;
//            case 8: // ERROR_NO_RESPONSE
//                AlertMessageBox messageBox = new AlertMessageBox("Внимание!", 
//                                            "Нет ответа от сервера.");
//                messageBox.center();
//                messageBox.addHideHandler(new HideHandler() {
//                    @Override
//                    public void onHide(HideEvent event) {
//                        History.newItem(LoginPlace.VIEW_HISTORY_TOKEN+":");
//                    }
//                });
//                messageBox.show();
//                break;
        }
    }
    
    /**
     * Обработчик системных успехов.
     * @param success системный успех
     */
    private void successMsg(MessageSystem success) {
//        if (success == null) return;
//        try {
//            TypeSuccess ts = TypeSuccess.values()[success.getCode()];
//            switch (ts) {
//                case LOGOUT:
//                    ClientFactory.getInstance().getEventBus().fireEvent(new LoginEvent(null));
//                    break;
//                case EDIT_USER:
//                case ADD_USER:
//                    Utils.infoMessage(success.getTitle(), success.getDescription());
//                    History.newItem(UserListPlace.VIEW_HISTORY_TOKEN + ":");
//                    break;
//                case EDIT_GROUP:
//                case ADD_GROUP:
//                    Utils.infoMessage(success.getTitle(), success.getDescription());
//                    History.newItem(GroupListPlace.VIEW_HISTORY_TOKEN + ":");
//                    break;
//                default:
//                    Utils.infoMessage(success.getTitle(), success.getDescription());
//                    break;
//            }
//        } catch (ArrayIndexOutOfBoundsException ex) { 
//            Log.error(CLASS_NAME, "Не могу определить тип ответа.");
//        }
    }
}
