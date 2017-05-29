package com.dmnoky.prd.dao;

import com.dmnoky.prd.model.User;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    void updateUser(User user);
    boolean removeUser(int id);
    User getUserById(int id);
    User getUserByName(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}
