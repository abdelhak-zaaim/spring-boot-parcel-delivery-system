/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 20:50
 *  * @modified : 04/06/2024, 20:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.model.datamigration.ImportObjectType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DataMigrationService {
    void migrateData(MultipartFile file, ImportObjectType objectType) throws IOException;

}
