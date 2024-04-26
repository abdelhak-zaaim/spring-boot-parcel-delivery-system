
package com.suivi.colis.suivicolis.repositorys;

import com.suivi.colis.suivicolis.models.entities.PrivilegesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PrivilegesGroupRepo extends JpaRepository<PrivilegesGroup, Long> {
}
