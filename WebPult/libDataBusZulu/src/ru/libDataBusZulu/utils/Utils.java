/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

/**
 *
 * @author Носов А.В.
 */
public class Utils {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(Utils.class);
    // End of variables declaration
    
    /**
     * Запуск комендной строки.
     * @param str команда
     * @return ответ
     */
    public static Shell getStartRuntime(String str) {
        Shell shell = new Shell("", "Error runtime", "", -1);
        InputStream stderr = null;
        InputStreamReader stdrerr = null;
        BufferedReader buferr = null;
        
        InputStream std = null;
        InputStreamReader stdr = null;
        BufferedReader buf = null;
            
        try {
            if (log.isTraceEnabled()) log.trace("Shell#: "+str);
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(new String[] {"/bin/bash", "-c", str});
            // Error stream
            stderr = proc.getErrorStream();
            stdrerr = new InputStreamReader(stderr);
            buferr = new BufferedReader(stdrerr);
            String line;
            
            String error = buferr.readLine();
            shell.setMessage(error);
            while ( (line = buferr.readLine()) != null)
                error = line + "\n";
            
            shell.setError(error);
            // Success stream
            std = proc.getInputStream();
            stdr = new InputStreamReader(std);
            buf = new BufferedReader(stdr);
            
            line = null;
            String success = buf.readLine();
            
            while ( (line = buf.readLine()) != null)
                success = line + "\n";
            if (log.isTraceEnabled()) log.trace("Info buf:"+success+"; Error buf:"+error);
            shell.setOutput(success);
            // Exit
            shell.setExitVal(proc.waitFor());
            
            return shell;
        } catch (IOException | InterruptedException ex) {
            log.error(ex.getMessage());
            shell.setError(ex.getMessage());
            return shell;
        } finally {
            try {
                if (stderr!=null) stderr.close();
                if (stdrerr!=null) stdrerr.close();
                if (buferr!=null) buferr.close();
                if (std!=null) std.close();
                if (stdr!=null) stdr.close();
                if (buf!=null) buf.close();
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
