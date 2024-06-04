/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 19:41
 *  * @modified : 04/06/2024, 19:41
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration.datatypes;

import com.fsdm.pfe.delix.entity.City;
import com.fsdm.pfe.delix.service.datamegration.DataProcessor;


public class CityDataProcessor implements DataProcessor<City> {
    @Override
    public City processRowData(String[] row) {
        String code = row[0];
        String name = row[1];
        String countryCode = row[2];
        String postalCode = row[3];

        City city = new City();
        city.setCode(code);
        city.setName(name);
        city.setCountryCode(countryCode);
        city.setPostalCode(postalCode);

        return city;
    }
}
