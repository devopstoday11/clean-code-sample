package at.pasztor.cleancodesample.user.storage;

import at.pasztor.cleancodesample.user.entity.User;
import at.pasztor.cleancodesample.user.exception.UserAlreadyExists;
import at.pasztor.cleancodesample.user.exception.UserNotFound;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserStorage implements UserStorage {
    private final Map<String, User> usersByEmail = new HashMap<>();

    @Override
    public void create(final User user) throws UserAlreadyExists {
        createAndReturn(user);
    }

    @Override
    public synchronized User createAndReturn(final User user) throws UserAlreadyExists {
        if (usersByEmail.containsKey(user.email)) {
            throw new UserAlreadyExists();
        }
        usersByEmail.put(user.email, user);
        return user;
    }

    public synchronized User getUserByEmail(String email) throws UserNotFound {
        if (usersByEmail.containsKey(email)) {
            return usersByEmail.get(email);
        }
        throw new UserNotFound();
    }
}
