/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zulucli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;
import org.kohsuke.args4j.spi.FieldSetter;
import org.kohsuke.args4j.spi.OptionImpl;
import org.kohsuke.args4j.spi.Setter;

/**
 * Комендная строка.
 * @author Носов А.В.
 */
public class ZuluCLI {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(ZuluCLI.class);
    // End of variables declaration
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) {
//        new ZuluCLI().LoadCLIargs4j(args);
        System.out.println("-g AAA -l 32 -d commit --author=PIPKA --amend a.java b.java b.java");
        new ZuluCLI().LoadFishjcommander(args);
    }
    
    private void LoadFishjcommander(String[] args) {
        boolean done = true;
        
        do {
            
            try {
                JCommanderSettings jcs = new JCommanderSettings();
                JCommander parser = new JCommander(jcs);
                parser.setProgramName("Zulu");
                
                JCommandCommit commit = new JCommandCommit();
                parser.addCommand("commit", commit);
                
                parser.parse(args);
                
                log.info("Groups: " + jcs.getGroups());
                log.info("Log: " + jcs.getVerbose());
                log.info("Debug: " + jcs.isDebug());

                for (String str : jcs.getParameters())
                    log.info("List: " + str);

                for (String str : jcs.getFiles())
                    log.info("Files: " + str);
                
                
                log.info("--- COMMIT ---");
                log.info("Author: " + commit.getAuthor());
                log.info("Amend: " + commit.getAmend());
                if (commit.getFiles() != null)
                    for (String str : commit.getFiles())
                        log.info("Files: " + str);
                
                if (jcs.isExit()) done = false;
                
                if (jcs.isHelp()) parser.usage();
            } catch (ParameterException ex) {
                System.out.println(ex.getLocalizedMessage());
                
            }
            
            // Чтение данных из консоли
            if (!done) continue;
            try {
                BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
                String str = inu.readLine();
                args = str.split(" ");
            } catch (IOException ex) {
                log.error("Ошибка чтения командной строки", ex);
            }
        } while (done);
        
        System.exit(0);
    }
    
    private void LoadCLIargs4j(String[] args) {
        String line = "";
        CmdLineParser parser;
        boolean done = true;
        
        ParserProperties pp = ParserProperties.defaults();
        pp.withShowDefaults(false);
        
        do {
            Args4jSettings st = new Args4jSettings();
            parser = new CmdLineParser(st, pp);
            try {
                log.debug("Строка:" + line + ";");
                
                parser.parseArgument(args);
                log.info("X=" + st.isX());
                log.info("Y=" + st.isY());
                log.info("Exit=" + st.isExit());
                
                Args4jMyOptions mo = st.getMyOptions();
                
                log.info("MO=" + mo.name());
                
                List<String> arguments = st.getArguments();
                for (String argument : arguments) {
                    log.info("Settings other: "+argument);
                }
                
                if (st.isExit()) done = false;
            } catch(CmdLineException ex) {
                System.out.println(ex.getLocalizedMessage());
                parser.printUsage(System.out);
            }
            
            // Чтение данных из консоли
            if (!done) continue;
            try {
                BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
                line = inu.readLine();
                args = line.split(" ");
            } catch (IOException ex) {
                log.error("Ошибка чтения командной строки", ex);
            }
        } while (done);
        
        System.exit(0);
    }
    
    private void LoadFish(String[] args) {
        boolean done = true;
        
        do {

            
            // Чтение данных из консоли
            if (!done) continue;
            try {
                BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
                String str = inu.readLine();
                args = str.split(" ");
            } catch (IOException ex) {
                log.error("Ошибка чтения командной строки", ex);
            }
        } while (done);
        
        System.exit(0);
    }
}
