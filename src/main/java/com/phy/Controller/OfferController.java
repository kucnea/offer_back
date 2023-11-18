package com.phy.Controller;

import com.phy.Dto.OFFERDETAIL_CREATE_DTO;
import com.phy.Dto.OFFERDETAIL_LISTUP_DTO;
import com.phy.Dto.OFFERDETAIL_UPDATE_DTO;
import com.phy.Entity.OfferDetail;
import com.phy.Service.OfferService;
import com.phy.Service.SV_UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }

    @PostMapping("/offer")
    public OFFERDETAIL_CREATE_DTO createOffer(OfferDetail offer) throws Exception {

        log.debug("Get in createOffer.");

        OFFERDETAIL_CREATE_DTO offerDto = OFFERDETAIL_CREATE_DTO.builder()
                                                    .nmStore(offer.getNmStore())
                                                    .nmCeo(offer.getNmCeo())
                                                    .cdAdj(offer.getCdAdj())
                                                    .strTel(offer.getStrTel())
                                                    .strMsg(offer.getStrMsg()).build();

        return offerService.save(offerDto);
    }

    @GetMapping("/offer/{pageNum}")
    public Page<OFFERDETAIL_LISTUP_DTO> readOffer(@PathVariable("pageNum") int pageNum
                                ,@RequestParam(required = false, defaultValue = "A") String cdComplete
                                ,@RequestParam(required = false, defaultValue = "0") String searchMode
                                ,@RequestParam(required = false) String searchKey) throws Exception {

        log.debug("Get in readOffer.");

        Page<OFFERDETAIL_LISTUP_DTO> list = offerService.getOfferList(pageNum, cdComplete, searchMode, searchKey);

        return list;
    }

    @PutMapping("/offer/{offerIdx}")
    public OFFERDETAIL_UPDATE_DTO updateOffer(@PathVariable("offerIdx") Long offerIdx
        , @RequestBody OFFERDETAIL_UPDATE_DTO offerDto ) throws Exception {

        log.debug("Get in updateOffer.");

        return offerService.updateOffer(offerDto);
    }

}
