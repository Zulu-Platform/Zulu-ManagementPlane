/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zulucli;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nosov
 */
public class JCommanderSettings {
    
    // Variables declaration
    @Parameter
    private List<String> parameters = new ArrayList<String>();

    @Parameter(names = { "--help", "-h" }, description = "Help message")
    private boolean help;
    
    @Parameter(names = { "--log", "--verbose", "-l"}, description = "Level of verbosity")
    private Integer verbose = 1;

    @Parameter(names = {"--groups", "-g"}, description = "Comma-separated list of group names to be run")
    private String groups;
    
    @Parameter(names = {"--files", "-f"}, arity = 2, description = "Files")
    private List<String> files = new ArrayList<String>();
    
    @Parameter(names = {"--debug", "-d"}, description = "Debug mode")
    private boolean debug = false;
    
    @Parameter(names = {"--exit", "-e", "-q"}, description = "Exit")
    private boolean exit = false;
    // End of variables declaration

    public List<String> getParameters() {
        return parameters;
    }

    public Integer getVerbose() {
        return verbose;
    }

    public String getGroups() {
        return groups;
    }

    public boolean isDebug() {
        return debug;
    }

    public List<String> getFiles() {
        return files;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isHelp() {
        return help;
    }
}
