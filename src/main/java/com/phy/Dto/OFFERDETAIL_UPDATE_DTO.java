package com.phy.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OFFERDETAIL_UPDATE_DTO {

    Long offerIdx;
    String nmStore;
    String cdComplete;

}
