package com.likelion.dao;

import com.likelion.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private User user;


    public void add(User user) {
        try {
            Connection c = dataSource.getConnection();

            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        try {
            Connection c = dataSource.getConnection();

            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            this.user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            rs.close();
            pstmt.close();
            c.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement pstmt = c.prepareStatement("DELETE FROM users");

        pstmt.executeUpdate();
        pstmt.close();
        c.close();
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement pstmt = c.prepareStatement("SELECT COUNT(*) FROM users");

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        pstmt.close();
        c.close();

        return count;
    }

}