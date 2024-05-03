/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 15:33
 *  * @modified : 26/04/2024, 15:33
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix;

import com.suivi.colis.delix.entity.Admin;
import com.suivi.colis.delix.repository.AdminEmployeeRepo;
import com.suivi.colis.delix.service.Impl.AdminServiceImpl;
import jakarta.persistence.Convert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
@Convert
class AdminServiceTest {


    @GetMapping("/test/admin")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("Admin");
    }

    @Mock
    private AdminEmployeeRepo adminRepo;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAdminReturnsAdminWhenExists() {
        Admin admin = new Admin();
        when(adminRepo.findById(1L)).thenReturn(Optional.of(admin));

        Admin result = adminService.loadAdminById(1L);

        assertEquals(admin, result);
        verify(adminRepo, times(1)).findById(1L);
    }

    @Test
    void getAdminReturnsNullWhenDoesNotExist() {
        when(adminRepo.findById(1L)).thenReturn(Optional.empty());

        Admin result = adminService.loadAdminById(1L);

        assertNull(result);
        verify(adminRepo, times(1)).findById(1L);
    }

    @Test
    void saveAdminSavesAndReturnsAdmin() {
        Admin admin = new Admin();
        when(adminRepo.save(admin)).thenReturn(admin);

        Admin result = adminService.saveAdmin(admin);

        assertEquals(admin, result);
        verify(adminRepo, times(1)).save(admin);
    }
}