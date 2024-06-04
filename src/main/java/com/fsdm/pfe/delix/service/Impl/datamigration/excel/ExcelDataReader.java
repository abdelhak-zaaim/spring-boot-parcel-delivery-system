/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:39
 *  * @modified : 04/06/2024, 19:39
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.excel;

import com.fsdm.pfe.delix.service.datamegration.DataReader;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataReader implements DataReader {
    @Override
    public List<String[]> readDataFromFile(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<String[]> data = new ArrayList<>();

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();

                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }

                data.add(rowData.toArray(new String[0]));
            }

            return data;
        }
    }
}
