package com.phy.Controller;

import com.phy.Dto.SV_USER_CREATE_DTO;
import com.phy.Entity.SV_User;
import com.phy.Service.SV_UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class SV_UserController {

    private final SV_UserService SVUserService;

    @Autowired // 생성자 한개일때는 생략가능
    public SV_UserController(SV_UserService SVUserService){
        this.SVUserService = SVUserService;
    }


    @PostMapping("/sv-user")
    public SV_USER_CREATE_DTO createUser(SV_User sv_user) throws Exception {

        log.debug("Get in createUser.");

        // 로그인한 유저가 유저 생성시 그 아이디로, 그렇지않을경우 입력한 아이디로 작성자정보 추가
        String writerId = SecurityContextHolder.getContext().getAuthentication().getName().length() == 0 ?
                sv_user.getLoginId() : SecurityContextHolder.getContext().getAuthentication().getName();

        SV_USER_CREATE_DTO sv_user_create_dto = SV_USER_CREATE_DTO.builder()
                                                                .loginId(sv_user.getLoginId())
                                                                .loginPw(sv_user.getLoginPw())
                                                                .nmUser(sv_user.getNmUser())
                                                                .cdWork(sv_user.getCdWork().getCd())
                                                                .dtStart(sv_user.getDtStart())
                                                                .dtEnd(sv_user.getDtEnd())
                                                                .dtLast(sv_user.getDtLast())
                                                                .cdRole(sv_user.getCdRole().getCd())
                                                                .cdGrade(sv_user.getCdGrade().getCd())
                                                                .cdPosition(sv_user.getCdPosition().getCd())
                                                                .cdCompanyKind(sv_user.getCdCompanyKind().getCd())
                                                                .idInsert(writerId)
                                                                .build();

        SV_USER_CREATE_DTO return_sv_user_create_dto = SVUserService.save(sv_user_create_dto);
        // 화면에서 선택에따라
        // 아이디 님 환영합니다.
        // 이름 님 확영합니다.
        // 이름(아이디)님 환영합니다.
        // 아이디(이름)님 환영합니다. 등 활용할 수 있도록. 또한 문구도 수정.

        return return_sv_user_create_dto;
    }

}

