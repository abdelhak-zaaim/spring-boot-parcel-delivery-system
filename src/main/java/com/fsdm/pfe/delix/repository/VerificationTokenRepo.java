/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 18:46
 *  * @modified : 10/05/2024, 18:46
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}