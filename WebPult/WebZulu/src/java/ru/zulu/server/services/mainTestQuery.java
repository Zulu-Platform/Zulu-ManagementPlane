/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.server.services;

import com.sun.security.auth.NTUserPrincipal;
import com.sun.security.auth.UnixPrincipal;
import com.sun.security.auth.callback.TextCallbackHandler;
import com.sun.security.auth.module.UnixSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import ru.libDataBusZulu.messages.UserGroup;
import org.apache.log4j.Logger;

/**
 *
 * @author nosov
 */
public class mainTestQuery {

    private static final Logger log = Logger.getLogger(mainTestQuery.class);
    private static String userName = "nosov";
    private static String groupName = "monitoring";
//    private static String[] groupList = {"monitoring", "systems", "interface", "mirroring",
//                                  "firewall", "nat", "security", "usergroup",
//                                  "tools" };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
            String str = "usermod  -l \"user05\" -G monitoring,usergroup,mirroring,security,interface -c \"test 05 05\" -p $(openssl passwd -1 user06) user05";
            
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(str);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isrerr = new InputStreamReader(stderr);
            BufferedReader buferr = new BufferedReader(isrerr);
            
            String lineErr = "First error:"+buferr.readLine();
            log.info(lineErr);
            
            while ( (lineErr = buferr.readLine()) != null)
                log.info(lineErr);
            
            InputStream std = proc.getInputStream();
            InputStreamReader stdread = new InputStreamReader(std);
            BufferedReader buf = new BufferedReader(stdread);
            
            String line = "First line:"+buf.readLine();
            log.info(line);
            
            while ( (line = buf.readLine()) != null)
                log.info(line);
            
            int exitVal = proc.waitFor();
            log.info("Process exitValue: " + exitVal);
            log.info("----------------------------------");
            str = "getent group " + groupName;
            proc = rt.exec(str);
            std = proc.getErrorStream();
            isrerr = new InputStreamReader(std);
            buferr = new BufferedReader(isrerr);
            
            lineErr = "First error:"+buferr.readLine();
            log.info(lineErr);
            
            while ( (lineErr = buferr.readLine()) != null)
                log.info(lineErr);
            
            std = proc.getInputStream();
            stdread = new InputStreamReader(std);
            buf = new BufferedReader(stdread);
            
            line = "First line:"+buf.readLine();
            log.info(line);
            
            while ( (line = buf.readLine()) != null)
                log.info(line);
            
            exitVal = proc.waitFor();
            log.info("Process exitValue: " + exitVal);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } catch (InterruptedException ex) {
            log.error(ex.getMessage());
        }
        
//        try {
//            Process gp = Runtime.getRuntime().exec("getent group " + groupName);
//            BufferedReader gr = new BufferedReader(
//                    new InputStreamReader(gp.getInputStream())); 
//            String gl = gr.readLine();
//            if (gl == null) exit();
//            
//            String[] gs = gl.split("\\:");
//            if (gs.length != 4) exit();
//
//            String[] users = gs[3].split(",");
//            for (String user : users) {
//                log.info(user);
//            }
//            
//            gp = Runtime.getRuntime().exec("hostname -I");
//            gr = new BufferedReader(
//                    new InputStreamReader(gp.getInputStream())); 
//            String ipl = gr.readLine();
//            log.info(ipl);
//        } catch (IOException ex) {
//            log.debug(ex.getMessage());
//        }
//        startLinuxConsole();
//        getUnixUser();
//        testloginContext();
        
        exit();
    }
    
    private static void startLinuxConsole() {
        String osName = System.getProperty("os.name");
        if (osName == null) exit();
        if (osName.toLowerCase().startsWith("windows")) {
        } else if(osName.toLowerCase().startsWith("linux")) {

            getUserInfo4Zulu();
        } else {

        }
    }
    
    private static void getUserInfo4Zulu() {
        log.error("==========getUserInfo4Zulu===============");
        UserGroup userGroup = new UserGroup();
        //List<UnixUser> usersList = userGroup.getUserList();
        
        log.error("==========getUserInfo4Zulu===============");
    }
    
    private static void exit() {
        System.exit(0);
    }
    
    private static String[] mySplit(String str, String reg) {
        List<String> list = new ArrayList<String>();
        
        while (str.indexOf(reg) > 0) {
            list.add(str.substring(0, str.indexOf(reg)));
            str = str.substring(str.indexOf(reg)+reg.length());
        }
        list.add(str);
        
        String[] result = new String[list.size()];
        for (int i=0; i<result.length; i++)
            result[i] = list.get(i);
        return result;
    }
    
    public static boolean gettest() {
        String a1 = "";
        String a2 = "false";
        
        return !((a1 == null) || !(Boolean.parseBoolean(String.valueOf(a2))));
    }
    
    static class MyAction implements PrivilegedAction<Void> {
        @Override
        public Void run() {
            // Privileged code goes here, for example:
            System.loadLibrary("awt");
            return null; // nothing to return
        }
    }
    
    public static void getUnixUser() {
        log.error("==========getUnixUser===============");
        try {
            log.debug("Пользователь, под которым запущено приложение.");
            UnixSystem us = new UnixSystem();
            log.debug("Name:"+us.getUsername()+"; UID:"+us.getUid()+"");
            for (Long l : us.getGroups()) {
                log.debug(l);
            }
        } catch (SecurityException ex){
            log.debug("SecurityException: " + ex.getMessage());
        }
        log.error("==========getUnixUser===============");
    }
    
    private static void testloginContext() {
        log.error("==========testloginContext===============");
        try {
            LoginContext lc = new LoginContext(userName, new TextCallbackHandler());
            lc.login();
            Subject subject = lc.getSubject();
            log.info("Principals:"+subject.getPrincipals().size());
            Principal principals[] = (Principal[])subject.getPrincipals().toArray(new Principal[0]);

            for (int i=0; i<principals.length; i++) {
                if (principals[i] instanceof NTUserPrincipal || principals[i] instanceof UnixPrincipal) {
                    String loggedInUserName = principals[i].getName();
                    log.debug("username "+loggedInUserName);
                }
            }

        } catch (SecurityException ex){
            log.debug("SecurityException: " + ex.getMessage());
        } catch (LoginException ex) {
            log.debug("LoginException: " + ex.getMessage());
        }
        log.error("==========testloginContext===============");
    }
}
