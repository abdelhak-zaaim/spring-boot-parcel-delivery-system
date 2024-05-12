/*
 * **
 *  * @project : DeliX
 *  * @created : 30/04/2024, 16:18
 *  * @modified : 30/04/2024, 16:18
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix;

import com.fsdm.pfe.delix.entity.Address;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.entity.DeliveryAddress;
import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.model.enums.ParcelStatus;
import com.fsdm.pfe.delix.service.Impl.AddressServiceImpl;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import com.fsdm.pfe.delix.service.Impl.DeliveryAddressServiceImpl;
import com.fsdm.pfe.delix.service.Impl.ParcelServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
//@Transactional
public class PaaarcelTeest {
    @Mock
    ParcelServiceImpl parcelService;
    @Mock
    CustomerServiceImpl customerService;
    @Mock
    AddressServiceImpl addressService;
    @Mock
    DeliveryAddressServiceImpl deliveryAddressService;

    @InjectMocks
    PaaarcelTeest paaarcelTeest;

//    public PaaarcelTeest(ParcelServiceImpl parcelService, CustomerServiceImpl customerService, AddressServiceImpl addressService, DeliveryAddressServiceImpl deliveryAddressService) {
//        this.parcelService = parcelService;
//        this.customerService = customerService;
//        this.addressService = addressService;
//        this.deliveryAddressService = deliveryAddressService;
//    }

    @Test
    public void addParcel() {
        Address address = new Address();
        address.setCity("fes");
        address.setCountry("Morocco");
        address.setStreet("marja");
        address.setZip("20000");
        address = addressService.saveAddress(address);
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddress("fes marja");


        DeliveryAddress deliveryAddress1 = deliveryAddressService.saveDeliveryAddress(deliveryAddress);


        DeliveryAddress deliveryAddress2 = new DeliveryAddress();

        deliveryAddress2 = deliveryAddressService.saveDeliveryAddress(deliveryAddress2);


        Parcel parcel = new Parcel();
        parcel.setStatus(ParcelStatus.IN_TRANSIT);
        parcel.setWeight(2);
        parcel.setHeight(3);
        parcel.setWeight(4);
        parcel.setPickupAddress(deliveryAddress2);
        Customer customer = customerService.loadCustomerById(1L);
        parcel.setSenderCustomer(customer);
        parcel.setReceiverAddress(deliveryAddress);


        Parcel p = parcelService.saveParcel(parcel);


        // add a parcel
    }

}
