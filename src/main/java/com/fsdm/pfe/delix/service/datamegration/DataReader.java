/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 19:52
 *  * @modified : 30/05/2024, 19:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface DataReader
{
   List<String[]> readDataFromFile(MultipartFile file) throws IOException;

}
