package com.asanchez.crud.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asanchez.crud.entity.User;

public class UserModel {

    private Connection connection;

    public UserModel() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_example", "root", "sasa1234");
    }

    public User findById(Long id) {
        User user = null;

        String query = "select * from users2 where user_id = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUser_id(rs.getLong(1));
                    user.setName(rs.getString(2));
                    user.setLastname(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String query = "select * from users2";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastname(rs.getString(3));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User save(User user) {
        String query = "INSERT INTO users2 (name, lastname) VALUES (?,?) ";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUser_id(rs.getLong(1));
                    return user;
                }
                System.out.println("por aqui?");
                return null;
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public User update(User user) {
        String query = "UPDATE users2 SET name=?, lastname=? WHERE user_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setLong(3, user.getUser_id());
            if (ps.executeUpdate() > 0) {
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public void deleteById(Long id) {
        String query = "DELETE FROM users2 WHERE user_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
