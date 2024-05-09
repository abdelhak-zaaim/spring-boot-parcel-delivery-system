/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:38
 *  * @modified : 23/04/2024, 18:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.model.enums;

import lombok.Getter;

@Getter
public enum Privilege {
    PARCELS_ADD(PrivilegeType.PARCELS, PrivilegeAction.ADD),
    PARCELS_EDIT(PrivilegeType.PARCELS, PrivilegeAction.EDIT),
    PARCELS_READ(PrivilegeType.PARCELS, PrivilegeAction.READ),
    PARCELS_DELETE(PrivilegeType.PARCELS, PrivilegeAction.DELETE),
    AGENCY_READ(PrivilegeType.AGENCY, PrivilegeAction.READ),
    AGENCY_EDIT(PrivilegeType.AGENCY, PrivilegeAction.EDIT),
    EMPLOYEE_EDIT(PrivilegeType.EMPLOYEE, PrivilegeAction.EDIT),
    EMPLOYEE_READ(PrivilegeType.EMPLOYEE, PrivilegeAction.READ),
    DELIVERY_EDIT(PrivilegeType.DELIVERY, PrivilegeAction.EDIT),
    DELIVERY_READ(PrivilegeType.DELIVERY, PrivilegeAction.READ),
    TRANSPORT_READ(PrivilegeType.TRANSPORT, PrivilegeAction.READ),
    TRANSPORT_EDIT(PrivilegeType.TRANSPORT, PrivilegeAction.EDIT)

    ;

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
        DELIVERY,
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
