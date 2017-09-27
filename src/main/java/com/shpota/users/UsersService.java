package com.shpota.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsersService {
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (lastName, firstName, middleName) VALUES (?, ?, ?);";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Collection<User> getUsers() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL_USERS,
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getString("middleName")
                )
        );
    }

    public void createUser(User user) {
        jdbcTemplate.update(
                SQL_INSERT_USER,
                user.getLastName(), user.getFirstName(), user.getMiddleName());
    }
}
