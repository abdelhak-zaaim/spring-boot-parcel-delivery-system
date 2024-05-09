/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:47
 *  * @modified : 01/05/2024, 18:47
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.Address;

public interface AddressService {
    void deleteAddress(Long id);
    Address loadAddressById(Long id);
    Address saveAddress(Address address);
    Address updateAddress(Address address);
}
