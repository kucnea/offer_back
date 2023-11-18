package com.phy.Repository;

import com.phy.Entity.OfferDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository  extends JpaRepository<OfferDetail, Long> {

    Page<OfferDetail> findAllByNmStore(String nmStore, Pageable pageable);
    Page<OfferDetail> findAllByCdComplete(String cdComplete, Pageable pageable);
    Page<OfferDetail> findAllByCdCompleteAndNmStore(String cdComplete, String nmStore, Pageable pageable);
    Optional<OfferDetail> findOneById(Long offerIdx);

}
