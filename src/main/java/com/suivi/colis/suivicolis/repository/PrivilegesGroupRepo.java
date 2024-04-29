
package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.entity.PrivilegesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PrivilegesGroupRepo extends JpaRepository<PrivilegesGroup, Long> {
}
