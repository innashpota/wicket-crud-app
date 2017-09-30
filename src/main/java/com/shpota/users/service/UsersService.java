package com.shpota.users.service;

import com.shpota.users.domain.User;
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
            "INSERT INTO users (last_name, first_name, middle_name) VALUES (?, ?, ?);";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String SQL_SELECT_USERS_BY_NAME =
            "SELECT * FROM users WHERE UPPER(last_name) LIKE UPPER(?) OR UPPER(first_name) " +
                    "LIKE UPPER(?) OR UPPER(middle_name) LIKE UPPER(?);";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_UPDATE_USER =
            "UPDATE users " +
                    "SET last_name = ?, first_name = ?, middle_name = ? WHERE id = ?;";

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

    public User getUserById(int id) {
        List<User> userList = jdbcTemplate.query(SQL_SELECT_USER_BY_ID, new Object[]{id}, userRowMapper());
        return userList.get(0);
    }

    public void updateUser(int id, User user) {
        jdbcTemplate.update(
                SQL_UPDATE_USER,
                user.getLastName(), user.getFirstName(), user.getMiddleName(), id
        );
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("middle_name")
        );
    }
}
