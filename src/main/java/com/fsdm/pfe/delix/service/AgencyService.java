/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:23
 *  * @modified : 01/05/2024, 19:23
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.Agency;

import java.util.List;

public interface AgencyService {
    void deleteAgency(Long id);

    Agency loadAgencyById(Long id);

    Agency saveAgency(Agency agency);

    Agency updateAgency(Agency agency);

    List<Agency> getAllAgencies();
}
