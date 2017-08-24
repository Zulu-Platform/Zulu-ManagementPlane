/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.utils;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import ru.libDataBusZulu.exceptions.ExceptionsLibDataBus;
import ru.libDataBusZulu.messages.Ethernet;
import ru.libDataBusZulu.messages.Systems;
import ru.libDataBusZulu.messages.UserGroup;

/**
 *
 * @author Носов А.В.
 */
public class createConfigFiles {
    
    private static final Logger log = Logger.getLogger(createConfigFiles.class);
    private static String userDir = System.getProperty("user.dir") + File.separator;
    private static String zulu = "zulu.properties";
    private static String systems = "systems.properties";
    private static String eth0 = "eth0.properties";
    private static String usergroup = "usergroup.properties";
    
    public static void main(String[] args) throws ExceptionsLibDataBus {
        log.info(userDir);
        Properties prop;
        String comment = "";
        
        // Zulu
        prop = new Properties();
        comment = " Create Zulu properties.\n"
                + "   server=" + TypeProperties.INTERNAL.name() + ","
                               + TypeProperties.TEST.name()
                ;
        prop.setProperty("server", TypeProperties.TEST.name());
        ReadProperties.saveProperties(userDir+zulu, prop, comment);
        
        // systems
        prop = new Properties();
        comment = "";
        prop.setProperty(Systems.names.hostname.name(), "Test mashine");
        prop.setProperty(Systems.names.productID.name(), "ZU-010814-A");
        prop.setProperty(Systems.names.serialNumber.name(), "1122334455");
        prop.setProperty(Systems.names.software.name(), "0.0.0.1");
        prop.setProperty(Systems.names.contact.name(), "Нет никого");
        prop.setProperty(Systems.names.location.name(), "Москва");
        ReadProperties.saveProperties(userDir+systems, prop, comment);
        
        // eth0
        prop = new Properties();
        comment = " Create eth0 for test properties.\n"
                + "   " + Ethernet.names.BOOTPROTO.name() + "="
                            + Ethernet.valueBOOTPROTO.DHCP.name() + ","
                            + Ethernet.valueBOOTPROTO.ETH0.name() + "\n"
                + "   " + Ethernet.names.DHCP_HOSTNAME.name() + "="
                            + "<имя машины без домена>"
                ;
        prop.setProperty(Ethernet.names.BOOTPROTO.name(), Ethernet.valueBOOTPROTO.ETH0.name());
        prop.setProperty(Ethernet.names.DHCP_HOSTNAME.name(), "");
        prop.setProperty(Ethernet.names.IPv4.name(), "192.168.0.5");
        prop.setProperty(Ethernet.names.IPv6.name(), "");
        prop.setProperty(Ethernet.names.GETWAY.name(), "225.225.225.0");
        prop.setProperty(Ethernet.names.MASK.name(), "192.168.0.1");
        prop.setProperty(Ethernet.names.MAC.name(), "90:2C:30:32:86:3C");
        ReadProperties.saveProperties(userDir+eth0, prop, comment);
        
        // usergroup
        prop = new Properties();
        comment = "";
        prop.setProperty(UserGroup.names.group_default.name(), "monitoring");
        prop.setProperty(UserGroup.names.groups.name(), "systems,interface,mirroring,firewall,nat,security,usergroup,tools");
        ReadProperties.saveProperties(userDir+usergroup, prop, comment);
    }
}
