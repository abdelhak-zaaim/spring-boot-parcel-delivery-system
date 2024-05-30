
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
import com.fsdm.pfe.delix.entity.*;
import com.fsdm.pfe.delix.model.ParcelVolume;
import com.fsdm.pfe.delix.model.PaymentModel;
import com.fsdm.pfe.delix.model.enums.ParcelStatus;
import com.fsdm.pfe.delix.model.enums.ParcelType;
import com.fsdm.pfe.delix.repository.ParcelRepo;
import com.fsdm.pfe.delix.service.Impl.location.AreaServiceImpl;
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
    private final AreaServiceImpl areaService;
    private final PaymentServiceImpl paymentService;
    private final PricingServiceImpl pricingService;

    public ParcelServiceImpl(ParcelRepo parcelRepository, DeliveryAddressServiceImpl deliveryAddressService, AreaServiceImpl areaService, PaymentServiceImpl paymentService, PricingServiceImpl pricingService) {
        this.parcelRepository = parcelRepository;
        this.deliveryAddressService = deliveryAddressService;
        this.areaService = areaService;
        this.paymentService = paymentService;
        this.pricingService = pricingService;
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

    @Transactional
    public void saveParcelFromDto(ParcelRequestDto parcelRequestDto) {

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

        Parcel savedParcel = parcelRepository.save(parcel);

        Payment payment = generatePaymentForParcel(savedParcel);
        savedParcel.setPayment(payment);
        parcelRepository.save(savedParcel);

    }

    Payment generatePaymentForParcel(Parcel parcel) {

        if (parcel == null) {
            throw new IllegalArgumentException("Parcel is null");
        }

        PaymentModel paymentModel = generateQuoteForParcel(parcel);
        Payment payment = Payment.builder()
                .totalCost(paymentModel.getTotalCost())
                .insuranceCost(paymentModel.getInsuranceCost())
                .deliveryFees(paymentModel.getDeliveryFees())
                .build();
        if (payment == null) {
            throw new IllegalArgumentException("Payment is null");
        }

        return paymentService.savePayment(payment);
    }


    public PaymentModel generateQuoteForParcel(Parcel parcel) {

        return generateQuote(parcel.getPickupAddress().getArea(), parcel.getReceiverAddress().getArea(), parcel.getType(), ParcelVolume.builder()
                .length(parcel.getLength())
                .width(parcel.getWidth())
                .height(parcel.getHeight())
                .build(), parcel.getWeight());
    }


    public PaymentModel generateQuoteFromRequestDto(GetQuoteRequestDto requestDto) {
        ParcelVolume parcelVolume = ParcelVolume.builder()
                .length(requestDto.getLength())
                .width(requestDto.getWidth())
                .height(requestDto.getHeight())
                .build();

        Area pickUpArea = areaService.loadByCode(requestDto.getPickUpArea());
        Area deliveryArea = areaService.loadByCode(requestDto.getDeliveryArea());
        if (pickUpArea == null || deliveryArea == null) {
            throw new IllegalArgumentException("Invalid area code");
        }

        return generateQuote(pickUpArea, deliveryArea, requestDto.getParcelType(), parcelVolume, requestDto.getWeight());
    }

    public Pricing getPricing() {
        return pricingService.loadCurrentPricing().orElse(new Pricing());
    }

    public PaymentModel generateQuote(Area pickUpArea, Area deliveryArea, ParcelType parcelType, ParcelVolume parcelVolume, double weight) {
        Pricing pricing = getPricing();

        double volume = parcelVolume.getLength() * parcelVolume.getWidth() * parcelVolume.getHeight();
        double density = weight / volume;
        double distance = calculateDistance(pickUpArea, deliveryArea, parcelType, parcelVolume);

        double quote = pricing.getBasePrice() + (density * pricing.getDensityFactor()) + (distance * pricing.getDistanceFactor());

        int insuranceCost = (int) (quote * pricing.getInsuranceCostFactor());
        int deliveryFees = (int) (quote * 0.2);
        int totalCost = insuranceCost + deliveryFees + (int) quote;

        return PaymentModel.builder()
                .totalCost(totalCost)
                .insuranceCost(insuranceCost)
                .deliveryFees(deliveryFees)
                .build();
    }

    private double calculateDistance(Area pickUpArea, Area deliveryArea, ParcelType parcelType, ParcelVolume parcelVolume) {
        double baseDistance = 12;


        double volume = parcelVolume.getLength() * parcelVolume.getWidth() * parcelVolume.getHeight();
        double volumeFactor = volume / 1000;

        double typeFactor = parcelType == ParcelType.CONTAINS_DANGEROUS ? 1.5 : 1.0; // Adjust this as needed

        return baseDistance * volumeFactor * typeFactor;
    }

}




