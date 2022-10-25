package com.likelion.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeSql(final String query) throws SQLException {
        workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement(query);
            }
        });
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = this.dataSource.getConnection();
            pstmt = stmt.makePreparedStatement(c);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
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
}
