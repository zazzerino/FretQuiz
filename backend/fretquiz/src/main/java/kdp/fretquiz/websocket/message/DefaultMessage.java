package kdp.fretquiz.websocket.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DefaultMessage(Type type) implements Message {
}
