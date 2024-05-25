
/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:11
 *  * @modified : 11/05/2024, 14:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepo extends JpaRepository<Area, Long> {
    Area findByCode(String code);

    void deleteByCode(String code);

    List<Area> findByCityCode_Code(String cityCode);
}