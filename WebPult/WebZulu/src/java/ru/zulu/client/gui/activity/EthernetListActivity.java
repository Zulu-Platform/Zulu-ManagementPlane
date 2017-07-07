/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import java.util.List;
import ru.zulu.client.event.EthernetListEvent;
import ru.zulu.client.event.EthernetListEventHandler;
import ru.zulu.client.gui.ClientFactory;
import ru.zulu.client.gui.places.EthernetEditPlace;
import ru.zulu.client.gui.places.EthernetListPlace;
import ru.zulu.client.gui.views.EthernetListView;
import libMessage.client.messages.MessageEthernets;
import libMessage.client.types.TypeMessage;
import libMessage.client.messages.Ethernet;

/**
 * Инициализация панели.
 * @author Носов А.В.
 */
public class EthernetListActivity extends MessageAbstractActivity implements EthernetListView.IEthernetListPresenter {
    
    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    // End of variables declaration
    
    public EthernetListActivity(EthernetListPlace place) {
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final EthernetListView view = ClientFactory.getInstance().getEthernetListView();
        view.setPresenter(this);
        eventBus.addHandler(EthernetListEvent.TYPE, new EthernetListEventHandler() {

            @Override
            public void ethernetsList(EthernetListEvent event) {
                view.setEthernets(event.getEthernets());
            }
            
        });
        panel.setWidget(view.asWidget());
        
        rqMessage(TypeMessage.RqEthernets);
    }

    @Override
    public void goToEditEthernet(int id) {
        History.newItem(EthernetEditPlace.VIEW_HISTORY_TOKEN+":"+String.valueOf(id));
    }

    @Override
    public void goToDeleteEthernets(List<Ethernet> ethernets) {
        if ( (ethernets == null) || (ethernets.size() < 1) ) return;
        MessageEthernets eths = new MessageEthernets();
        eths.setTypeMessage(TypeMessage.GroupsRemove);
        eths.setEthernets(ethernets);
        rqMessage(eths);
        
        rqMessage(TypeMessage.RqEthernets);
    }

    @Override
    public void updateEthernet(List<Ethernet> ethernets) {
        if (ethernets == null) return;
        MessageEthernets eths = new MessageEthernets();
        eths.setTypeMessage(TypeMessage.EthernetsEdit);
        eths.setEthernets(ethernets);
        rqMessage(eths);
    }
}
