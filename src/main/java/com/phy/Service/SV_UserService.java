package com.phy.Service;

import com.phy.CommonFuncs.DateFunc;
import com.phy.Configure.SpringSecurity.UserDetailsImpl;
import com.phy.Dto.SV_USER_CREATE_DTO;
import com.phy.Dto.SV_USER_LOGIN_DTO;
import com.phy.Entity.SV_Commoncode;
import com.phy.Entity.SV_User;
import com.phy.Repository.SV_CommonCodeRepository;
import com.phy.Repository.SV_UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
//@PropertySource("classpath:a.properties")
@Log4j2
public class SV_UserService {

    private final SV_UserRepository sVUserRepository;
    private final SV_CommonCodeRepository svCommonCodeRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public SV_UserService(SV_UserRepository sVUserRepository, SV_CommonCodeRepository svCommonCodeRepository, AuthenticationManager authenticationManager ){
        this.sVUserRepository = sVUserRepository;
        this.svCommonCodeRepository = svCommonCodeRepository;
        this.authenticationManager = authenticationManager;
    }

    @Value("${path.file}") private String basePath;

    public SV_USER_CREATE_DTO save(SV_USER_CREATE_DTO sv_user_create_dto) throws Exception {

        SV_Commoncode cd_permission = svCommonCodeRepository.findByCdGroupAndYnDefault("U001","Y")
                .orElseThrow(()-> new EntityNotFoundException("로그인허용(U001) 공통코드가 확인되지 않습니다."));

        // 중복체크
        if( !hasSameId(sv_user_create_dto.getLoginId()) ) new EntityNotFoundException("중복된 아이디가 있습니다.");

        SV_User sv_user = sVUserRepository.save(SV_User.builder()
                                                    .loginId(sv_user_create_dto.getLoginId())
                                                    .loginPw(sv_user_create_dto.getLoginPw())
                                                    .nmUser(sv_user_create_dto.getNmUser())
                                                    .cdPermission(cd_permission)
                                                    .cdWork(new SV_Commoncode(sv_user_create_dto.getCdWork()))
                                                    .dtStart(sv_user_create_dto.getDtStart())
                                                    .dtEnd(sv_user_create_dto.getDtEnd())
                                                    .dtLast(sv_user_create_dto.getDtLast())
                                                    .cdRole(new SV_Commoncode(sv_user_create_dto.getCdRole()))
                                                    .cdGrade(new SV_Commoncode(sv_user_create_dto.getCdGrade()))
                                                    .cdPosition(new SV_Commoncode(sv_user_create_dto.getCdPosition()))
                                                    .cdCompanyKind(new SV_Commoncode(sv_user_create_dto.getCdCompanyKind()))
                                                    .idInsert(sv_user_create_dto.getIdInsert())
                                                    .build());

        return SV_USER_CREATE_DTO.builder()
                .loginId(sv_user.getLoginId())
                .nmUser(sv_user.getNmUser())
                .build();

    }

    public boolean hasSameId(String id){
        return sVUserRepository.findByLoginId(id) == null ? false : true ;
    }

    public SV_USER_LOGIN_DTO longIn(SV_USER_LOGIN_DTO svUserLoginDto){

        // 시큐리티 이전 로그인시 엔티티 가져온 거
//        SV_USER_LOGIN_DTO finalSvUserLoginDto = svUserLoginDto;
//        SV_User sv_user = sVUserRepository.findByLoginIdAndLoginPw(svUserLoginDto.getLogin_id(),svUserLoginDto.getLogin_pw())
//                .orElseThrow(()->new UsernameNotFoundException("찾을 수 없는 사용자입니다. ID : "+ finalSvUserLoginDto.getLogin_id()));

        // 시큐리티 로그인
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( svUserLoginDto.getLoginId(), svUserLoginDto.getLoginPw() ) );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        SV_User sv_user = principal.getSv_user();

        // 로그인 허가여부 확인
        if( !sv_user.getCdPermission().equals("0") ){
            sv_user = new SV_User(sv_user.getCdPermission().getCd(), sv_user.getCdPermission().getNm()+"상태로 확인돼 로그인이 어렵습니다. \n 관리자에게 문의하세요.");
        }


        SV_Commoncode loginDays = svCommonCodeRepository.findAllByCdGroup("U002").orElseThrow(()->new EntityNotFoundException("로그인 경과일 기준(U002)이 확인되지 않습니다."));
        SV_Commoncode loginCheck = svCommonCodeRepository.findAllByCdGroup("U003").orElseThrow(()->new EntityNotFoundException("로그인 경과시 기준이 확인되지 않습니다."));

        // 로그인한지 시간경과 확인
        if( Integer.parseInt(loginDays.getCd()) < DateFunc.betweenDays(new Date(), sv_user.getDtLastConnect()) ){
            switch (Integer.parseInt(loginCheck.getCd())){
                case 0: // 로그인 체크 0 일시 통과
                    break;
                case 1: // 로그인체크 1이면 잠금처리
                    sv_user = new SV_User("1",loginDays+"일이상 로그인이 확인되지 않아 계정이 잠금처리 되었습니다. \n관리자에게 문의하세요.");
            }
        }

        svUserLoginDto = SV_USER_LOGIN_DTO.builder()
                .loginId(sv_user.getLoginId())
                .nmUser(sv_user.getNmUser())
//                .cd_work(sv_user.getCd_work().getCd())
//                .dt_start(sv_user.getDt_start())
//                .dt_end(sv_user.getDt_end())
//                .cd_role(sv_user.getCd_role().getCd())
//                .cd_grade(sv_user.getCd_grade().getCd())
//                .cd_position(sv_user.getCd_position().getCd())
//                .cd_company_kind(sv_user.getCd_company_kind().getCd())
//                .dt_last_connect(sv_user.getDt_last_connect())
                .build();

        return svUserLoginDto;
    }


}
