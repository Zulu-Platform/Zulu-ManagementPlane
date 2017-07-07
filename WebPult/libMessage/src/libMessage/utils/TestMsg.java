/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.utils;

import java.util.Random;
import libMessage.client.Message;
import org.apache.log4j.Logger;

/**
 * Класс полезных утилиток.
 * @author Носов А.В.
 */
public class TestMsg {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(TestMsg.class);
    
    private static Random random = new Random();
    // End of variables declaration
    
    /**
     * Возращает тестовое сообщение для клиента в соответствии с его типом.
     * @param msg запрос
     * @param localAddres ip адрес сессии
     * @return ответ
     */
    public static Message getTestMsg(Message msg, String localAddres) {
        return null;
    }
}
