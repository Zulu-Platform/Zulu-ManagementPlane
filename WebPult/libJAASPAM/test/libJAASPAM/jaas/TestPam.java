package libJAASPAM.jaas;


import java.util.Arrays;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.jvnet.libpam.PAM;
import org.jvnet.libpam.PAMException;
import org.jvnet.libpam.UnixUser;

/**
 *
 * @author Носов А.В.
 */
public class TestPam {
    
    private static final Logger log = Logger.getLogger(TestPam.class);
    
    public TestPam() {
        String name = "user01";
        String pass = "user01";
        
        try {
            PAM pam = new PAM("pam-login");
            UnixUser uu = pam.authenticate(name, pass);
            log.info(uu.getUserName()+":"+uu.getUID()+":"+uu.getGroups().size()+
                    ":"+uu.getGecos());
            log.info(Arrays.toString(uu.getGroups().toArray()));
            if (uu.getGroups() != null) {
                Iterator<String> it = uu.getGroups().iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    log.info("--- "+next);
                }
            }
        } catch (PAMException ex) {
            log.error(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new TestPam();
    }
}
