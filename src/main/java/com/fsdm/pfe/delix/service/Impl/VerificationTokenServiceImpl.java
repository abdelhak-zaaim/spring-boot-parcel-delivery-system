/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 18:49
 *  * @modified : 10/05/2024, 18:49
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.entity.VerificationToken;

import com.fsdm.pfe.delix.model.enums.UserStatus;
import com.fsdm.pfe.delix.repository.VerificationTokenRepo;
import com.fsdm.pfe.delix.service.VerificationTokenService;
import com.fsdm.pfe.delix.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepo verificationTokenRepo;
    private final EmailServiceImpl emailService;
    private final UserServiceImpl userService;

    public VerificationTokenServiceImpl(VerificationTokenRepo verificationTokenRepo, EmailServiceImpl emailService, UserServiceImpl userService) {
        this.verificationTokenRepo = verificationTokenRepo;
        this.emailService = emailService;
        this.userService = userService;
    }


    @Override
    public VerificationToken createVerification(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        return verificationTokenRepo.save(verificationToken);
    }

    @Override
    @Transactional
    public boolean verifyEmail(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        if (verificationToken != null) {
            User user = verificationToken.getUser();
            if (user.getStatus().equals(UserStatus.EMAIL_NOT_VERIFIED)) {
                user.setStatus(UserStatus.ACTIVE);
                user.setVerifiedAt(new Date());
                userService.saveUser(user);
                verificationTokenRepo.delete(verificationToken);
            }
            verificationTokenRepo.delete(verificationToken);
            return true;
        }
        return false;
    }

}
