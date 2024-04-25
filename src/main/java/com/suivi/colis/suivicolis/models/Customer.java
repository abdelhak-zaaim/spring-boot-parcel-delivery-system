/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:31
 *  * @modified : 23/04/2024, 18:31
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(Role.CUSTOMER_ROLE)

public class Customer extends User{

    @Column( unique = true)
    private String customerNumber;







}
