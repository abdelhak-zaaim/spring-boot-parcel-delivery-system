/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:18
 *  * @modified : 11/05/2024, 14:18
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.location;

import com.fsdm.pfe.delix.dto.response.AreaResponseDto;
import com.fsdm.pfe.delix.dto.response.CityResponseDto;
import com.fsdm.pfe.delix.entity.location.Area;
import com.fsdm.pfe.delix.entity.location.City;
import com.fsdm.pfe.delix.repository.location.AreaRepo;
import com.fsdm.pfe.delix.service.location.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService {
   private final AreaRepo areaRepo;

    public AreaServiceImpl(AreaRepo areaRepo) {
        this.areaRepo = areaRepo;
    }

    @Override
   public Area create(Area area) {
      return areaRepo.save(area);
   }

   @Override
   public Area update(Area area) {
      return areaRepo.save(area);
   }

   @Override
   public Area loadById(Long id) {
      return areaRepo.findById(id).orElse(null);
   }



   @Override
   public Area loadByCode(String code) {
      return areaRepo.findByCode(code);
   }

   @Override
   public List<Area> loadAll() {
      return areaRepo.findAll();
   }

   @Override
   public void delete(Long id) {
        areaRepo.deleteById(id);
   }

   @Override
   public void deleteByCode(String code) {
        areaRepo.deleteByCode(code);
   }

   @Override
   public List<Area> saveAll(List<Area> areas) {
      return areaRepo.saveAll(areas);
   }

   public static List<AreaResponseDto> convertListToDto(List<Area> areas) {
      return areas.stream()
              .map(AreaResponseDto::new)
              .collect(Collectors.toList());
   }

}
