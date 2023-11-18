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
public class OFFERDETAIL_LISTUP_DTO {

    Long offerIdx;
    String nmStore;
    String nmCeo;
    String strTel;
    String cdAdj;
    String strMsg;
    String cdComplete;
    Date dtInsert;

}
