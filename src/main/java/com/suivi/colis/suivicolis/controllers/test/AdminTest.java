/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 15:38
 *  * @modified : 26/04/2024, 15:38
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.controllers.test;

import com.suivi.colis.suivicolis.models.entities.Admin;
import com.suivi.colis.suivicolis.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class AdminTest {
   private AdminService adminService;
   public AdminTest(AdminService adminService) {
        this.adminService = adminService;
    }

        @GetMapping("/test/admin")
        public ResponseEntity<String> testAdmin() {
           Admin admin = new Admin();
              admin.setId(1L);
                admin.setName("Admin");
                admin.setEmail("gsvcvjs@eg.egr");
                admin.setPassword("admin");
                admin.setRole("Admin");
                admin.setPhone("1234567890");
                admin.setDateOfBirth(new Date("12/12/2020"));



            return ResponseEntity.ok(adminService.saveAdmin(admin).toString());
        }

    @GetMapping("/test/error")
    public String errorPage() {
        return "error";
    }
}
