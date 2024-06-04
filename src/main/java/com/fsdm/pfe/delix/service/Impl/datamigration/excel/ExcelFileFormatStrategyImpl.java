/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 20:32
 *  * @modified : 04/06/2024, 20:32
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.excel;

import com.fsdm.pfe.delix.service.datamegration.DataProcessor;
import com.fsdm.pfe.delix.service.datamegration.DataReader;
import com.fsdm.pfe.delix.service.datamegration.FileFormatStrategy;
import org.springframework.web.multipart.MultipartFile;

public class ExcelFileFormatStrategyImpl implements FileFormatStrategy {
    @Override
    public boolean supports(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"));
    }

    @Override
    public DataReader createDataReader() {
        return new ExcelDataReader();
    }


}