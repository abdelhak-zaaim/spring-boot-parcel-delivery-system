/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 20:56
 *  * @modified : 18/05/2024, 20:56
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.PasswordResetToken;
import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.repository.PasswordResetTokenRepo;
import com.fsdm.pfe.delix.service.PasswordResetTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
   private final PasswordResetTokenRepo passwordResetTokenRepo;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepo passwordResetTokenRepo) {
        this.passwordResetTokenRepo = passwordResetTokenRepo;
    }

    @Override
   public void createPasswordResetTokenForUser(User user, String token) {
       passwordResetTokenRepo.save(new PasswordResetToken(token, user));
   }

   @Override
   public void deletePasswordResetToken(String token) {
      passwordResetTokenRepo.deleteByToken(token);
   }

   @Override
   public void deleteAllExpiredSince(Date now) {
      passwordResetTokenRepo.deleteAllByExpiryDateBefore(now);
   }

   @Override
   public Optional<PasswordResetToken> findByToken(String token) {
      return passwordResetTokenRepo.findByToken(token);
   }

   boolean isTokenExpired(PasswordResetToken passwordResetToken) {
      return passwordResetToken.getExpiryDate().before(new Date());
   }
   boolean isTokenValid(PasswordResetToken passwordResetToken) {
      return passwordResetTokenRepo.existsByToken(passwordResetToken.getToken());
   }
   void validateToken(PasswordResetToken passwordResetToken) {
      if (!isTokenValid(passwordResetToken)) {
         throw new IllegalArgumentException("Invalid token");
      }
      if (isTokenExpired(passwordResetToken)) {
         throw new IllegalArgumentException("Token expired");
      }
   }


  public  boolean isExpiredByToken(String token) {
      Optional<PasswordResetToken> passwordResetToken = findByToken(token);
       return passwordResetToken.filter(this::isTokenExpired).isPresent();
   }
}
