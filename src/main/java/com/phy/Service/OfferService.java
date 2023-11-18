package com.phy.Service;

import com.phy.Dto.OFFERDETAIL_CREATE_DTO;
import com.phy.Dto.OFFERDETAIL_LISTUP_DTO;
import com.phy.Dto.OFFERDETAIL_UPDATE_DTO;
import com.phy.Entity.OfferDetail;
import com.phy.Repository.OfferRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Log4j2
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService( OfferRepository offerRepository ){
        this.offerRepository = offerRepository;
    }

    public OFFERDETAIL_CREATE_DTO save(OFFERDETAIL_CREATE_DTO offer){

        OfferDetail offerDetail = OfferDetail.builder()
                                            .nmStore(offer.getNmStore())
                                            .nmCeo(offer.getNmCeo())
                                            .cdAdj(offer.getCdAdj())
                                            .strTel(offer.getStrTel())
                                            .strMsg(offer.getStrMsg())
                                            .cdComplete("N")
                                            .dtInsert(new Date())
                                            .dtUpdate(new Date()).build();

        try{
            offerRepository.save(offerDetail);
            return offer;
        } catch (Exception e){
            log.debug(e.getMessage());
            OFFERDETAIL_CREATE_DTO offer_fail = new OFFERDETAIL_CREATE_DTO();
            return offer_fail;
        }

    }

    public Page<OFFERDETAIL_LISTUP_DTO> getOfferList(int pageNum, String cdComplete, String searchMode, String searchKey) {

        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("offerIdx").descending());
        Page<OfferDetail> list = null;

        if( cdComplete.equals("A") ){
            if( searchMode.equals("store") )
                list = offerRepository.findAllByNmStore(searchKey, pageable);
            else
                list = offerRepository.findAll(pageable);
        } else if( cdComplete.equals("Y") || cdComplete.equals("N") ) {
            if( searchMode.equals("store") )
                list = offerRepository.findAllByCdCompleteAndNmStore(cdComplete, searchKey, pageable);
            else
                list = offerRepository.findAllByCdComplete(cdComplete, pageable);
        } else {
            list = offerRepository.findAll(pageable);
        }

        return list.map(this::converToListUpDto);
    }

    private OFFERDETAIL_LISTUP_DTO converToListUpDto(OfferDetail offerDetail){
        return OFFERDETAIL_LISTUP_DTO.builder()
                .offerIdx(offerDetail.getOfferIdx())
                .nmStore(offerDetail.getNmStore())
                .nmCeo(offerDetail.getNmCeo())
                .strTel(offerDetail.getStrTel())
                .cdAdj(offerDetail.getCdAdj())
                .strMsg(offerDetail.getStrMsg())
                .dtInsert(offerDetail.getDtInsert()).build();
    }

    public OFFERDETAIL_UPDATE_DTO updateOffer(OFFERDETAIL_UPDATE_DTO offerDto) {

        OfferDetail offer = offerRepository.findOneById(offerDto.getOfferIdx()).orElseGet(() -> new OfferDetail(offerDto.getOfferIdx(), "none"));
        if( !offer.getNmStore().equals("none") ){
            try{
                OfferDetail rstOffer = OfferDetail.builder()
                        .offerIdx(offer.getOfferIdx())
                        .nmStore(offer.getNmStore())
                        .nmCeo(offer.getNmCeo())
                        .strTel(offer.getStrTel())
                        .cdAdj(offer.getCdAdj())
                        .strMsg(offer.getStrMsg())
                        .cdComplete(offerDto.getCdComplete())
                        .dtUpdate(new Date()).build();
                offerRepository.save(rstOffer);
            } catch (Exception e){
                log.debug(e.getMessage());
                offer = new OfferDetail(offerDto.getOfferIdx(), "none");
            }
        }

        return OFFERDETAIL_UPDATE_DTO.builder()
                .offerIdx(offer.getOfferIdx())
                .nmStore(offer.getNmStore()).build();
    }
}
