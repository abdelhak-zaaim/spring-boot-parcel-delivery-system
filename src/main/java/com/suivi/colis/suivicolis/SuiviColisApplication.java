package com.suivi.colis.suivicolis;

import com.suivi.colis.suivicolis.models.PrivilegesGroup;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.services.PrivilegesGroupService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SuiviColisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuiviColisApplication.class, args);







    }

}
