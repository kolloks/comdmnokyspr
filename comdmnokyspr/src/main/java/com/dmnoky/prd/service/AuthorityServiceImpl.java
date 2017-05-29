package com.dmnoky.prd.service;

import com.dmnoky.prd.dao.AuthorityDAO;
import com.dmnoky.prd.model.Authority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AuthorityServiceImpl implements AuthorityService{
    private AuthorityDAO authorityDAO;

    public void setAuthorityDAO(AuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Override
    @Transactional
    public boolean addAuthority(Authority authority) {
        return this.authorityDAO.addAuthority(authority);
    }

    @Override
    @Transactional
    public void updateAuthority(Authority authority) {
        this.authorityDAO.updateAuthority(authority);
    }

    @Override
    @Transactional
    public boolean removeAuthority(String username) {
        return this.authorityDAO.removeAuthority(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Authority getAuthority(String username) {
        return this.authorityDAO.getAuthority(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Authority> getAllAuthority() {
        return this.authorityDAO.getAllAuthority();
    }
}
