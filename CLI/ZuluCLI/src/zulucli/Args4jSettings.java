/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zulucli;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 *
 * @author nosov
 */
public class Args4jSettings {
    
    // Variables declaration
    @Option(name="-x", aliases = {"-xx", "X"}, usage="Доп команда X")
    private boolean x = false;

    @Option(name="-y", aliases = {"-yy", "Y"}, usage="Доп команда Y")
    private boolean y = false;
    
    @Option(name = "--help", aliases={"-?", "-h"}, usage = "Печатает это сообщение")
    private boolean help;
    
    @Option(name="--exit", aliases = {"-e", "-q"}, metaVar = "exit", usage="Выход")
    private boolean exit = false;
    
    @Argument(index = 1, usage = "My first option enum")
    private Args4jMyOptions myOptions = Args4jMyOptions.FEATURES;
    
    @Argument
    private List<String> arguments = new ArrayList<String>();
    // End of variables declaration
    
    public boolean isX() {
        return x;
    }
    
    public boolean isY() {
        return y;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isHelp() {
        return help;
    }

    public Args4jMyOptions getMyOptions() {
        return myOptions;
    }

    public List<String> getArguments() {
        return arguments;
    }
    
}
