package at.pasztor.cleancodesample.user.entity;

import java.util.Objects;

public class User {
    public final String id;
    public final String email;

    public User(final String email) {
        this.id = "1";
        this.email = email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
