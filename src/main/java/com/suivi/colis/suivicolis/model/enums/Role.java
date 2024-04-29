/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:05
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.model.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Getter
public enum Role {

    CUSTOMER(new SimpleGrantedAuthority("CUSTOMER")),
    EMPLOYEE(new SimpleGrantedAuthority("EMPLOYEE")),

    DELIVERY_MAN(new SimpleGrantedAuthority("DELIVERY")),
    TRANSPORTER(new SimpleGrantedAuthority("TRANSPORTER")),
    AGENCY_EMPLOYEE(new SimpleGrantedAuthority("AGENCY_EMPLOYEE")),
    SUPER_ADMIN(new SimpleGrantedAuthority("SUPER_ADMIN")),
    ADMIN_EMPLOYEE(new SimpleGrantedAuthority("ADMIN_EMPLOYEE")),;

    public static final String USER_ROLE_NAME = "ROLE";


    public static final String SUPER_ADMIN_RULE = "ADMIN";
    public static final String CUSTOMER_ROLE = "CUSTOMER";
    public static final String DELIVERY_MAN_ROLE = "DELIVERY";
    public static final String AGENCY_EMPLOYEE_ROLE = "AGENCY_EMPLOYEE";
    public static final String TRANSPORTER_ROLE = "TRANSPORTER";
    public static final String EMPLOYEE_ROLE = "EMPLOYEE";
    public static final String ADMIN_EMPLOYEE_ROLE = "ADMIN_EMPLOYEE";


    private final GrantedAuthority grantedAuthority;

    Role(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

}