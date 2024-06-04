/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:02
 *  * @modified : 04/06/2024, 19:02
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.csv;

import com.fsdm.pfe.delix.service.datamegration.DataValidation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class CsvDataValidation implements DataValidation {

    private static final String CSV_CONTENT_TYPE = "text/csv";
    private static final String CSV_EXTENSION = ".csv";

    @Override
    public boolean checkFormat(MultipartFile file) {
        return file.getContentType().equals(CSV_CONTENT_TYPE);
    }

    @Override
    public boolean checkFormat(File file) {
        return file.getName().endsWith(CSV_EXTENSION);
    }
}
