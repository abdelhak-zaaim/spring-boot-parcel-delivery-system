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

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MigrationController {
    private final MessageSource messageSource;

    private final Validator validator;
    public MigrationController(MessageSource messageSource, Validator validator) {
        this.messageSource = messageSource;
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
     * @param dataMigrationRequestDto : from the request body
     * @return ResponseEntity<?> : include the status of the request , logs fil in cas Exeption
     */
    @PostMapping("/admin/data/migration/import")
    public ResponseEntity<?> importData(@RequestParam DataMigrationRequestDto dataMigrationRequestDto) {
        // validate the request
        DataBinder binder = new DataBinder(dataMigrationRequestDto);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();
        if (result.hasErrors()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("you have invalid "+ result.getAllErrors().size()+" invalid input").build());
        }





        return null;
    }

}
