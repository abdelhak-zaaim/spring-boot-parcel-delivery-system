/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 13:47
 *  * @modified : 26/04/2024, 01:53
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 17:01
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:31
 *  * @modified : 23/04/2024, 18:31
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;

import com.suivi.colis.suivicolis.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(Role.CUSTOMER_ROLE)

public class Customer extends User {

    @Column( unique = true )
    private String customerNumber;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Parcel> parcels;

}
