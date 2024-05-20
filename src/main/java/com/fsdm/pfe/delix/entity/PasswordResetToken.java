/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 19:38
 *  * @modified : 18/05/2024, 19:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetToken {
   private static final int EXPIRATION = 60 * 24; // 24 sa3a

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String token;

   @OneToOne( fetch = FetchType.EAGER)
   @JoinColumn(nullable = false)
   private User user;

   private Date expiryDate;
   public PasswordResetToken(final String token, final User user) {
      super();
      this.token = token;
      this.user = user;
      this.expiryDate = calculateExpiryDate();
   }

   private Date calculateExpiryDate() {
      final Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(new Date().getTime());
      cal.add(Calendar.MINUTE, EXPIRATION);
      return new Date(cal.getTime().getTime());
   }
}
