

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 14:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@DiscriminatorValue(Role.ADMIN_ROLE)
public class Admin extends Employee {

private String adminEmployeeNumber;





}
