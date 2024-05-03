/*
 * **
 *  * @project : DeliX
 *  * @created : 30/04/2024, 16:18
 *  * @modified : 30/04/2024, 16:18
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix;

import com.suivi.colis.delix.entity.Address;
import com.suivi.colis.delix.entity.Customer;
import com.suivi.colis.delix.entity.DeliveryAddress;
import com.suivi.colis.delix.entity.Parcel;
import com.suivi.colis.delix.model.enums.ParcelStatus;
import com.suivi.colis.delix.service.Impl.AddressServiceImpl;
import com.suivi.colis.delix.service.Impl.CustomerServiceImpl;
import com.suivi.colis.delix.service.Impl.DeliveryAddressServiceImpl;
import com.suivi.colis.delix.service.Impl.ParcelServiceImpl;
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
        deliveryAddress.setCity("fes");
        deliveryAddress.setCountry("Morocco");
        deliveryAddress.setPostalCode("20000");
        deliveryAddress.setContactNumber("123456789");
        deliveryAddress.setContactName("abdelhak");

        DeliveryAddress deliveryAddress1 = deliveryAddressService.saveDeliveryAddress(deliveryAddress);


        DeliveryAddress deliveryAddress2 = new DeliveryAddress();
        deliveryAddress.setAddress("rue 1");
        deliveryAddress.setCity("casablanca");
        deliveryAddress.setCountry("Morocco");
        deliveryAddress.setPostalCode("30000");
        deliveryAddress.setContactNumber("0675757677");
        deliveryAddress.setContactName("abdelhak");
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
