/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zulucli;

import java.io.File;
import java.util.*;
import org.kohsuke.args4j.*;
 
/**
 * Example of using Args4j for parsing
 * Ant command line options
 */
public class Args4jAntOpts {
 
  @Argument(metaVar = "[target [target2 [target3] ...]]", usage = "targets")
  private List<String> targets = new ArrayList<String>();
 
  @Option(name = "-h", aliases = "-help", usage = "print this message")
  private boolean help = false;
 
  @Option(name = "-lib", metaVar = "<path>",
          usage = "specifies a path to search for jars and classes")
  private String lib;
 
  @Option(name = "-f", aliases = { "-file", "-buildfile" }, metaVar = "<file>",
          usage = "use given buildfile")
  private File buildFile;
 
  @Option(name = "-nice", metaVar = "number",
          usage = "A niceness value for the main thread:\n"
          + "1 (lowest) to 10 (highest); 5 is the default")
  private int nice = 5;
 
  private Map<String, String> properties = new HashMap<String, String>();
  @Option(name = "-D", metaVar = "<property>=<value>",
          usage = "use value for given property")
  private void setProperty(final String property) throws CmdLineException {
    String[] arr = property.split("=");
    if(arr.length != 2) {
        throw new CmdLineException("Properties must be specified in the form:"+
                                   "<property>=<value>");
    }
    properties.put(arr[0], arr[1]);
  }
 
  public static void main(String[] args) throws CmdLineException {
    final String[] argv = { "-D", "key=value", "-f", "build.xml", "-h",
                            "-D", "key2=value2", "clean", "install", "test3", "test4" };
    final Args4jAntOpts options = new Args4jAntOpts();
    final CmdLineParser parser = new CmdLineParser(options);
    parser.parseArgument(argv);
 
    // print usage
    //parser.setUsageWidth(Integer.MAX_VALUE);
    
    // check the options have been set correctly
    System.out.println(options.buildFile.getName());
    System.out.println(options.properties.size());
    Collection<String> col = options.properties.values();
    for (String s : col)
        System.out.println(s);
    
    System.out.println(options.targets.size());
    for (String s : options.targets)
        System.out.println(s);
    
    if (options.help)
        parser.printUsage(System.err);
  }
}
