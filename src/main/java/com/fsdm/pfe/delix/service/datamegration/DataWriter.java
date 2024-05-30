/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 19:54
 *  * @modified : 30/05/2024, 19:54
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface DataWriter {
    void writeData(List<String> data);
}
