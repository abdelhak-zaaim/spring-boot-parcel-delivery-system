/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:34
 *  * @modified : 04/06/2024, 19:34
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import java.util.List;

public interface DataWriter<T> {
    void writeDataToDatabase(T data);
}
