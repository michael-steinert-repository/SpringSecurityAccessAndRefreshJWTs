package com.example.SpringSecurityAccessAndRefreshJWT.service;

import com.example.SpringSecurityAccessAndRefreshJWT.model.AppUser;
import com.example.SpringSecurityAccessAndRefreshJWT.model.UserRole;
import com.example.SpringSecurityAccessAndRefreshJWT.repository.RoleRepository;
import com.example.SpringSecurityAccessAndRefreshJWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findAppUserByUsername(username);
        if (appUser == null) {
            log.error("User not found in the Database");
            throw new UsernameNotFoundException("Username not found in the Database");
        } else {
            log.info("User {} found in the Database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getUserRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        });

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving new User {} to the Database", appUser.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public UserRole saveRole(UserRole userRole) {
        log.info("Saving new Role {} to the Database", userRole.getName());
        return roleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding new Role {} to User {} to the Database", roleName, username);
        AppUser appUser = userRepository.findAppUserByUsername(username);
        UserRole userRole = roleRepository.findUserRoleByName(roleName);
        /* Because of "Transactional" Annotation the User will also saved if a Role is added */
        appUser.getUserRoles().add(userRole);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching User {} from the Database", username);
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all Users");
        return userRepository.findAll();
    }
}
