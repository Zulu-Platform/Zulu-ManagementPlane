/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libDataBusZulu;

import java.util.List;
import java.util.Properties;
import libMessage.client.messages.User;
import org.apache.log4j.Logger;
import ru.libDataBusZulu.exceptions.ExceptionsLibDataBus;
import ru.libDataBusZulu.messages.UserGroup;
import ru.libDataBusZulu.utils.ReadProperties;

/**
 *
 * @author Носов А.В.
 */
public class TestMainClass {
    
    private static final Logger log = Logger.getLogger(TestMainClass.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExceptionsLibDataBus {
        Properties prop = new Properties();
        prop.setProperty("hostName", "Test mashine");
        prop.setProperty("productID", "ZU-010814-A");
        prop.setProperty("serialNumber", "1122334455");
        prop.setProperty("software", "0.0.0.1");
        prop.setProperty("contact", "Нет никого");
        prop.setProperty("location", "Москва");

        ReadProperties.saveProperties("/home/nosov/Public/systems.properties", prop, null);
    }
    
    
}
