package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao = new UserDao();

    User user1;
    User user2;
    User user3;

    @Test
    void addAndGet() throws SQLException {
        this.user1 = new User("1", "ramen", "11213");
        this.user2 = new User("2", "udon", "12412");
        this.user3 = new User("3", "pasta", "1q2w3e");

        String id = "2";
        userDao.add(new User(id, "olivia", "loona"));
        User user = userDao.findById(id);

        assertEquals("olivia", user.getName());
        assertEquals("loona", user.getPassword());
    }
}
