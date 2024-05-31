/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 20:00
 *  * @modified : 30/05/2024, 20:00
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration;

import com.fsdm.pfe.delix.service.datamegration.DataMigrationService;
import com.fsdm.pfe.delix.service.datamegration.DataProcessor;
import com.fsdm.pfe.delix.service.datamegration.DataReader;
import com.fsdm.pfe.delix.service.datamegration.DataWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Service
public class DataMigrationServiceImpl implements DataMigrationService {

   private final DataReader dataReader;
   private final DataProcessor dataProcessor;
   private final DataWriter dataWriter;

   public DataMigrationServiceImpl(DataReader dataReader, DataProcessor dataProcessor, DataWriter dataWriter) {
      this.dataReader = dataReader;
      this.dataProcessor = dataProcessor;
      this.dataWriter = dataWriter;
   }


   @Override
   public void migrateData(File file) {
      List<String> data = dataReader.readData(file);
      List<String> processedData = dataProcessor.processData(data);
      dataWriter.writeData(processedData);
   }
}
