package com.shpota.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsersService {
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (lastName, firstName, middleName) VALUES (?, ?, ?);";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String SQL_SELECT_USERS_BY_NAME =
            "SELECT * FROM users WHERE UPPER(lastName) LIKE UPPER(?) OR UPPER(firstName) " +
                    "LIKE UPPER(?) OR UPPER(middleName) LIKE UPPER(?);";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Collection<User> getUsers() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL_USERS,
                userRowMapper()
        );
    }

    public void createUser(User user) {
        jdbcTemplate.update(
                SQL_INSERT_USER,
                user.getLastName(), user.getFirstName(), user.getMiddleName()
        );
    }

    public void deleteUser(int id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    public List<User> getUsersByName(String name) {
        return jdbcTemplate.query(
                SQL_SELECT_USERS_BY_NAME,
                new Object[]{name + "%", name + "%", name + "%"},
                userRowMapper()
        );
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("lastName"),
                rs.getString("firstName"),
                rs.getString("middleName")
        );
    }
}
