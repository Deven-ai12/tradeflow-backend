package com.dev.tradeflow.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.tradeflow.entity.User;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the complete User entity.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the user's role as a GrantedAuthority.
     * Example: ROLE_USER or ROLE_ADMIN
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }

    /**
     * Returns the encrypted password stored in the database.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Spring Security uses email as the username.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Account expiration check.
     * We are not implementing this feature now.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Account locking check.
     * We are not implementing this feature now.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Credential expiration check.
     * We are not implementing this feature now.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Only verified users can log in.
     */
    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}