/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 15:38
 *  * @modified : 26/04/2024, 15:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.controller.test;

import com.fsdm.pfe.delix.entity.Admin;
import com.fsdm.pfe.delix.entity.PrivilegesGroup;
import com.fsdm.pfe.delix.model.enums.Privilege;
import com.fsdm.pfe.delix.model.enums.UserStatus;
import com.fsdm.pfe.delix.service.Impl.AdminServiceImpl;
import com.fsdm.pfe.delix.service.Impl.PrivilegesGroupServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminTest {
    private AdminServiceImpl adminService;
    private PrivilegesGroupServiceImpl privilegesGroupService;

    public AdminTest(AdminServiceImpl adminService, PrivilegesGroupServiceImpl privilegesGroupService) {
        this.adminService = adminService;
        this.privilegesGroupService = privilegesGroupService;
    }

    @GetMapping("/test/admin/add")
    public ResponseEntity<Admin> testAdmin() {
        List<Privilege> privileges = new ArrayList<>();
        privileges.add(Privilege.PARCELS_DELETE);
        privileges.add(Privilege.PARCELS_ADD);
        PrivilegesGroup privilegesGroup = new PrivilegesGroup("manager_admin", privileges);
        privilegesGroup = privilegesGroupService.savePrivilegesGroup(privilegesGroup);

        Admin admin = new Admin();
        admin.setId(1L);
        admin.setFirstName("Admin");
        admin.setLastName("Admino");
        admin.setEmail("admin2@test.com");
        admin.setPassword("12345678");
        admin.setPrivilegesGroup(privilegesGroup);
        admin.setRole("Admin");
        admin.setStatus(UserStatus.ACTIVE);
        admin.setPhoneNumber("1234567890");
        admin.setDateOfBirth(new Date("12/12/2000"));
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }

    @GetMapping("/test/error")
    public String errorPage() {
        return "error";
    }


    @GetMapping("/test/admin/get")
    public ResponseEntity<Admin> getAdmin() {
        try {
            Admin admin = adminService.loadAdminById(5L);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            throw new IllegalStateException("Error while getting the admin" + e.toString());

        }
    }

}
