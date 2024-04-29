/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 20:26
 *  * @modified : 29/04/2024, 20:26
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}