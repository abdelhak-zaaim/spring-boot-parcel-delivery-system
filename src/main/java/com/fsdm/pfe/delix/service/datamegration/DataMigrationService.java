/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 19:59
 *  * @modified : 30/05/2024, 19:59
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
/**
 * this interface is used to migrate data from a file to the database
 * contain base function to migrate data
 */
public interface DataMigrationService {
   void migrateData(MultipartFile file);
   void saveLog(String log);
   LinkedList<String> getDataLogs();
   MultipartFile getLogsAsFile();
}
