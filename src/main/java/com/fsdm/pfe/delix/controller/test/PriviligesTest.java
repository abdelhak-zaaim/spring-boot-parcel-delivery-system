/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:23
 *  * @modified : 29/04/2024, 19:23
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.controller.test;

import com.fsdm.pfe.delix.entity.PrivilegesGroup;
import com.fsdm.pfe.delix.model.enums.Privilege;
import com.fsdm.pfe.delix.service.Impl.PrivilegesGroupServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class PriviligesTest {
    PrivilegesGroupServiceImpl privilegesGroupService;

    public PriviligesTest(PrivilegesGroupServiceImpl privilegesGroupService) {
        this.privilegesGroupService = privilegesGroupService;
    }

    @GetMapping("/test/add/privilige")
    public ResponseEntity<PrivilegesGroup> addPriviliges() {
        PrivilegesGroup privilegesGroup = new PrivilegesGroup();
        Set<Privilege> privileges = Set.of(Privilege.PARCELS_ADD, Privilege.PARCELS_EDIT, Privilege.PARCELS_READ, Privilege.PARCELS_DELETE);
        privilegesGroup.setName("AGNTMAANAGER");
        privilegesGroup.setPrivileges(privileges);
        return ResponseEntity.ok(privilegesGroupService.savePrivilegesGroup(privilegesGroup));


    }

    @GetMapping("/test/get/privilige")
    public ResponseEntity<PrivilegesGroup> getPriviliges() {
        return ResponseEntity.ok(privilegesGroupService.loadPrivilegesGroupById(1L));
    }


}
