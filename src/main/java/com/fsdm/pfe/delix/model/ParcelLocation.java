/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:25
 *  * @modified : 26/04/2024, 01:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.model;

import com.fsdm.pfe.delix.model.enums.HandlerType;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelLocation {
    private HandlerType currentUserType;
    private Long userId;
}
