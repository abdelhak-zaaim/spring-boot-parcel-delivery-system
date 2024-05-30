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

import java.io.File;
import java.util.List;

public interface DataReader
{
   List<String> readData(File file);

}
