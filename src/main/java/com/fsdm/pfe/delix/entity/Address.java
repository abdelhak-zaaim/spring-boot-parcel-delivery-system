/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 17:57
 *  * @modified : 23/04/2024, 17:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.dto.response.AddressResponseDto;
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

    @ManyToOne
    Area area;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;


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
        return new AddressResponseDto(this);
    }
}