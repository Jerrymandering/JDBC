package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "iduser BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(255), " +
                "ulastname VARCHAR(255), " +
                "userage TINYINT)";
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO user (username, ulastname, userage) VALUES (?, ?, ?)";
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            conn.commit();  // <-- добавить
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE iduser = ?";
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            conn.commit();  // <-- добавить
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT iduser, username, ulastname, userage FROM user";
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("ulastname"),
                        rs.getByte("userage")
                );
                user.setId(rs.getLong("iduser"));
                System.out.println("Прочитан пользователь: name=" + user.getName() + ", lastName=" + user.getLastName() + ", age=" + user.getAge());
                users.add(user);
            }
            System.out.println("getAllUsers() нашёл записей: " + users.size()); // <-- отладка
        } catch (SQLException e) {
            System.out.println("Ошибка в getAllUsers(): " + e.getMessage()); // <-- отладка
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user";
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            conn.commit();  // <-- добавить
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
