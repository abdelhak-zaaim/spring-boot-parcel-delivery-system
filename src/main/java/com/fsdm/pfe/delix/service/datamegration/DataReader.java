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

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public interface DataReader
{
   /**
    * this function read data from object (document Row) and return a list of string
    * @param object
    * @return LinkedList<String>
    */
   LinkedList<String> readDataFrmRow(Object object);

}
