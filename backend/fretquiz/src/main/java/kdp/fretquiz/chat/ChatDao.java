package kdp.fretquiz.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatDao
{
    /**
     * Maps a gameId to a list of that game's chat messages.
     */
    private final Map<String, List<ChatMessage>> messages = new ConcurrentHashMap<>();

    public void save(ChatMessage message)
    {
        final var gameId = message.gameId();
        messages.computeIfAbsent(gameId, _key -> new ArrayList<>());
        final var gameMessages = messages.get(gameId);
        gameMessages.add(message);
    }

    public void delete(ChatMessage message)
    {
        final var gameId = message.gameId();

        if (gameId == null) {
            return;
        }

        final var gameMessages = messages.get(gameId);
        gameMessages.removeIf(msg -> msg.id().equals(message.id()));
    }

    public void deleteAll(String gameId)
    {
        messages.remove(gameId);
    }

    public List<ChatMessage> getMessages(String gameId)
    {
        return messages.get(gameId);
    }
}
