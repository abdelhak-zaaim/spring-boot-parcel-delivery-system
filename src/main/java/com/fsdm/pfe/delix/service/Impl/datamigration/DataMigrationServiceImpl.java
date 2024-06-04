/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 18:11
 *  * @modified : 04/06/2024, 18:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration;

import com.fsdm.pfe.delix.service.datamegration.DataMigrationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.LinkedList;

@Service
/**
 * Abstract class for data migration service
 * Contains the common methods for data migration


 */
public abstract class DataMigrationServiceImpl implements DataMigrationService {
    protected LinkedList<String> dataLogs = new LinkedList<>();


    @Override
    public void migrateData(MultipartFile file) {

    }

    @Override
    public void saveLog(String log) {
        dataLogs.add(log);
    }

    @Override
    public LinkedList<String> getDataLogs() {
        if (dataLogs.isEmpty())
            return null;
        return dataLogs;
    }

    @Override
    public MultipartFile getLogsAsFile() {
        if (dataLogs.isEmpty())
            return null;
        else {
            String fileName = "logs_" + LocalDate.now() + ".log";
            MultipartFile multipartFile = null;
            try {
                Files.write(Files.createTempFile(fileName, ".log"), dataLogs.toString().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return multipartFile;
        }
    }
}
