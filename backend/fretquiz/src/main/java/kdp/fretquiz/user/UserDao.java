package kdp.fretquiz.user;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A user data access object.
 */
public class UserDao
{
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public Collection<User> getAllUsers()
    {
        return users.values();
    }

    public User getUserById(String userId)
    {
        return Objects.requireNonNull(users.get(userId));
    }

    public String getSessionId(String userId)
    {
        return getUserById(userId).sessionId;
    }

    public List<String> getSessionIds(List<String> userIds)
    {
        return userIds.stream()
                .map(this::getSessionId)
                .toList();
    }

    public void save(User user)
    {
        users.put(user.id, user);
    }

    public void delete(String id)
    {
        users.remove(id);
    }
}
