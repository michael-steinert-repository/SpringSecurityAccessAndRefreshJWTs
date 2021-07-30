package com.example.SpringSecurityAccessAndRefreshJWT.service;

import com.example.SpringSecurityAccessAndRefreshJWT.model.AppUser;
import com.example.SpringSecurityAccessAndRefreshJWT.model.UserRole;

import java.util.List;

public interface IUserService {
    AppUser saveUser(AppUser appUser);
    UserRole saveRole(UserRole userRole);
    /* Assumption: Username is unique */
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
