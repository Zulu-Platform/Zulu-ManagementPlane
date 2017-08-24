
package libJAASPAM.jaas;

import java.io.IOException;
import java.util.logging.Logger;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class UsernamePasswordCallbackHandler extends Object implements CallbackHandler {
    
    // Variables declaration
    private final String _user;
    private final String _password;
    // End of variables declaration

    public UsernamePasswordCallbackHandler(String user, String password) {
        super();
        _user = user;
        _password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof TextOutputCallback) {
                // display the message according to the specified type
                TextOutputCallback toc = (TextOutputCallback) callback;
                switch (toc.getMessageType()) {
                    case TextOutputCallback.INFORMATION:
                        System.out.println(toc.getMessage());
                        break;
                    case TextOutputCallback.ERROR:
                        System.out.println("ERROR: " + toc.getMessage());
                        break;
                    case TextOutputCallback.WARNING:
                        System.out.println("WARNING: " + toc.getMessage());
                        break;
                    default:
                        throw new IOException("Unsupported message type: " + toc.getMessageType());
                }
            } else if (callback instanceof NameCallback) {
                handleName((NameCallback)callback);
            } else if (callback instanceof PasswordCallback) {
                handlePassword((PasswordCallback)callback);
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
//        for (int i = 0; i < callbacks.length; i++) {
//            if (callbacks[i] instanceof TextOutputCallback) {
//                // display the message according to the specified type
//                TextOutputCallback toc = (TextOutputCallback) callbacks[i];
//                switch (toc.getMessageType()) {
//                    case TextOutputCallback.INFORMATION:
//                        System.out.println(toc.getMessage());
//                        break;
//                    case TextOutputCallback.ERROR:
//                        System.out.println("ERROR: " + toc.getMessage());
//                        break;
//                    case TextOutputCallback.WARNING:
//                        System.out.println("WARNING: " + toc.getMessage());
//                        break;
//                    default:
//                        throw new IOException("Unsupported message type: " + toc.getMessageType());
//                }
//    //        } else if (callbacks[i] instanceof NameCallback) {
//    //            // prompt the user for a username
//    //            NameCallback nc = (NameCallback) callbacks[i];
//    //            nc.setName(_user);
//    //        } else if (callbacks[i] instanceof PasswordCallback) {
//    //            // prompt the user for sensitive information
//    //            PasswordCallback pc = (PasswordCallback) callbacks[i];
//    //            pc.setPassword(_password.toCharArray());
//            } else {
//                throw new UnsupportedCallbackException (callbacks[i], "Unrecognized Callback");
//            }
//        }

    }

    private void handleName(NameCallback callback) {
        callback.setName(_user);
    }

    private void handlePassword(PasswordCallback callback) {
        char[] passwordChars = _password.toCharArray();
        callback.setPassword(passwordChars);
    }
}
