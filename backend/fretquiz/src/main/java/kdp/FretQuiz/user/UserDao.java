package kdp.FretQuiz.user;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A user data access object.
 */
public class UserDao {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User getById(String userId) {
        return Objects.requireNonNull(users.get(userId));
    }

//    public User getBySessionId(String sessionId) {
//        return users.values()
//                .stream()
//                .filter(user -> user.sessionId.equals(sessionId))
//                .findFirst()
//                .orElseThrow(NoSuchElementException::new);
//    }

    public String getSessionId(String userId) {
        return getById(userId).sessionId;
    }

    public List<String> getSessionIds(List<String> userIds) {
        return userIds.stream()
                .map(this::getSessionId)
                .toList();
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public void save(User user) {
        users.put(user.id, user);
    }

    public void delete(String id) {
        users.remove(id);
    }
}
