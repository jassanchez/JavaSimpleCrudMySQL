package com.asanchez.crud.Services;

import java.sql.SQLException;
import java.util.List;

import com.asanchez.crud.entity.User;
import com.asanchez.crud.model.UserModel;

public class UserService {

    private UserModel userModel;

    public UserService() {
        try {
            userModel = new UserModel();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        return userModel.findAll();
    }

    public User findById(Long id) {
        return userModel.findById(id);
    }

    public User save(User user) {
        if (validate(user))
            return userModel.save(user);
            
        return null;
    }

    public User update(User user) {
        if (!validate(user))
            return null;

        if (findById(user.getUser_id()) != null) {
            return userModel.update(user);
        }

        return null;
    }

    public void deleteById(Long id) {
        userModel.deleteById(id);
    }

    private boolean validate(User _user) {
        if (_user.getName().isBlank())
            return false;
        if (_user.getLastname().isBlank())
            return false;
        return true;
    }
}
