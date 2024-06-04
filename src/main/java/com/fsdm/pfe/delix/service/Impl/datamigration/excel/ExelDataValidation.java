/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:00
 *  * @modified : 04/06/2024, 19:00
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.excel;

import com.fsdm.pfe.delix.service.datamegration.DataValidation;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ExelDataValidation implements DataValidation {
    private static final String EXCEL_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Override
    public boolean checkFormat(MultipartFile file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException("File is null");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return file.getContentType().equals(EXCEL_CONTENT_TYPE);
    }

    @Override
    public boolean checkFormat(@NotNull File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException("File is null");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        return file.getName().endsWith(".xlsx");
    }


}
