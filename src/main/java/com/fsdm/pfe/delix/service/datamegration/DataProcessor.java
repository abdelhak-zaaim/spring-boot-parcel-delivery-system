/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 20:03
 *  * @modified : 30/05/2024, 20:03
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import java.util.List;

public interface DataProcessor {
   List<String> processData(List<String> data);
}
