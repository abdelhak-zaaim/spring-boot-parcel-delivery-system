/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 20:27
 *  * @modified : 29/04/2024, 20:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}