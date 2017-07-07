/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.messages;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import libMessage.client.messages.SystemsSettings;
import org.apache.log4j.Logger;
import ru.libDataBusZulu.exceptions.ErrorsLibDataBus;
import ru.libDataBusZulu.exceptions.ExceptionsLibDataBus;
import static ru.libDataBusZulu.messages.Ethernet.path;
import ru.libDataBusZulu.utils.ReadProperties;
import ru.libDataBusZulu.utils.Shell;
import ru.libDataBusZulu.utils.Utils;

/**
 * Системные данные.
 * @author Носов А.В.
 */
public class Systems {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(Systems.class);
    private final String path = "/etc/zulu/systems.properties";
    // End of variables declaration
    
    /** Имена. */
    public static enum names {
        hostname,
        productID,
        serialNumber,
        software,
        contact,
        location;
    }
    
    public Systems() {
    }
    
    /**
     * Возвращает системные настройки Ethernet.
     * @return системные настройки
     */
    public SystemsSettings getSystemsSettings() {
        SystemsSettings ss = new SystemsSettings();
        try {
            Properties p = ReadProperties.loadProperties(path);
            ss.setHostname(p.getProperty(names.hostname.name(), ""));
            ss.setProductID(p.getProperty(names.productID.name(), ""));
            ss.setSerialnumber(p.getProperty(names.serialNumber.name(), ""));
            ss.setSoftware(p.getProperty(names.software.name(), ""));
            ss.setContact(p.getProperty(names.contact.name(), ""));
            ss.setLocation(p.getProperty(names.location.name(), ""));
            
            ss.setDate(new Date(System.currentTimeMillis()));
            
            Shell shellSSH = Utils.getStartRuntime("/etc/init.d/sshd status");
            if ( (shellSSH.getMessage() != null) && (shellSSH.getMessage().indexOf("running") > 1) )
                ss.setSsh(true);
            Shell shellSNMP = Utils.getStartRuntime("/etc/init.d/snmpd status");
            if ( (shellSNMP.getMessage() != null) && (shellSNMP.getMessage().indexOf("running") > 1) )
                ss.setSnmp(true);
            Shell shellTelnet = Utils.getStartRuntime("/etc/init.d/telnetd status");
            if ( (shellTelnet.getMessage() != null) && (shellTelnet.getMessage().indexOf("running") > 1) )
                ss.setTelnet(true);
            
            // Ethernet
            p = ReadProperties.loadProperties(Ethernet.path);
            ss.setIpv4(p.getProperty(Ethernet.names.IPv4.name(), ""));
            ss.setMask(p.getProperty(Ethernet.names.MASK.name(), ""));
            ss.setGetway(p.getProperty(Ethernet.names.GETWAY.name(), ""));
            ss.setMac(p.getProperty(Ethernet.names.MAC.name(), ""));
            
        } catch (ExceptionsLibDataBus ex) {
            log.error(ex.getMessage());
        }
        return ss;
    }
    
    /**
     * Редактирование системных настроек.
     * При успешном редактировании необходимо перегрузить систему.
     * @param ss системные настройки
     * @return результат
     */
    public String editSystemSettings(SystemsSettings ss) {
        if (ss == null)
            return ErrorsLibDataBus.ERROR_SYSTEMSETTINGS.getDescription();
        
        try {
            Properties p = new Properties();
            p.getProperty(names.hostname.name(), ss.getHostname());
            p.getProperty(names.contact.name(), ss.getContact());
            p.getProperty(names.location.name(),ss.getLocation());
            
            // Time
            
            
            // Daemons
            if (ss.isSsh())
                Utils.getStartRuntime("/etc/init.d/sshd start");
            else Utils.getStartRuntime("/etc/init.d/sshd stop");
            if (ss.isSnmp())
                Utils.getStartRuntime("/etc/init.d/snmpd start");
            else Utils.getStartRuntime("/etc/init.d/snmpd stop");
            if (ss.isTelnet())
                Utils.getStartRuntime("/etc/init.d/telnetd start");
            else Utils.getStartRuntime("/etc/init.d/telnetd stop");
            
            // Ethernet
            p = new Properties();
            p.getProperty(Ethernet.names.IPv4.name(), ss.getIpv4());
            p.getProperty(Ethernet.names.MASK.name(), ss.getMask());
            p.getProperty(Ethernet.names.GETWAY.name(), ss.getGetway());
            p.getProperty(Ethernet.names.MAC.name(), ss.getMac());
            ReadProperties.saveProperties(Ethernet.path, p, null);
            
        } catch (ExceptionsLibDataBus ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
    
    private SystemsSettings getEthByName(String name) {
        SystemsSettings ss = new SystemsSettings();
        try {
            // IP
            InetAddress ip = InetAddress.getByName(name);
            ss.setIpv4(ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            // MAC
            if (network.getHardwareAddress() != null) {
                byte[] mac = network.getHardwareAddress();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));        
                }
                ss.setMac(sb.toString());
            }
            // Masck
            List<InterfaceAddress> list = network.getInterfaceAddresses();
            for (InterfaceAddress ia : list)
                log.info(ia.getAddress().getHostName()+":"+ia.getNetworkPrefixLength());
            // Getway
        } catch (UnknownHostException | SocketException ex) {
            log.fatal(ex.getMessage());
        }
        
        return ss;
    }
}
