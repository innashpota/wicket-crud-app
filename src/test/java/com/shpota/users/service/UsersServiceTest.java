package com.shpota.users.service;

import com.shpota.users.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql("/init_test_table.sql")
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void shouldGetUsers() throws Exception {
        Collection<User> users = usersService.getUsers();

        assertEquals(2, users.size());
    }

    @Test
    public void shouldCreateUser() throws Exception {
        User user = new User("Last", "First", "Middle");

        usersService.createUser(user);

        User newUser = usersService.getUserById(3);
        assertEquals("Last", newUser.getLastName());
        assertEquals("First", newUser.getFirstName());
        assertEquals("Middle", newUser.getMiddleName());
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        usersService.deleteUser(2);

        int size = usersService.getUsers().size();
        assertEquals(1, size);
    }

    @Test
    public void shouldGetUsersByName() throws Exception {
        List<User> expectedUsers = Collections.singletonList(
                new User(2, "Viazovska", "Maryna", "Sergiivna")
        );
        String name = "Maryna";

        List<User> actualUsers = usersService.getUsersByName(name);

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldGetUserById() throws Exception {
        User expectedUser = new User(2, "Viazovska", "Maryna", "Sergiivna");

        User actualUser = usersService.getUserById(2);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void updateUser() throws Exception {
        int id = 2;
        User expectedUser = new User(id, "Last", "First", "Middle");

        usersService.updateUser(id, expectedUser);

        User actualUser = usersService.getUserById(id);
        assertEquals(expectedUser, actualUser);
    }
}