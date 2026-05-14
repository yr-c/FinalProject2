package org.yoonchan.interfaces;

import org.yoonchan.Library;
import org.yoonchan.roles.User;

public interface UserRegistrar {
    /**
     * Adds a User to the Library's registeredUsers list.
     * @param user The User to be added.
     * @throws NullPointerException If the input User is null.
     */
    default void registerUserToLibrary(User user) throws NullPointerException {
        if (user == null) throw new NullPointerException();

        Library.registeredUsers.add(user);
    }
}
