/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 18:46
 *  * @modified : 10/05/2024, 18:46
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createVerification(User user);

    boolean verifyEmail(String token);
}
