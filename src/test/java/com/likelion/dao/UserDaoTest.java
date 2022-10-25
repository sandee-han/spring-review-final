package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserDaoTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

    UserDao dao = context.getBean("userDao", UserDao.class);
    User user1;
    User user2;
    User user3;

    @Test
    @DisplayName("추가랑 확인이 되나용가리")
    void addAndGet() throws SQLException {
        this.user1 = new User("1", "ramen", "11213");
        this.user2 = new User("2", "udon", "12412");
        this.user3 = new User("3", "pasta", "1q2w3e");

        String id = "6";
        dao.add(new User(id, "kimlip", "loona"));

        dao.add(user1);
        assertEquals(1, dao.getCount());

        User user = dao.findById(id);

        assertEquals("kimlip", user.getName());
        assertEquals("loona", user.getPassword());
    }

    @Test
    @DisplayName("삭제 되나용가리치킨")
    void delete() throws SQLException {
        dao.deleteAll();
    }

    @Test
    @DisplayName("Count 테스트")
    void count() throws SQLException {
        this.user1 = new User("1", "ramen", "11213");
        this.user2 = new User("2", "udon", "12412");
        this.user3 = new User("3", "pasta", "1q2w3e");

        dao.deleteAll();
        assertEquals(0, dao.getCount());

        dao.add(user1);
        assertEquals(1, dao.getCount());
        dao.add(user2);
        assertEquals(2, dao.getCount());
        dao.add(user3);
        assertEquals(3, dao.getCount());
    }

}
