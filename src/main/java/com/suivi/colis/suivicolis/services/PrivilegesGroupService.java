/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 15:46
 *  * @modified : 25/04/2024, 15:46
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.services;

import com.suivi.colis.suivicolis.models.PrivilegesGroup;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.repositorys.PrivilegesGroupRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegesGroupService {
   @Autowired
   private PrivilegesGroupRepo privilegesGroupRepo;




   public void addPrivilegesGroup(PrivilegesGroup privilegesGroup){
       try {
           privilegesGroupRepo.save(privilegesGroup);
       } catch (Exception e) {
           throw new RuntimeException("Failed to add privileges group", e);
       }
   }
}
