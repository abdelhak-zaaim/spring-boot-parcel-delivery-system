
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:09
 *  * @modified : 23/04/2024, 19:09
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.dto.request.GetQuoteRequestDto;
import com.fsdm.pfe.delix.dto.request.ParcelRequestDto;
import com.fsdm.pfe.delix.dto.response.ParcelResponseDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.entity.DeliveryAddress;
import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.model.enums.ParcelStatus;
import com.fsdm.pfe.delix.model.enums.ParcelType;
import com.fsdm.pfe.delix.repository.ParcelRepo;

import com.fsdm.pfe.delix.service.ParcelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService {


    private static final double BASE_PRICE_REGULAR = 10.0;
    private static final double BASE_PRICE_EXPRESS = 30.0;
    private static final double DENSITY_FACTOR = 1;
    private static final double DISTANCE_FACTOR = 0.3;


    private final ParcelRepo parcelRepository;
    private final DeliveryAddressServiceImpl deliveryAddressService;

    public ParcelServiceImpl(ParcelRepo parcelRepository, DeliveryAddressServiceImpl deliveryAddressService) {
        this.parcelRepository = parcelRepository;
        this.deliveryAddressService = deliveryAddressService;
    }

    @Override
    public void deleteParcel(Long id) {
        parcelRepository.deleteById(id);
    }

    @Override
    public Parcel loadParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

    @Override
    public Parcel saveParcel(Parcel parcel) {
        parcel.setCodeBar(generateParcelBarcode());
        return parcelRepository.save(parcel);
    }

    @Override
    public Parcel updateParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public Parcel markAsDeleted(Long id) {
        Parcel parcel = parcelRepository.findById(id).orElse(null);
        if (parcel != null) {
            parcel.setStatus(ParcelStatus.DELETED);
            return parcelRepository.save(parcel);
        }
        return null;
    }

    @Override
    public List<Parcel> loadAllByCreationDateBetween(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return parcelRepository.findAllByCreationDateBetween(startDate, endDate);
        } else return List.of();
    }

    public String generateParcelBarcode() {
        SecureRandom random = new SecureRandom();
        String barcode;
        do {
            int randomNumber = 1000000 + random.nextInt(9000000); // generates a random number between 1000000 and 9999999
            barcode = "PR" + randomNumber + "M";
        } while (parcelRepository.existsByCodeBar(barcode));

        return barcode;
    }

    public List<Parcel> getParcelsForCustomer(Long customerId) {
        return parcelRepository.findAllBySenderCustomerId(customerId);
    }



    public ParcelResponseDto convertEntityToResponseDto(Parcel parcel) {
        return new ParcelResponseDto(parcel);
    }

    public List<ParcelResponseDto> convertEntityListToResponseDtoList(List<Parcel> parcels) {
        return parcels.stream().map(this::convertEntityToResponseDto).toList();
    }


    public static List<Map<String, String>> getParcelTypesAsArrayOfMaps() {
        List<Map<String, String>> list = new ArrayList<>();
        for (ParcelType parcelType : ParcelType.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", parcelType.name());
            map.put("message", parcelType.getMessage());
            list.add(map);
        }
        return list;
    }

    @Transactional
    public Parcel saveParcelFromDto(ParcelRequestDto parcelRequestDto) {

        DeliveryAddress pickupAddress = deliveryAddressService.convertRequestToEntity(parcelRequestDto.getPickupRequestAddress());
        DeliveryAddress receiverAddress = deliveryAddressService.convertRequestToEntity(parcelRequestDto.getReceiverAddress());


        Parcel parcel = new Parcel();
        parcel.setHeight(parcelRequestDto.getHeight());
        parcel.setWidth(parcelRequestDto.getWidth());
        parcel.setLength(parcelRequestDto.getLength());
        parcel.setWeight(parcelRequestDto.getWeight());
        parcel.setType(parcelRequestDto.getType());
        parcel.setAppointmentTime(parcelRequestDto.getAppointmentTime());
        parcel.setCodeBar(generateParcelBarcode());

        parcel.setPickupAddress(deliveryAddressService.saveDeliveryAddress(pickupAddress));
        parcel.setReceiverAddress(deliveryAddressService.saveDeliveryAddress(receiverAddress));
        parcel.setStatus(ParcelStatus.PENDING);
        return parcelRepository.save(parcel);


    }


    public int generateQuote(GetQuoteRequestDto requestDto) {
        double basePrice;

        if (requestDto.getParcelType() == ParcelType.DOCUMENT) {
            basePrice = BASE_PRICE_REGULAR;
        } else {
            basePrice = BASE_PRICE_EXPRESS;
        }

        double volume = requestDto.getLength() * requestDto.getWidth() * requestDto.getHeight();

        double density = requestDto.getWeight() / volume;

        double distance = calculateDistance(requestDto.getPickUpArea(), requestDto.getDeliveryArea());

        double quote = basePrice + (density * DENSITY_FACTOR) + (distance * DISTANCE_FACTOR);

        return (int)quote;
    }

    private double calculateDistance(String pickUpArea, String deliveryArea) {






        return 100;
    }


}




