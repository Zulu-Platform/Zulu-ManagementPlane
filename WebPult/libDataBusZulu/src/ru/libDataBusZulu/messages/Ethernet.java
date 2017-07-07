/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.messages;

import java.util.Properties;
import libMessage.client.messages.SystemsSettings;
import org.apache.log4j.Logger;
import ru.libDataBusZulu.exceptions.ExceptionsLibDataBus;
import ru.libDataBusZulu.utils.ReadProperties;

/**
 *
 * @author Носов А.В.
 */
public class Ethernet {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(UserGroup.class);
    public static final String path = "/etc/zulu/eth0.properties";
    // End of variables declaration
    
    /** Имена. */
    public static enum names {
        BOOTPROTO,
        DHCP_HOSTNAME,
        IPv4,
        IPv6,
        MASK,
        GETWAY,
        MAC;
    }
    
    /** Значения BOOTPROTO. */
    public static enum valueBOOTPROTO {
        DHCP,
        ETH0;
    }
    
    public SystemsSettings getEth0() {
        SystemsSettings ss = new SystemsSettings();
        try {
            Properties p = ReadProperties.loadProperties(path);
            ss.setIpv4(p.getProperty(names.IPv4.name(), ""));
            ss.setMask(p.getProperty(names.MASK.name(), ""));
            ss.setGetway(p.getProperty(names.GETWAY.name(), ""));
            ss.setMac(p.getProperty(names.MAC.name(), ""));
        } catch (ExceptionsLibDataBus ex) {
            log.error(ex.getMessage());
        }
        return ss;
    }
}
