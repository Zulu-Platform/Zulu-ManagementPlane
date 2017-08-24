/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libJAASPAM.jaas;

import org.apache.log4j.Logger;
import org.jvnet.libpam.PAMException;
import org.jvnet.libpam.UnixUser;

/**
 *
 * @author Носов А.В.
 */
public class TestUnixUser {
    
    private static final Logger log = Logger.getLogger(TestUnixUser.class);
    private String userName = "test01";
    
    public TestUnixUser() {
        try {
            UnixUser uu = new UnixUser(userName);
            log.info("UserName:"+uu.getUserName());
            log.info("UID:"+uu.getUID());
            log.info("GID:"+uu.getGID());
            log.info("Groups:"+uu.getGroups());
            log.info("Gecos:"+uu.getGecos());
            log.info("Shell:"+uu.getShell());
            log.info("Dir:"+uu.getDir());
        } catch (PAMException ex) {
            log.error(ex.getMessage());
        }
        
//        log.info("=====");
//        UserPamPrincipal uu = Utils.getUser(userName);
//        log.info("UserName:"+uu.getName());
//        log.info("UID:"+uu.getUid());
//        log.info("GID:"+uu.getGid());
//        log.info("Groups:"+uu.getGroups());
//        log.info("Gecos:"+uu.getGecos());
//        log.info("Shell:"+uu.getShell());
//        log.info("Dir:"+uu.getHomeDir());
    }
    
    public static void main(String[] args) {
        new TestUnixUser();
    }
}
