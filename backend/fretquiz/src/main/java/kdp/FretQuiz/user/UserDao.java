package kdp.FretQuiz.user;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserDao {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public void save(User user) {
        users.put(user.id(), user);
    }

    public void delete(String id) {
        users.remove(id);
    }
}
