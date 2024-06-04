/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 20:31
 *  * @modified : 04/06/2024, 20:31
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.csv;

import com.fsdm.pfe.delix.service.datamegration.FileFormatStrategy;
import com.fsdm.pfe.delix.service.datamegration.DataProcessor;
import com.fsdm.pfe.delix.service.datamegration.DataReader;
import org.springframework.web.multipart.MultipartFile;

public class CsvFileFormatStrategyImpl implements FileFormatStrategy {
    @Override
    public boolean supports(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && fileName.endsWith(".csv");
    }

    @Override
    public DataReader createDataReader() {
        return new CsvDataReaderImpl();
    }

}

