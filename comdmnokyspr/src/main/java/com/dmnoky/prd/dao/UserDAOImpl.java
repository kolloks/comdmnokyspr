package com.dmnoky.prd.dao;

import com.dmnoky.prd.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        this.sessionFactory.getCurrentSession().persist(user);
        logger.info("User: "+user+" successfully added.");
        return true;
    }

    @Override
    public void updateUser(User user) {
        this.sessionFactory.getCurrentSession().update(user);
        logger.info("User: "+user+" successfully updated.");
    }

    @Override
    public boolean removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if (user!=null){
            session.delete(user);
            logger.info("User: "+user+" successfully removed.");
            return true;
        }
        logger.info("User: unsuccessfully removed.");
        return false;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        logger.info("User: "+user+" successfully loaded.");
        return user;
    }

    @Override
    public User getUserByName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User)
                (session.createQuery("SELECT u FROM User u WHERE u.username=?")
                        .setParameter(0, username)).uniqueResult();
        logger.info("User: "+user+" successfully loaded.");
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User)
                (session.createQuery("SELECT u FROM User u WHERE u.email=?")
                        .setParameter(0, email)).uniqueResult();
        logger.info("User: "+user+" successfully loaded.");
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User").list();
        logger.info("Users: "+userList.size()+" successfully loaded.");
        return userList;
    }
}
