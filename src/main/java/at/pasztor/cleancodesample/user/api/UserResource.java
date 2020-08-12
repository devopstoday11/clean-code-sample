package at.pasztor.cleancodesample.user.api;

import at.pasztor.cleancodesample.user.entity.User;

public class UserResource {
    public final String id;
    public final String email;

    public UserResource(User user) {
        this.id = user.id;
        this.email = user.email;
    }
}
