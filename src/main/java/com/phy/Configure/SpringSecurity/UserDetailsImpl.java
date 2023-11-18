package com.phy.Configure.SpringSecurity;

import com.phy.Entity.SV_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

//    private static final String ROLE_PREFIX = "ROLE_";
//    private final Member member;
    private final SV_User sv_user;

    @Autowired
    public UserDetailsImpl(SV_User svUser) {
        sv_user = svUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(sv_user.getCdRole().getCd());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

    public SV_User getSv_user(){
        return sv_user;
    }

    @Override
    public String getPassword() {
        return sv_user.getLoginPw();
    }

    @Override
    public String getUsername() {
        return sv_user.getLoginId();
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
        return true;
    }
}
