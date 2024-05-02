/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 15:38
 *  * @modified : 26/04/2024, 15:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.controller.test;

import com.suivi.colis.delix.entity.AdminEmployee;
import com.suivi.colis.delix.service.Impl.AdminEmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class AdminTest {
    private AdminEmployeeServiceImpl adminService;

    public AdminTest(AdminEmployeeServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/test/admin")
    public ResponseEntity<String> testAdmin() {
        AdminEmployee admin = new AdminEmployee();
        admin.setId(1L);
        admin.setName("AdminEmployee");
        admin.setEmail("fhefgh@ghr.rg");
        admin.setPassword("admin");
        admin.setRole("AdminEmployee");
        admin.setPhoneNumber("1234567890");
        admin.setDateOfBirth(new Date("12/12/2000"));
        return ResponseEntity.ok(adminService.saveAdmin(admin).toString());
    }

    @GetMapping("/test/error")
    public String errorPage() {
        return "error";
    }
}
