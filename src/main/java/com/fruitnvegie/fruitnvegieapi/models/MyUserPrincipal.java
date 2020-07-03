package com.fruitnvegie.fruitnvegieapi.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyUserPrincipal implements UserDetails {

    private User user;
    private Integer id;

    public MyUserPrincipal(User user) {
        this.user = user;
        id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        java.util.Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : user.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive();
    }

    public Integer getId(){
        return user.getId();
    }
}
