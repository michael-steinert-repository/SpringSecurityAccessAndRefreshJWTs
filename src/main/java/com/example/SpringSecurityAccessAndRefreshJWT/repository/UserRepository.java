package com.example.SpringSecurityAccessAndRefreshJWT.repository;

import com.example.SpringSecurityAccessAndRefreshJWT.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
}
