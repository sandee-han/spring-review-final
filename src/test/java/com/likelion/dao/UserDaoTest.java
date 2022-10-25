package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserDaoTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

    UserDao dao = context.getBean("userDao", UserDao.class);
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void before() {
        this.user1 = new User("1", "ramen", "11213");
        this.user2 = new User("2", "udon", "12412");
        this.user3 = new User("3", "pasta", "1q2w3e");
    }


    @Test
    @DisplayName("추가랑 확인이 되나용가리")
    void addAndGet() throws SQLException {
        String id = "8";
        dao.add(new User(id, "haseul", "loona"));

        User user = dao.findById(id);
        assertEquals("haseul", user.getName());
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
        dao.deleteAll();
        assertEquals(0, dao.getCount());

        dao.add(user1);
        assertEquals(1, dao.getCount());
        dao.add(user2);
        assertEquals(2, dao.getCount());
        dao.add(user3);
        assertEquals(3, dao.getCount());
    }

    @Test
    public void getAll() throws SQLException {
        dao.deleteAll();

        dao.add(user1);
        List<User> users1 = dao.getAll();


    }

}
