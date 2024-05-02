/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 15:33
 *  * @modified : 26/04/2024, 15:33
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis;

import com.suivi.colis.suivicolis.entity.AdminEmployee;
import com.suivi.colis.suivicolis.repository.AdminEmployeeRepo;
import com.suivi.colis.suivicolis.service.Impl.AdminEmployeeServiceImpl;
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
        return ResponseEntity.ok("AdminEmployee");
    }

    @Mock
    private AdminEmployeeRepo adminRepo;

    @InjectMocks
    private AdminEmployeeServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAdminReturnsAdminWhenExists() {
        AdminEmployee admin = new AdminEmployee();
        when(adminRepo.findById(1L)).thenReturn(Optional.of(admin));

        AdminEmployee result = adminService.loadAdminById(1L);

        assertEquals(admin, result);
        verify(adminRepo, times(1)).findById(1L);
    }

    @Test
    void getAdminReturnsNullWhenDoesNotExist() {
        when(adminRepo.findById(1L)).thenReturn(Optional.empty());

        AdminEmployee result = adminService.loadAdminById(1L);

        assertNull(result);
        verify(adminRepo, times(1)).findById(1L);
    }

    @Test
    void saveAdminSavesAndReturnsAdmin() {
        AdminEmployee admin = new AdminEmployee();
        when(adminRepo.save(admin)).thenReturn(admin);

        AdminEmployee result = adminService.saveAdmin(admin);

        assertEquals(admin, result);
        verify(adminRepo, times(1)).save(admin);
    }
}