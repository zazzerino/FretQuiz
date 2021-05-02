package kdp.fretquiz.chat;

import kdp.fretquiz.user.User;

import java.time.Instant;

public record ChatMessage(User user,
                          String message,
                          Instant createdAt)
{

    public ChatMessage(User user, String message)
    {
        this(user, message, Instant.now());
    }
}
