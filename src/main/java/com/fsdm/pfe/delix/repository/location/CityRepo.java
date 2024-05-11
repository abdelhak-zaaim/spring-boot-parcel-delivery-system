/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:11
 *  * @modified : 11/05/2024, 14:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository.location;

import com.fsdm.pfe.delix.entity.location.Area;
import com.fsdm.pfe.delix.entity.location.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepo extends JpaRepository<City, Long> {
    Optional<City> findByCode(String code);
    void deleteByCode(String code);
}