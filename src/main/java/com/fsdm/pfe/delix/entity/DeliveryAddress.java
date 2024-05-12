
/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 22:54
 *  * @modified : 25/04/2024, 22:54
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsdm.pfe.delix.entity.location.Area;
import com.fsdm.pfe.delix.entity.location.Province;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DeliveryAddress  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @ManyToOne
    private Area area;

    @NotNull
    @Column(nullable = false)
    private String contactFirstName;
    @NotNull
    @Column(nullable = false)
    private String contactLastName;

    @NotNull
    @Column(nullable = false)
    private String contactNumber;

    private String contactEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdateDate;

    public DeliveryAddress(String address, String contactNumber) {
        this.address = address;
        this.contactNumber = contactNumber;
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


}
