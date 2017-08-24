// $Id$

package libJAASPAM.jaas;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jvnet.libpam.PAM;
import org.jvnet.libpam.PAMException;
import org.jvnet.libpam.UnixUser;
import libJAASPAM.jaas.principals.GroupPamPrincipal;
import libJAASPAM.jaas.principals.UserPamPrincipal;

/**
 * Вызов модуля авторизации.
 * -Djava.security.auth.login.config=${src.dir}/login.config 
 * /media/nosov/WorkDisk/java/Pult/Zulu/5_Software/WebPult/trunk/WebZulu/
 * java.security.auth.login.config - настройка в tomcat
 * @author Носов А.В.
 */
public class PamLoginModule extends Object implements LoginModule {
    
    // Variables declaration
    public static final String SERVICE_KEY = "service";

    private PAM pam;
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> options;

    /** Имя пользователя. */
    private String username;
    /** Пароль пользователя. */
    private String password;

    /** Авторизация пройдена. */
    private boolean authSucceeded;
    /** Пользователь. */
    private UserPamPrincipal userPrincipal;
    /** Группа. */
    private GroupPamPrincipal groupPrincipal;
    // End of variables declaration

    public PamLoginModule() {
        super();
        this.authSucceeded = false;
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, 
                           Map<String, ?> sharedState, Map<String, ?> options) {
        
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.options = new HashMap<>(options);
    }

    @Override
    public boolean login() throws LoginException {
        initializePam();
        obtainUserAndPassword();
        return performLogin();
    }

    private void initializePam() throws LoginException {
        String service = (String)options.get(SERVICE_KEY);
        if (service == null) {
            throw new LoginException("Error: PAM service was not defined");
        }
        createPam(service);
    }

    private void createPam(String service) throws LoginException {
        try {
            pam = new PAM(service);
        } catch (PAMException ex) {
            LoginException le = new LoginException("Error initializing PAM");
            le.initCause(ex);
            throw le;
        }
    }

    private void obtainUserAndPassword() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available  to gather authentication information from the user");
        }

        try {
            NameCallback nameCallback = new NameCallback("username");
            PasswordCallback passwordCallback = new PasswordCallback("password", false);

            invokeCallbackHandler(nameCallback, passwordCallback);

            initUserName(nameCallback);
            initPassword(passwordCallback);
        }
        catch (IOException | UnsupportedCallbackException ex) {
            LoginException le = new LoginException("Error in callbacks");
            le.initCause(ex);
            throw le;
        }
    }

    private void invokeCallbackHandler(NameCallback nameCallback, PasswordCallback passwordCallback) 
            throws IOException, UnsupportedCallbackException {
        
        Callback[] callbacks = new Callback[2];
        callbacks[0] = nameCallback;
        callbacks[1] = passwordCallback;

        callbackHandler.handle(callbacks);
    }

    private void initUserName(NameCallback nameCallback) {
        username = nameCallback.getName();
    }

    private void initPassword(PasswordCallback passwordCallback) {
        char[] passwordCB = passwordCallback.getPassword();
        this.password = new String(passwordCB);

        passwordCallback.clearPassword();
    }

    private boolean performLogin() throws LoginException {
        try {
            UnixUser user = pam.authenticate(username, password);
            userPrincipal = new UserPamPrincipal(user);
            authSucceeded = true;

            return true;
        } catch (PAMException ex) {
            LoginException le = new FailedLoginException("Invalid username or password");
            le.initCause(ex);
            throw le;
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (authSucceeded == false) {
            return false;
        }

        if (subject.isReadOnly()) {
            cleanup();
            throw new LoginException("Subject is read-only");
        }

        Set<Principal> principals = subject.getPrincipals();
        if (principals.contains(userPrincipal) == false) {
            principals.add(userPrincipal);
        }

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        if (authSucceeded == false) {
            return false;
        }

        cleanup();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        if (subject.isReadOnly()) {
            cleanup();
            throw new LoginException("Subject is read-only");
        }

        subject.getPrincipals().remove(userPrincipal);

        cleanup();
        return true;
    }

    private void cleanup() {
        authSucceeded = false;
        username = null;
        password = null;
        userPrincipal = null;
        pam.dispose();
    }
}
