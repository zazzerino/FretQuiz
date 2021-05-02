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

    public void save(String gameId, ChatMessage message)
    {
        messages.computeIfAbsent(gameId, _key -> new ArrayList<>());
    }
}
