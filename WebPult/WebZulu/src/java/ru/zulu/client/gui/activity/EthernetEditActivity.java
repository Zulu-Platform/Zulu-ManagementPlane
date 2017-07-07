/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import ru.zulu.client.event.EthernetByIdEvent;
import ru.zulu.client.event.EthernetByIdEventHandler;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.EthernetEditPlace;
import ru.zulu.client.gui.places.EthernetListPlace;
import ru.zulu.client.gui.views.EthernetEditView;
import libMessage.client.messages.Ethernet;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class EthernetEditActivity extends MessageAbstractActivity implements EthernetEditView.IEthernetEditPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    /* Идентификатор пользователя. */
    private int id;
    // End of variables declaration
    
    public EthernetEditActivity(EthernetEditPlace place) {
        this.id = place.getId();
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final EthernetEditView view = ClientFactory.getInstance().getEthernetEditView();
        view.setPresenter(this);
        eventBus.addHandler(EthernetByIdEvent.TYPE, new EthernetByIdEventHandler() {

            @Override
            public void ethernet(EthernetByIdEvent event) {
                view.setEthernet(event.getEthernet());
            }
            
        });
        panel.setWidget(view.asWidget());
        
        rqEthernetById();
    }

    @Override
    public void save(Ethernet ethernet) {
        rqMessage(ethernet);
    }

    @Override
    public void cansel() {
        History.newItem(EthernetListPlace.VIEW_HISTORY_TOKEN+":");
    }
    
    /**
     * Запрос списка пользователей.
     */
    private void rqEthernetById() {
//        Group msgG = new Group();
//        msgG.setGid((short)id);
//        msgG.setTypeMessage(TypeMessage.RqGroupById);
//        ClientFactory.getInstance().getTxRx().sendMessage(msgG);
    }
}
