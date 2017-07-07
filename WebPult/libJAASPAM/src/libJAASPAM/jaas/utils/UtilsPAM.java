/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libJAASPAM.jaas.utils;

import org.jvnet.libpam.PAMException;
import org.jvnet.libpam.UnixUser;
import libJAASPAM.jaas.principals.UserPamPrincipal;

/**
 *
 * @author Носов А.В.
 */
public class UtilsPAM {
    
    
    public UtilsPAM() {
    }
    
    public static UserPamPrincipal getUser(String userName) {
        try {
            UnixUser uu = new UnixUser(userName);
            return new UserPamPrincipal(uu);
        } catch (PAMException ex) {
            return null;
        }
    }
}
