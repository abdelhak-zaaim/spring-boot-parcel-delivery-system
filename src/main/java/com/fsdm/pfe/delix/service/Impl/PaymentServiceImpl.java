/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:07
 *  * @modified : 20/05/2024, 21:07
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.Payment;
import com.fsdm.pfe.delix.repository.PaymentRepo;
import com.fsdm.pfe.delix.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    public static final float INSURANCE_COST_FACTOR = 0.1f;
    private final PaymentRepo paymentRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);

    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public Optional<Payment> loadPaymentById(Long id) {
        return paymentRepo.findById(id);
    }
}
