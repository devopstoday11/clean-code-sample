package at.pasztor.cleancodesample.user.storage;

import at.pasztor.cleancodesample.user.entity.User;
import at.pasztor.cleancodesample.user.exception.UserAlreadyExists;

public interface UserStorage {
    void create(User user) throws UserAlreadyExists;

    User createAndReturn(User user) throws UserAlreadyExists;
}
