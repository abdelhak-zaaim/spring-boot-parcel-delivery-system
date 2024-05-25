
/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:10
 *  * @modified : 11/05/2024, 14:10
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepo extends JpaRepository<Province, Long> {
    Optional<Province> findByCode(String code);

    void deleteByCode(String code);
}