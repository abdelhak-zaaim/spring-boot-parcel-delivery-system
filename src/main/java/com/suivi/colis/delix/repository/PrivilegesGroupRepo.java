
package com.suivi.colis.delix.repository;

import com.suivi.colis.delix.entity.PrivilegesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PrivilegesGroupRepo extends JpaRepository<PrivilegesGroup, Long> {
}
