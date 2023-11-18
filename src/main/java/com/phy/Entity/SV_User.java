package com.phy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="SV_User")
@SequenceGenerator(
        name = "SV_User_SEQ_GENERATOR",
        sequenceName = "SV_User_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@ToString
public class SV_User {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SV_User_SEQ_GENERATOR") // 오라클 사용시
	@GeneratedValue(strategy=GenerationType.IDENTITY)	// MySQL 사용시
//	@EmbeddedId									//이거는 시퀀스 자동증가를 사용할 수 없음.
    private Long idUser;
    private String loginId;
    private String loginPw;
    private String nmUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cd_permission", referencedColumnName = "cd")
    private SV_Commoncode cdPermission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cd_work", referencedColumnName = "cd")
    private SV_Commoncode cdWork;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEnd;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtLast;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cd_role", referencedColumnName = "cd")
    private SV_Commoncode cdRole;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cd_grade", referencedColumnName = "cd")
    private SV_Commoncode cdGrade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cd_position", referencedColumnName = "cd")
    private SV_Commoncode cdPosition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cd_company_kind", referencedColumnName = "cd")
    private SV_Commoncode cdCompanyKind;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtLastConnect;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInsert;
    private String idInsert;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdate;
    private String idUpdate;

    public SV_User(String errorCode, String errorMsg){
        // 오류 객체 생성시 해당 생성자 사용
        this.loginId = "errorCode : "+errorCode;
        this.nmUser = "errorMessage : "+errorMsg;
    }

}
