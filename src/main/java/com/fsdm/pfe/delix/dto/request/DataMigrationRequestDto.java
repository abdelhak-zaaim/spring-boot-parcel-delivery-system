/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 17:31
 *  * @modified : 04/06/2024, 17:31
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.model.datamigration.ImportObjectType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Value

public class DataMigrationRequestDto implements Serializable {
    @NotNull
    MultipartFile file;
    @NotNull
    ImportObjectType importType;
}
