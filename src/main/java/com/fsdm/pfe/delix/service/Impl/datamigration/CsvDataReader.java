package com.fsdm.pfe.delix.service.Impl.datamigration;/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 20:02
 *  * @modified : 30/05/2024, 20:01
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 20:01
 *  * @modified : 30/05/2024, 20:01
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */


import com.fsdm.pfe.delix.service.datamegration.DataReader;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvDataReader implements DataReader {
   @Override
   public List<String> readData(File file) {
      List<String> logsList = new ArrayList<>();
      List<String> dataList = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
         String line;
         while ((line = br.readLine()) != null) {
            // Validate the data here
            boolean isValid = validateData(line);
            if (isValid) {
               // Insert the data into the database
               insertDataToDatabase(line);
               dataList.add(line);
            } else {
               // Add a log to logsList
               logsList.add("Invalid data at line: " + line);
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return dataList;
   }

   private boolean validateData(String data) {



      return true;
   }

   private void insertDataToDatabase(String data) {
      // Implement your database insertion logic here
   }
}
