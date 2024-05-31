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

import com.fsdm.pfe.delix.model.datamigration.ImportObjectType;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Locale;

@Controller
public class MigrationController {
    private final MessageSource messageSource;

    public MigrationController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/admin/data/migration/import")
    public String migration(Model model) {
       ImportObjectType[] importObjectTypes = ImportObjectType.values();

       model.addAttribute("importTypes",importObjectTypes);
        return "admin/migration/dataMigration";
    }

}
