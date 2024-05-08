/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:47
 *  * @modified : 26/04/2024, 17:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:49
 *  * @modified : 26/04/2024, 01:06
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 17:57
 *  * @modified : 23/04/2024, 17:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.dto.response.AddressResponseDto;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String street;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String state;

    @NotNull
    @Column(nullable = false)
    private String zip;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(255) default 'Morocco'")
    private String country;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public Address(String street, String city, String state, String zip, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    @PrePersist
    protected void onCreated() {
        Date date = new Date();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }

    public AddressResponseDto toAddressResponseDto() {

        return new AddressResponseDto(this.getId(),  this.getStreet(), this.getState(), this.getZip(),  this.getCountry(), this.getCity());


    }

}
