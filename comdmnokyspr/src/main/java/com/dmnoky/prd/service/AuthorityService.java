package com.dmnoky.prd.service;

import com.dmnoky.prd.model.Authority;

import java.util.List;

public interface AuthorityService {
    boolean addAuthority(Authority authority);
    void updateAuthority(Authority authority);
    boolean removeAuthority(String username);
    Authority getAuthority(String username);
    List<Authority> getAllAuthority();
}
