package dslite.protocol;

import java.io.Serializable;

public class Message<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private T content;

    public Message(MessageType type, T content) {
        this.type = type;
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public T getContent() {
        return content;
    }
}