/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 19:44
 *  * @modified : 18/05/2024, 19:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.PasswordResetToken;
import com.fsdm.pfe.delix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    void deleteAllByExpiryDateBefore(Date now);
   boolean existsByToken(String token);
    void deleteByToken(String token);

    void deleteAllByUser(User user);

    PasswordResetToken findByUser(User user);

    PasswordResetToken findByUser_Id(Long id);

}