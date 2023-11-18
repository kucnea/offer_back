package com.phy.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class SV_Commoncode {

    private String cdGroup;
    @Id
    private String cd;
    private String nm;
    private String ynUse;
    private int inOrder;
    private String ynDefault;
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInsert;
    private String idInsert;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdate;
    private String idUpdate;

    public SV_Commoncode(String errorCode, String errorMsg){
        // 오류 객체 생성시 해당 생성자 사용
        this.cd = errorCode;
        this.nm = errorMsg;
    }
    public SV_Commoncode(String cd){
        this.cd = cd;
    }
}
