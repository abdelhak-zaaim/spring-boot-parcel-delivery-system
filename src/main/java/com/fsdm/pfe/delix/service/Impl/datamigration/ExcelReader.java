/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 20:12
 *  * @modified : 30/05/2024, 20:12
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration;

import com.fsdm.pfe.delix.service.datamegration.DataReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ExcelReader implements DataReader {
    @Override
    public List<String> readData(File file) {
        return List.of();
    }
}
