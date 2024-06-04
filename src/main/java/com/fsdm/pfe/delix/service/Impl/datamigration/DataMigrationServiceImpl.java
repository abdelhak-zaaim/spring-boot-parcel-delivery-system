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
import com.fsdm.pfe.delix.service.datamegration.DataProcessor;
import com.fsdm.pfe.delix.service.datamegration.DataReader;
import com.fsdm.pfe.delix.service.datamegration.DataWriter;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataMigrationServiceImpl implements DataMigrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataMigrationServiceImpl.class);

    private DataReader dataReader;
    private DataWriter dataWriter;
    private DataProcessor dataProcessor;

    public DataMigrationServiceImpl(DataReader dataReader, DataWriter dataWriter, DataProcessor dataProcessor) {
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
        this.dataProcessor = dataProcessor;
    }

    @Override
    public void migrateData(MultipartFile file) {
        try {
            List<String[]> rawData = dataReader.readDataFromFile(file);
            List<Object> processedData = rawData.stream().map(row -> {
                try {
                    return dataProcessor.processRowData(row);
                } catch (Exception e) {
                    LOGGER.error("Failed to process row: {}. Error: {}", Arrays.toString(row), e.getMessage());
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            dataWriter.writeDataToDatabase(processedData);
        } catch (IOException e) {
            LOGGER.error("Failed to read data from file. Error: {}", e.getMessage());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
