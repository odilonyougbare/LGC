package com.lgs.repository;

import com.lgs.domain.Agence;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Agence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long>, JpaSpecificationExecutor<Agence> {

    @Query("select agence from Agence agence where agence.user.login = ?#{principal.username}")
    List<Agence> findByUserIsCurrentUser();
    List<Agence> findAgencesByClientId(Long clientId);
}
