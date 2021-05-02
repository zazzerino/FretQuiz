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

    public void add(String gameId, ChatMessage message)
    {
        messages.computeIfAbsent(gameId, _key -> new ArrayList<>());
        final var gameMessages = messages.get(gameId);
        gameMessages.add(message);
    }

    public void delete(String gameId)
    {
        messages.remove(gameId);
    }

    public List<ChatMessage> getMessages(String gameId)
    {
        return messages.get(gameId);
    }
}
