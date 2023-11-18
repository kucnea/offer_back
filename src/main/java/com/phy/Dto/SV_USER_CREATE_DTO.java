package com.phy.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SV_USER_CREATE_DTO {

    private String loginId;
    private String loginPw;
    private String nmUser;
    private String cdWork;
    private Date dtStart;
    private Date dtEnd;
    private Date dtLast;
    private String cdRole;
    private String cdGrade;
    private String cdPosition;
    private String cdCompanyKind;
    private String idInsert;

}
