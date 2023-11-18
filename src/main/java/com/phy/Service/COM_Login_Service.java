package com.phy.Service;

import com.phy.Configure.SpringSecurity.UserDetailsImpl;
import com.phy.Dto.SV_USER_LOGIN_DTO;
import com.phy.Entity.SV_User;
import com.phy.Repository.SV_UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class COM_Login_Service implements UserDetailsService {

    private final SV_UserRepository svUserRepository;

    @Autowired
    public COM_Login_Service( SV_UserRepository svUserRepository) {
        this.svUserRepository = svUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SV_User sv_user = svUserRepository.findByLoginId(username)
                .orElseThrow(()-> new UsernameNotFoundException("아이디로 유저를 찾을 수 없습니다."));

        return new UserDetailsImpl(sv_user);
    }

}
