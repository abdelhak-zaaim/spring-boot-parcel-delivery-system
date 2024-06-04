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

import com.fsdm.pfe.delix.service.datamegration.DataReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.LinkedList;

public class ExelDataReaderImpl implements DataReader {
   @Override
   public LinkedList<String> readDataFrmRow(Object object) {
      Row row = (Row) object;
      LinkedList<String> data = new LinkedList<>();
      for (Cell cell : row) {
         data.add(cell.getStringCellValue());
      }

      return data;

   }
}
