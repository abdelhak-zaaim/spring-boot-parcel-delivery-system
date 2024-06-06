/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 16:56
 *  * @modified : 30/05/2024, 16:56
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller.admin.datamigratipn;

import com.fsdm.pfe.delix.dto.request.DataMigrationRequestDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.model.datamigration.ImportObjectType;
import com.fsdm.pfe.delix.service.Impl.datamigration.DataMigrationServiceImpl;
import com.fsdm.pfe.delix.util.helpers.FileUtils;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class MigrationController {
    private final MessageSource messageSource;
    private final DataMigrationServiceImpl dataMigrationService;
    private final Validator validator;

    public MigrationController(MessageSource messageSource, DataMigrationServiceImpl dataMigrationService, Validator validator) {
        this.messageSource = messageSource;
        this.dataMigrationService = dataMigrationService;
        this.validator = validator;
    }

    @GetMapping("/admin/data/migration/import")
    public String migration(Model model) {
        ImportObjectType[] importObjectTypes = ImportObjectType.values();

        model.addAttribute("importTypes", importObjectTypes);
        return "admin/migration/dataMigration";
    }

    @GetMapping("/admin/data/migration/export")
    public String export(Model model) {
        return "admin/migration/dataMigration";
    }

    @GetMapping("/admin/data/migration/export/download")
    public ResponseEntity<?> download(Model model) {
        return null;
    }

    /**
     * this function is called when the user post data (file and type of object)
     *
     * @return ResponseEntity<?> : include the status of the request , logs fil in cas Exeption
     */
    @PostMapping("/admin/data/migration/import")
    public ResponseEntity<?> importData(@RequestParam("file") MultipartFile fileForUpload, @RequestParam("importType") ImportObjectType importType) {
        // check te importType is not null or empty
        if (importType == null) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).message("import type is empty").build());
        }
        if(!importType.equals(ImportObjectType.LOCATION)){
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).message("import type is not supported ,currently we only support locations ").build());
        }

     // check the file is not empty
        if (fileForUpload.isEmpty()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).message("file is empty").build());
        }

        // check the file is of Excel format
        if (!FileUtils.checkExcelFormat(fileForUpload)) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).message("file is not of Excel format").build());
        }
        try {
            List<String> logs = dataMigrationService.migrateExel(fileForUpload);
            if (logs.size() > 0) {

                File file = FileUtils.convertListToFile(logs, "logs.txt");
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));


                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=importLogs.txt");
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(resource);
                //   return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).message("data imported with some errors").error(logs).build());

            }
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(true).message("data imported successfully").build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
