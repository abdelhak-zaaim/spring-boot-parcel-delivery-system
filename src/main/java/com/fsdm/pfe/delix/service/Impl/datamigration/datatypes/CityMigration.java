/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 18:55
 *  * @modified : 04/06/2024, 18:55
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.datatypes;

import com.fsdm.pfe.delix.entity.City;
import com.fsdm.pfe.delix.repository.CityRepo;
import com.fsdm.pfe.delix.service.Impl.datamigration.DataMigrationServiceImpl;
import com.fsdm.pfe.delix.service.datamegration.DataType.WriteDataToDatabase;

public class CityMigration extends DataMigrationServiceImpl implements WriteDataToDatabase {
   private final CityRepo cityRepo;

    public CityMigration(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
   public void writeDataToDatabase(Object object) {
        try {
             City city = (City) object;
             cityRepo.save(city);
             saveLog("City " + city.getName() + " has been added successfully");
        } catch (Exception e) {
             saveLog("Error while adding city to database: " + e.getMessage());
        }
   }
}
