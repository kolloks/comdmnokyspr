package com.dmnoky.prd.service;

import com.dmnoky.prd.dao.UserDAO;
import com.dmnoky.prd.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean addUser(User user) {
        user.setEnabled(1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user, int enabled) {
        user.setEnabled(enabled);
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public boolean removeUser(int id) {
        return this.userDAO.removeUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String username) {
        return this.userDAO.getUserByName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return this.userDAO.getUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }
}
