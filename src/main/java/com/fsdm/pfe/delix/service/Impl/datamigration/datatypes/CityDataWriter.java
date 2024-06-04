/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:40
 *  * @modified : 04/06/2024, 19:40
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.datatypes;

import com.fsdm.pfe.delix.entity.City;
import com.fsdm.pfe.delix.repository.CityRepo;
import com.fsdm.pfe.delix.service.datamegration.DataWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CityDataWriter implements DataWriter<City> {
   private final CityRepo cityRepo;
private static final Logger LOGGER = LoggerFactory.getLogger(CityDataWriter.class);
    public CityDataWriter(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
   public void writeDataToDatabase(List<City> data) {
        for (City city : data) {
            try {
                cityRepo.save(city);
            } catch (Exception e) {
                LOGGER.error("Failed to save city: {}. Error: {}", city, e.getMessage());
            }
        }
   }
}
