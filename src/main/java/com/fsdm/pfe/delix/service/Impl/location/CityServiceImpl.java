/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:24
 *  * @modified : 11/05/2024, 14:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.location;

import com.fsdm.pfe.delix.dto.response.CityResponseDto;
import com.fsdm.pfe.delix.dto.response.ProvinceResponseDto;
import com.fsdm.pfe.delix.entity.location.City;
import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.repository.location.CityRepo;
import com.fsdm.pfe.delix.service.location.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepo cityRepo;

    public CityServiceImpl(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
    public City create(City city) {
        return cityRepo.save(city);
    }

    @Override
    public City update(City city) {
        return cityRepo.save(city);
    }

    @Override
    public City loadById(Long id) {
        return cityRepo.findById(id).orElse(null);
    }

    @Override
    public City loadByCode(String code) {
        return cityRepo.findByCode(code).orElse(null);
    }

    @Override
    public List<City> loadAll() {
        return cityRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        cityRepo.deleteById(id);
    }

    @Override
    public void deleteByCode(String code) {
        cityRepo.deleteByCode(code);
    }

    @Override
    public List<City> saveAll(List<City> cities) {
        return cityRepo.saveAll(cities);
    }


    public static List<CityResponseDto> convertListToDto(List<City> cities) {
        return cities.stream()
                .map(CityResponseDto::new)
                .collect(Collectors.toList());
    }
}
