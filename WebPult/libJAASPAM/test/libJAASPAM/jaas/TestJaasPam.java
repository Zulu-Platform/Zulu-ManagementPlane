package libJAASPAM.jaas;

import java.security.Principal;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.apache.log4j.Logger;
import libJAASPAM.jaas.principals.UserPamPrincipal;

/**
 *
 * @author Носов А.В.
 */
public class TestJaasPam {
    
    private static final Logger log = Logger.getLogger(TestJaasPam.class);
    
    public TestJaasPam() {
        String name = "admin";
        String pass = "admin";
//        String name = "user01";
//        String pass = "user01";
        try {
            log.debug("Login DLL:"+System.getProperty("java.security.auth.login.config"));
            CallbackHandler handler = new UsernamePasswordCallbackHandler(name, pass);
            LoginContext context = new LoginContext("pam-login", handler);
            context.login();
            Subject subject = context.getSubject();
            
            Set principals = subject.getPrincipals();
            Iterator i = principals.iterator();
            while (i.hasNext()) {
                Principal p = (Principal) i.next();
                log.info(p.getName());
                if (p instanceof UserPamPrincipal) {
                    UserPamPrincipal pp = (UserPamPrincipal) p;
                    log.info(pp.getName() +":"+pp.getUid()+":"+pp.getGroups().size());
                    if (pp.getGroups() == null) continue;
                    log.info("Group list:");
                    for (String group : pp.getGroups())
                        log.info("\t"+group);
                } else { log.error("no"); }
            }
        } catch (LoginException ex) {
            log.error("LoginException:"+ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new TestJaasPam();
    }
}
