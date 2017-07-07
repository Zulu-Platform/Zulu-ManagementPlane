package libMessage.parser;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import libMessage.client.Message;
import libMessage.client.messages.User;
import org.apache.log4j.Logger;
import libMessage.exceptions.ErrorsMessage;
import libMessage.exceptions.ExceptionsMessage;

/**
 * Объект, возвращающий сообщение по его типу.
 */
public class MessageParser {

    // Variables declaration
    private static final Logger log = Logger.getLogger(MessageParser.class);
    // End of variables declaration

    /**
     * Преобразование из строки в Message с помощью JSON;
     * @param msg строка
     * @return сообщение
     * @throws ExceptionsMessage исключение
     */
    public static Message parseJSON(String msg) throws ExceptionsMessage {
        try {
            Gson gson = new Gson();
            MessageParserType messageType = gson.fromJson(msg, MessageParserType.class);
            
            if (messageType == null || messageType.getTypeMessage() == null) {
                ExceptionsMessage ex = new ExceptionsMessage(ErrorsMessage.WARNING_UNPROCESSED_TYPE);
                log.error(ex.getMessage());
                throw ex;
            }

            switch (messageType.getTypeMessage()) {
                case UserAdd:
                    return gson.fromJson(msg, User.class);
                case UserRemove:
                    return gson.fromJson(msg, User.class);
                default:
                    ExceptionsMessage ex = new ExceptionsMessage(ErrorsMessage.WARNING_UNKNOWN_TYPE);
                    log.error(ex.getMessage());
                    throw ex;
            }

        } catch (JsonParseException jpe) {
            ExceptionsMessage ex = new ExceptionsMessage(ErrorsMessage.ERROR_JSON);
            log.error(msg + "\n" + jpe.getMessage());
            throw ex;
        }
    }
}
