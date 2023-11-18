package com.phy.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SV_USER_LOGIN_DTO {

    private String loginId;
    private String loginPw;
    private String nmUser;
//    private String yn_permission;
//    private String cd_work;
//    private Date dt_start;
//    private Date dt_end;
//    private String cd_role;
//    private String cd_grade;
//    private String cd_position;
//    private String cd_company_kind;
//    private Date dt_last_connect;

}
