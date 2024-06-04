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

import com.fsdm.pfe.delix.service.datamegration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DataMigrationImpl implements DataMigration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataMigrationImpl.class);
    private List<String> errorLog = new LinkedList<>();

    private final DataReader dataReader;
    private final DataWriter dataWriter;
    private final DataProcessor dataProcessor;


    public DataMigrationImpl(DataReader dataReader, DataWriter dataWriter, DataProcessor dataProcessor) {
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
        this.dataProcessor = dataProcessor;

    }



    @Override
    public void migrateData(MultipartFile file) {
        try {
            List<String[]> rawData = dataReader.readDataFromFile(file);
            for (String[] row : rawData) {
                processAndWriteRow(row);
            }
        } catch (IOException e) {
            logError("Failed to read data from file. Error: ", e);
        }
    }

    private void processAndWriteRow(String[] row) {
        try {
            Object processedData = dataProcessor.processRowData(row);
            dataWriter.writeDataToDatabase(processedData);
        } catch (Exception e) {
            logError("Failed to process row: " + Arrays.toString(row) + ". Error: ", e);
        }
    }

    private void logError(String message, Exception e) {
        errorLog.add(message + e.getMessage());
        LOGGER.error(message + "{}", e.getMessage());
    }

    private String getErrorLogAsString() {
        return String.join("\n", errorLog);
    }

    public File getErrorLogAsFile() {
        if (errorLog.isEmpty()) {
            return null;
        }
        try {
            File errorLogFile = File.createTempFile("error-log", ".txt");
            org.apache.commons.io.FileUtils.writeStringToFile(errorLogFile, getErrorLogAsString(), StandardCharsets.UTF_8);
            return errorLogFile;
        } catch (IOException e) {
            LOGGER.error("Failed to create error log file. Error: {}", e.getMessage());
            return null;
        }
    }


}
