package com.example.SpringSecurityAccessAndRefreshJWT.repository;

import com.example.SpringSecurityAccessAndRefreshJWT.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleByName(String name);
}
