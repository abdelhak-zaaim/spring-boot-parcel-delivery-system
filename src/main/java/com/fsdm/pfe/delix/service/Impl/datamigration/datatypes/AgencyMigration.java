/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 18:05
 *  * @modified : 04/06/2024, 18:05
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.datatypes;

import com.fsdm.pfe.delix.entity.Agency;
import com.fsdm.pfe.delix.repository.AgencyRepo;
import com.fsdm.pfe.delix.service.Impl.datamigration.DataMigrationServiceImpl;
import com.fsdm.pfe.delix.service.datamegration.DataType.WriteDataToDatabase;
import org.springframework.stereotype.Service;

@Service
public class AgencyMigration extends DataMigrationServiceImpl implements WriteDataToDatabase {
    private final AgencyRepo agencyRepo;

    public AgencyMigration(AgencyRepo agencyRepo) {
        this.agencyRepo = agencyRepo;
    }


    @Override
    public void writeDataToDatabase(Object object) {
        try {
            Agency agency = (Agency) object;
            agencyRepo.save(agency);
            saveLog("Agency " + agency.getAgencyName() + " has been added successfully");
        } catch (Exception e) {
            saveLog("Error while adding agency to database: " + e.getMessage() );
        }
    }


}
