package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.PrivilegesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PrivilegesGroupRepo extends JpaRepository<PrivilegesGroup, Long> {
}
