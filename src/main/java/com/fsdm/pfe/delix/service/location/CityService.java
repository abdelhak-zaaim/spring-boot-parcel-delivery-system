/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:16
 *  * @modified : 11/05/2024, 14:16
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.location;

import com.fsdm.pfe.delix.entity.location.City;

import java.util.List;

public interface CityService {
    City create(City city);
    City update(City city);
    City loadById(Long id);
    City loadByCode(String code);
    List<City> loadAll();
    void delete(Long id);
    void deleteByCode(String code);
    List<City> saveAll(List<City> cities);
    List<City> loadByProvinceCode(String provinceCode);
}
