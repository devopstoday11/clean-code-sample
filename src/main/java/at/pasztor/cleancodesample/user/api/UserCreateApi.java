package at.pasztor.cleancodesample.user.api;

import at.pasztor.cleancodesample.user.entity.User;
import at.pasztor.cleancodesample.user.exception.UserAlreadyExists;
import at.pasztor.cleancodesample.user.storage.UserStorage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(
    tags = "Users"
)
@RequestMapping("/users")
public class UserCreateApi {
    private UserStorage userStorage;

    @Autowired
    public UserCreateApi(
        UserStorage userStorage
    ) {
        this.userStorage = userStorage;
    }

    public Response create(Request request) throws UserAlreadyExists {
        User user = new User(
            request.email
        );
        user = userStorage.createAndReturn(user);
        return new Response(
            user
        );
    }

    public static class Request {
        public final String email;
        public final String password;

        public Request(
            final String email,
            final String password
        ) {
            this.email = email;
            this.password = password;
        }
    }

    static class Response {
        public final UserResource userResource;

        Response(final User user) {
            this.userResource = new UserResource(user);
        }
    }
}
