/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:05
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.model.enums;

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


    public static final String SUPER_ADMIN_RULE = "SUPER_ADMIN";
    public static final String CUSTOMER_ROLE = "CUSTOMER";
    public static final String DELIVERY_MAN_ROLE = "DELIVERY";
    public static final String AGENCY_EMPLOYEE_ROLE = "AGENCY_EMPLOYEE";
    public static final String TRANSPORTER_ROLE = "TRANSPORTER";
    public static final String EMPLOYEE_ROLE = "EMPLOYEE";
    public static final String ADMIN_ROLE = "ADMIN";


    private final GrantedAuthority grantedAuthority;

    Role(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

}