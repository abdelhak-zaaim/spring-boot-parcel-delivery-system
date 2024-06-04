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

@Component
public class CsvDataReaderImpl implements DataReader {
    @Override
    public List<String[]> readDataFromFile(MultipartFile file) throws IOException, CsvException {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {
            return csvReader.readAll();
        }
    }
}
