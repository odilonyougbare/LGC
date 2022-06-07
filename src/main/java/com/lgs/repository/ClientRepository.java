package com.lgs.repository;

import com.lgs.domain.Client;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    @Query("select client from Client client where client.user.login = ?#{principal.username}")
    List<Client> findByUserIsCurrentUser();

}
