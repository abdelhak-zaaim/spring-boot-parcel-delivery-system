/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:06
 *  * @modified : 20/05/2024, 21:06
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.Payment;

import java.util.Optional;

public interface PaymentService {

    Payment savePayment(Payment payment);

    void deletePayment(Long id);

    Payment updatePayment(Payment payment);

    Optional<Payment> loadPaymentById(Long id);

}
