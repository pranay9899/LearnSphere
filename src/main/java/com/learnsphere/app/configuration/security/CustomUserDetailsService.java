package com.learnsphere.app.configuration.security;

import com.learnsphere.app.entity.User;
import com.learnsphere.app.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Loads User from DB by email and adapts to Spring Security UserDetails.
 * Ensure this class is discovered by component scan (package under @SpringBootApplication).
 */
@Service
public class   CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()));

        boolean enabled = u.getStatus() == User.Status.ACTIVE;
        boolean accountNonLocked = u.getStatus() != User.Status.SUSPENDED;

        return new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                u.getPassword(),
                enabled,
                true,
                true,
                accountNonLocked,
                authorities
        );
    }
}
