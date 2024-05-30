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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MigrationController {
   @GetMapping("/admin/data/migration/import")
    public String migration() {
        return "admin/migration/dataMigration";
    }

}
