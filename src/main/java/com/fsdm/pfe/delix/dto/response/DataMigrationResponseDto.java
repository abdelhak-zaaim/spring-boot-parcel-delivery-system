/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 17:37
 *  * @modified : 04/06/2024, 17:37
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.io.Serializable;

@Builder
@Data
public class DataMigrationResponseDto implements Serializable {
    private String message;
    private String status;
    File logsFile;
}

