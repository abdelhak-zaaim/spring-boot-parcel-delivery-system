/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 17:53
 *  * @modified : 04/06/2024, 17:53
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.datamegration;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface DataValidation {
    /**
     * Check if the file format is correct for the data migration
     * @param file
     * @return
     */
    boolean checkFormat(MultipartFile file);
    boolean checkFormat(File file);
}
