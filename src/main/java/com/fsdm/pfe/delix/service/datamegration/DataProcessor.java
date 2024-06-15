/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:36
 *  * @modified : 04/06/2024, 19:36
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

public interface DataProcessor<T> {
    T processRowData(String[] row);
}
