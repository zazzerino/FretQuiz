package kdp.fretquiz.websocket.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultMessage {
    private Message.Type type;

    public DefaultMessage() {}

    public DefaultMessage(Message.Type type) {
        this.type = type;
    }

    public Message.Type getType() {
        return type;
    }

    public void setType(Message.Type type) {
        this.type = type;
    }
}
