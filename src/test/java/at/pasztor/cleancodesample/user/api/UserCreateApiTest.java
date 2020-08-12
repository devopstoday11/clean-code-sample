package at.pasztor.cleancodesample.user.api;

import at.pasztor.cleancodesample.user.entity.User;
import at.pasztor.cleancodesample.user.exception.UserAlreadyExists;
import at.pasztor.cleancodesample.user.storage.InMemoryUserStorage;
import at.pasztor.cleancodesample.user.storage.UserStorage;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class UserCreateApiTest {
    @Test
    public void testUserCreation() throws Throwable {
        //given
        final InMemoryUserStorage userStorage = new InMemoryUserStorage()
        final UserCreateApi api       = new UserCreateApi(userStorage);

        //when
        final UserCreateApi.Response response = api.create(new UserCreateApi.Request(
            "foo@example.com",
            "asdfasdf"
        ));

        //then
        assertNotNull(userStorage.getUserByEmail("foo@example.com"));
    }
}
