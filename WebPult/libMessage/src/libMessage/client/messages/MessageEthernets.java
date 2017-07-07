/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import libMessage.client.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import libMessage.client.messages.Ethernet;

/**
 * Запросить/Отправить/Принять список ethernet портов.
 * @author Носов А.В.
 */
public class MessageEthernets extends Message implements Serializable {
    
    // Variables declaration
    /** Список ethernet портов. */
    private List<Ethernet> ethernets;
    // End of variables declaration
    
    /**
     * Создание сообщения .
     */
    public MessageEthernets() {
    }

    /**
     * Возвращает список ethernet портов.
     * @return список ethernet портов
     */
    public List<Ethernet> getEthernets() {
        return ethernets;
    }

    /**
     * Устанавливает список ethernet портов.
     * @param ethernets список ethernet портов
     */
    public void setEthernets(List<Ethernet> ethernets) {
        this.ethernets = ethernets;
    }
    
    /**
     * Добавляет в список ethernet порт.
     * @param ethernet ethernet порт
     */
    public void addEthernet(Ethernet ethernet) {
        if (ethernets == null)
            ethernets = new ArrayList<Ethernet>();
        ethernets.add(ethernet);
    }
}