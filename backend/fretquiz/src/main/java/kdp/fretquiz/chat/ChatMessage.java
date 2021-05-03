package kdp.fretquiz.chat;

import kdp.fretquiz.Util;
import kdp.fretquiz.user.User;

import java.time.Instant;

public record ChatMessage(String id,
                          String gameId,
                          User user,
                          String text,
                          String createdAt)
{
    public ChatMessage(String gameId, User user, String text)
    {
        this(Util.randomId(),
                gameId,
                user,
                text,
                Instant.now().toString());
    }
}
