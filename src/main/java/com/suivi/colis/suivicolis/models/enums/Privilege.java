/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:38
 *  * @modified : 23/04/2024, 18:38
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Privilege {
    PARCELS_ADD(PrivilegeType.PARCELS, PrivilegeAction.ADD),
    PARCELS_EDIT(PrivilegeType.PARCELS, PrivilegeAction.EDIT),
    PARCELS_READ(PrivilegeType.PARCELS, PrivilegeAction.READ),
    PARCELS_DELETE(PrivilegeType.PARCELS, PrivilegeAction.DELETE);

    private final PrivilegeType type;
    private final PrivilegeAction action;


    Privilege(PrivilegeType type, PrivilegeAction action) {
        this.type = type;
        this.action = action;
    }

    public static boolean contains(Privilege privilege) {
        for (Privilege p : Privilege.values()) {
            if (p.equals(privilege)) {
                return true;
            }
        }
        return false;
    }

    public enum PrivilegeType {
        PARCELS,
        USER,
        EMPLOYEE,
        TRANSPORT,
        AGENCY;
    }

    public enum PrivilegeAction {
        ADD,
        EDIT,
        READ,
        DELETE;
    }
}
