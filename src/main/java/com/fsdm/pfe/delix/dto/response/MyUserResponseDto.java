/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 19:02
 *  * @modified : 18/05/2024, 19:02
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.entity.Address;
import com.fsdm.pfe.delix.entity.User;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class MyUserResponseDto {

     String firstName;


     String lastName;


     String email;


     String role;


   
     String phoneNumber;


     Address address;


     Date registeredAt;

   
     Date lastUpdateDate;



     String cin;
    public MyUserResponseDto(String firstName, String lastName, String email, String role, String phoneNumber, Address address, Date registeredAt, Date lastUpdateDate, String cin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registeredAt = registeredAt;
        this.lastUpdateDate = lastUpdateDate;
        this.cin = cin;
    }

    public MyUserResponseDto(User user) {
        this.cin = user.getCin();
        this.lastUpdateDate = user.getLastUpdateDate();
        this.registeredAt = user.getRegisteredAt();
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
    }
}
