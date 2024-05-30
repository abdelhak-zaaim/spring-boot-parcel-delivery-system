/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 20:05
 *  * @modified : 30/05/2024, 20:05
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration;

import com.fsdm.pfe.delix.service.datamegration.DataMigrationService;
import com.fsdm.pfe.delix.service.datamegration.DataProcessor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Service
public class DataMigrationProcessor implements DataProcessor {
   @Override
   public List<String> processData(List<String> data) {
      return List.of();
   }
}
