package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao;

    User user1;
    User user2;
    User user3;

    @Test
    void addAndGet() throws SQLException {
        this.user1 = new User("1", "ramen", "11213");
        this.user2 = new User("2", "udon", "12412");
        this.user3 = new User("3", "pasta", "1q2w3e");

        String id = "29";
        userDao.add(new User(id, "EternityHwan", "1234"));
        User user = userDao.findById(id);

        assertEquals("EternityHwan", user.getName());
        assertEquals("1234", user.getPassword());
    }
}