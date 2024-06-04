/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 21:03
 *  * @modified : 04/06/2024, 21:03
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.datatypes;

import com.fsdm.pfe.delix.entity.Province;
import com.fsdm.pfe.delix.repository.ProvinceRepo;
import com.fsdm.pfe.delix.service.datamegration.DataWriter;

public class ProvinceDataWriter implements DataWriter<Province> {
    private final ProvinceRepo provinceRepo;

    public ProvinceDataWriter(ProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    @Override
    public void writeDataToDatabase(Province data) {

    }
}
