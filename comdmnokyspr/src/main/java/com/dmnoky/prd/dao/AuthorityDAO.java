package com.dmnoky.prd.dao;

import com.dmnoky.prd.model.Authority;

import java.util.List;

public interface AuthorityDAO {
    boolean addAuthority(Authority authority);
    void updateAuthority(Authority authority);
    boolean removeAuthority(String username);
    Authority getAuthority(String username);
    List<Authority> getAllAuthority();
}
