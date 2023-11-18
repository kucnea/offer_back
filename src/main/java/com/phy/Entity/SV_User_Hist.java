package com.phy.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SV_User_Hist {

    //슈퍼키에 넣음.
//    private String id_user;
//    private int seq;
    @EmbeddedId
    private SV_User_Hist_SuperKey svUserHistSuperKey;
    private String loginId;
    private String loginPw;
    private String nmUser;
    private String cdPermission;
    private String cdWork;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEnd;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtLast;
    private String cdRole;
    private String cdGrade;
    private String cdPosition;
    private String cdCompanyKind;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtLastConnect;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInsert;
    private String idInsert;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdate;
    private String idUpdate;
    private String cdReason;

}
