package org.example.Users;

import org.example.Exceptions.DuplicateUsernameException;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
class UserTest {

    @Test
    void ConstructorTest() {
        User user = new User(1, "testUser", "testPassword", UserRole.STAFF);
        assertEquals(1, user.getUserId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertEquals(UserRole.STAFF, user.getRole());
    }

    @Test
    void updateUser() {
        User user = new User(1, "testUser", "password", UserRole.STAFF);
        assertEquals("testUser", user.getUsername());
        assertEquals(1, user.getUserId());
        assertEquals("password", user.getPassword());
        assertEquals(UserRole.STAFF, user.getRole());
        user.updateUserConstructor(1,"New User Name" , "New Password", UserRole.STAFF);
        assertEquals("New User Name", user.getUsername());
        assertEquals(1, user.getUserId());
        assertEquals("New Password", user.getPassword());
        assertEquals(UserRole.STAFF, user.getRole());
    }

    @Test
    void listUsers() {
        User.listUsers();
    }

    @Test
    void deleteUser() {
        User user = new User(1, "testUser", "testPassword", UserRole.STAFF);
        User.deleteUser(1);
    }
}
