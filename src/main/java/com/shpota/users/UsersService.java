package com.shpota.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsersService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Collection<User> getUsers() {
        Collection<User> users = jdbcTemplate.query(
                "SELECT * FROM users",
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getString("middleName")
                )
        );
        return users;
    }
}
