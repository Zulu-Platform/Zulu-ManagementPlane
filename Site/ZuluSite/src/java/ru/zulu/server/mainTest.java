/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import ru.zulu.client.gui.types.ITypeMenu;
import ru.zulu.client.gui.types.TypeMenu;

/**
 *
 * @author nosov
 */
public class mainTest {

    private static final Logger log = Logger.getLogger(mainTest.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TypeMenu tm = TypeMenu.Download;
        TypeMenu[] tms = TypeMenu.values();
        
        log.info(tm.name()+" "+tm.getCode()+" "+tm.getDescription());
        log.info(Arrays.toString(tms));
        log.info(tms[8]);
        
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
}
