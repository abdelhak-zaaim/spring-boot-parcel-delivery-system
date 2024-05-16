/*
 *
 *  * @project : DeliX
 *  * @created : 15/05/2024, 01:09
 *  * @modified : 15/05/2024, 01:09
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.config;

import com.fsdm.pfe.delix.entity.Admin;
import com.fsdm.pfe.delix.entity.PrivilegesGroup;
import com.fsdm.pfe.delix.model.enums.Privilege;
import com.fsdm.pfe.delix.model.enums.UserStatus;
import com.fsdm.pfe.delix.service.Impl.AdminServiceImpl;
import com.fsdm.pfe.delix.service.Impl.PrivilegesGroupServiceImpl;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class AdminCreationConfig {

    private final PrivilegesGroupServiceImpl privilegesGroupService;
    private final AdminServiceImpl adminService;
    private final PasswordEncoder passwordEncoder;

    public AdminCreationConfig(PrivilegesGroupServiceImpl privilegesGroupService, AdminServiceImpl adminService, PasswordEncoder passwordEncoder) {
        this.privilegesGroupService = privilegesGroupService;
        this.adminService = adminService;

        this.passwordEncoder = passwordEncoder;
    }

@Bean
public ApplicationRunner createAdminRunner() {
    return args -> {
        String adminEmail = "admin@admin.com";
        String adminPassword = "admin1234";

        // Check if the admin user already exists
        if (adminService.loadUserByEmail(adminEmail)== null) {
            List<Privilege> privileges = new ArrayList<>();
            privileges.add(Privilege.PARCELS_DELETE);
            privileges.add(Privilege.PARCELS_ADD);
            PrivilegesGroup privilegesGroup = new PrivilegesGroup("initial_admin", privileges);
            privilegesGroup = privilegesGroupService.savePrivilegesGroup(privilegesGroup);

            Admin admin = new Admin();
            admin.setId(1L);
            admin.setFirstName("Admin");
            admin.setLastName("Admino");
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword);
            admin.setPrivilegesGroup(privilegesGroup);
            admin.setRole("Admin");
            admin.setStatus(UserStatus.ACTIVE);
            admin.setPhoneNumber("1234567890");
            admin.setDateOfBirth(new Date("12/12/2000"));
            adminService.saveAdmin(admin);
        }
    };
}
}