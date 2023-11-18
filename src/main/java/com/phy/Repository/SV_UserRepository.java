package com.phy.Repository;

import com.phy.Entity.SV_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SV_UserRepository extends JpaRepository<SV_User, Long> {

    Optional<SV_User> findByLoginIdAndLoginPw(String loginId, String loginPw);
    Optional<SV_User> findByLoginId(String login_id);

}
