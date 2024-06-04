/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:38
 *  * @modified : 04/06/2024, 19:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.csv;

import com.fsdm.pfe.delix.service.datamegration.DataReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class CsvDataReaderImpl implements DataReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvDataReaderImpl.class);
    @Override
    public List<String[]> readDataFromFile(MultipartFile file) throws IOException {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {
            return csvReader.readAll();
        } catch (CsvException e) {
            LOGGER.error("Failed to parse CSV file: {}. Error: {}", file.getOriginalFilename(), e.getMessage());
            throw new RuntimeException("Failed to parse CSV file: " + file.getOriginalFilename(), e);
        }
    }
}
