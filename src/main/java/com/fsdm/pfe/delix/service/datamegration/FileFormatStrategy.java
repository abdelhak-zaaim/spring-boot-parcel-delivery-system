/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 20:31
 *  * @modified : 04/06/2024, 20:31
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import org.springframework.web.multipart.MultipartFile;

public interface FileFormatStrategy {
    boolean supports(MultipartFile file);
    DataReader createDataReader();
}