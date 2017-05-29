package com.dmnoky.prd.service;

import com.dmnoky.prd.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    void updateUser(User user, int enabled);
    boolean removeUser(int id);
    User getUserById(int id);
    User getUserByName(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}
