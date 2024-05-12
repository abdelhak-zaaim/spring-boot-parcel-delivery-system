/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:17
 *  * @modified : 11/05/2024, 14:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.location;

import com.fsdm.pfe.delix.entity.location.Area;

import java.util.List;

public interface AreaService {
    Area create(Area area);
    Area update(Area area);
    Area loadById(Long id);
    Area loadByCode(String code);
    List<Area> loadAll();
    void delete(Long id);
    void deleteByCode(String code);
    List<Area> saveAll(List<Area> areas);
}
