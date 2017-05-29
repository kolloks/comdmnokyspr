package com.dmnoky.prd.dao;

import com.dmnoky.prd.model.Authority;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO{
    private static final Logger logger = LoggerFactory.getLogger(AuthorityDAOImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addAuthority(Authority authority) {
        this.sessionFactory.getCurrentSession().persist(authority);
        logger.info("Authority: "+authority+" successfully added.");
        return true;
    }

    @Override
    public void updateAuthority(Authority authority) {
        this.sessionFactory.getCurrentSession().update(authority);
        logger.info("Authority: "+authority+" successfully updated.");
    }

    @Override
    public boolean removeAuthority(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Authority authority = (Authority) session.createQuery("SELECT a FROM authorities a WHERE a.username=?")
                .setParameter(0, username).uniqueResult();
        if (authority!=null){
            session.delete(authority);
            logger.info("Authority: "+authority+" successfully removed.");
            return true;
        }
        logger.info("Authority: unsuccessfully removed.");
        return false;
    }

    @Override
    public Authority getAuthority(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Authority authority = (Authority) session.createQuery("SELECT a FROM authorities a WHERE a.username=?")
                .setParameter(0, username).uniqueResult();
        logger.info("Authority: "+authority+" successfully loaded.");
        return authority;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Authority> getAllAuthority() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Authority> authorities = session.createQuery("FROM authorities").list();
        logger.info("Authorities: "+authorities.size()+" successfully loaded.");
        return authorities;
    }
}
