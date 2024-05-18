/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 20:55
 *  * @modified : 18/05/2024, 20:55
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.PasswordResetToken;
import com.fsdm.pfe.delix.entity.User;


import java.util.Date;
import java.util.Optional;

public interface PasswordResetTokenService {
    void createPasswordResetTokenForUser(User user, String token);

    void deletePasswordResetToken(String token);

    void deleteAllExpiredSince(Date now);

    Optional<PasswordResetToken> findByToken(String token);

}
