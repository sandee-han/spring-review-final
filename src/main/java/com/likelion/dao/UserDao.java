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

    private PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement pstmt;
        pstmt = c.prepareStatement("DELETE FROM USERS");
        return pstmt;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (ps!= null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
            }
        }
    }


    public void add(User user) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = dataSource.getConnection();
            pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(c != null) {
                    c.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public User findById(String id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            this.user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void deleteAll() throws SQLException {
        StatementStrategy st = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(st);
    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;
        int count = 0;
        try {
            c = dataSource.getConnection();
            pstmt = c.prepareStatement("SELECT COUNT(*) FROM users");
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(c != null) {
                    c.close();
                }
            } catch (SQLException e) {
            }
        }
        return count;
    }

}