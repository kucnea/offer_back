package com.phy.Repository;

import com.phy.Entity.SV_Commoncode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SV_CommonCodeRepository extends JpaRepository<SV_Commoncode, String> {

    Optional<SV_Commoncode> findByCdGroupAndYnDefault(String cd_group, String yn_default);
    Optional<SV_Commoncode> findAllByCdGroup(String cd_group);

}
