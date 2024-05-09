/*
 *
 *  * @project : DeliX
 *  * @created : 09/05/2024, 21:36
 *  * @modified : 09/05/2024, 21:36
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.model.enums.ParcelStatus;
import com.fsdm.pfe.delix.model.enums.ParcelType;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Parcel}
 */

@Value
public class ParcelResponseDto implements Serializable {


    Long id;
    String codeBar;
    float height;
    float width;
    float weight;
    ParcelStatus status;
    ParcelType Type;
    Date creationDate;
    Date lastUpdateDate;
    Date estimatedDeliveryDate;
    Date deleveryDate;
    DeliveryAddressResponseDto pickupAddress;
    DeliveryAddressResponseDto receiverAddress;

    public ParcelResponseDto(Parcel parcel) {
        this.id = parcel.getId();
        this.codeBar = parcel.getCodeBar();
        this.height = parcel.getHeight();
        this.width = parcel.getWidth();
        this.weight = parcel.getWeight();
        this.status = parcel.getStatus();
        this.Type = parcel.getType();
        this.creationDate = parcel.getCreationDate();
        this.lastUpdateDate = parcel.getLastUpdateDate();
        this.estimatedDeliveryDate = parcel.getEstimatedDeliveryDate();
        this.deleveryDate = parcel.getDeleveryDate();
        this.pickupAddress = new DeliveryAddressResponseDto(parcel.getPickupAddress());
        this.receiverAddress = new DeliveryAddressResponseDto(parcel.getReceiverAddress());
    }
}