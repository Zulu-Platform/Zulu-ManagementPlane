/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zulucli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.util.List;

/**
 *
 * @author nosov
 */
@Parameters(separators = "=", commandDescription = "Record changes to the repository")
public class JCommandCommit {
    
    @Parameter(description = "The list of files to commit")
    private List<String> files;

    @Parameter(names = {"--amend"}, description = "Amend")
    private Boolean amend = false;

    @Parameter(names = "--author", description = "Author")
    private String author;

    public List<String> getFiles() {
        return files;
    }

    public Boolean getAmend() {
        return amend;
    }

    public String getAuthor() {
        return author;
    }
    
}
