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
public class OfferDetail {

    @Id
    Long offerIdx;

    String nmStore;
    String nmCeo;
    String strTel;
    String cdAdj;
    String strMsg;
    String cdComplete;

    @Temporal(TemporalType.TIMESTAMP)
    Date dtInsert;
    @Temporal(TemporalType.TIMESTAMP)
    Date dtUpdate;

    public OfferDetail(Long offerIdx, String cdErr){
        this.offerIdx = offerIdx;
        this.nmStore = cdErr;
    }
}
