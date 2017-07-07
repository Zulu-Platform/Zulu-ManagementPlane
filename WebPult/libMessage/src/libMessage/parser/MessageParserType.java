package libMessage.parser;

import libMessage.client.types.TypeMessage;

/**
 * Сообщение, возвращающее тип message.
 * @author Носов А.В.
 */
public class MessageParserType {

    // Variables declaration
    private TypeMessage typeMessage;
    // End of variables declaration

    public MessageParserType() {
    }

    /**
     * Возвращает тип сообщения.
     * @return тип сообщения
     */
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    /**
     * Устанавливает тип сообщения.
     * @param typeMessage тип сообщения
     */
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

}
