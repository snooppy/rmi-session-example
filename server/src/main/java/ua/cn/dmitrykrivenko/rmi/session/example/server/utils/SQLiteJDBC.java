package ua.cn.dmitrykrivenko.rmi.session.example.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class SQLiteJDBC {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static final String DB_FILE = "rmiexample.db";
    private static final String CONNECTION = "jdbc:sqlite:" + DB_FILE;
    private static final int DEFAULT_USER_COUNT = 10;

    public static void createTable() throws SQLException {
        Connection c = null;
        Statement stmt = null;
        try {
            c = getDBConnection();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "drop table if exists users; "
                    + "create table users "
                    + "(login char(50) primary key  not null,"
                    + " password       char(50)     not null)";
            stmt.executeUpdate(sql);

            c.commit();

            System.out.println("Table has created successfully");
        } catch (SQLException e) {
            printException(e);
            if (c != null) {
                c.rollback();
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public static void fillTable() throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = getDBConnection();
            c.setAutoCommit(false);

            String sql = "insert into users (login,password) values (?, ?);";
            stmt = c.prepareStatement(sql);

            String login = "test";
            String password = "qwerty";

            for (int i = 1; i <= DEFAULT_USER_COUNT; i++) {
                String passwordEncrypted = Encryptor.encryptPassword((password + i).toCharArray());

                stmt.setString(1, login + i);
                stmt.setString(2, passwordEncrypted);
                stmt.executeUpdate();
            }
            c.commit();
            System.out.println("Table has filled successfully");
        } catch (Exception e) {
            printException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public static boolean userExists(String login, String passwordEncrypted) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            c = getDBConnection();
            c.setAutoCommit(false);

            String sql = "select * from users where login=? and password=?";

            stmt = c.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, passwordEncrypted);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = true;
            }
            c.commit();
        } catch (SQLException e) {
            printException(e);
            if (c != null) {
                c.rollback();
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return result;
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(CONNECTION);
            return dbConnection;
        } catch (SQLException e) {
            printException(e);
        }

        return dbConnection;
    }

    private static void printException(Exception e) {
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }
}
