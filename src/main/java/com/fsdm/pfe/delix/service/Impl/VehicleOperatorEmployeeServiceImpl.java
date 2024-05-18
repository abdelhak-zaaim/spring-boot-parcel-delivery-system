/*
 *
 *  * @project : DeliX
 *  * @created : 17/05/2024, 16:53
 *  * @modified : 17/05/2024, 16:53
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.VehicleOperatorEmployee;
import com.fsdm.pfe.delix.repository.VehicleOperatorEmployeeRepo;
import com.fsdm.pfe.delix.service.VehicleOperatorEmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class VehicleOperatorEmployeeServiceImpl implements VehicleOperatorEmployeeService , UserDetailsService
{
   private final VehicleOperatorEmployeeRepo vehicleOperatorEmployeeRepo;

    public VehicleOperatorEmployeeServiceImpl(VehicleOperatorEmployeeRepo vehicleOperatorEmployeeRepo) {
        this.vehicleOperatorEmployeeRepo = vehicleOperatorEmployeeRepo;
    }

   @Override
   public void updateVehicleOperatorEmployee(VehicleOperatorEmployee vehicleOperatorEmployee) {
      vehicleOperatorEmployeeRepo.save(vehicleOperatorEmployee);
   }

   @Override
   public void deleteVehicleOperatorEmployee(VehicleOperatorEmployee vehicleOperatorEmployee) {
      vehicleOperatorEmployeeRepo.delete(vehicleOperatorEmployee);
   }

   @Override
   public Optional<VehicleOperatorEmployee> loadByEmail(String email) {
      return vehicleOperatorEmployeeRepo.findByEmail(email);
   }

   @Override
   public Optional<VehicleOperatorEmployee> loadByPhoneNumber(String phoneNumber) {
      return vehicleOperatorEmployeeRepo.findByPhoneNumber(phoneNumber);
   }

   @Override
   public Optional<VehicleOperatorEmployee> loadByCin(String cin) {
      return vehicleOperatorEmployeeRepo.findByCin(cin);
   }



   @Override
   public Optional<VehicleOperatorEmployee> loadByVehicleType(String vehicleType) {
      return vehicleOperatorEmployeeRepo.findByVehicleType(vehicleType);
   }

   @Override
   public Optional<VehicleOperatorEmployee> loadByRole(String role) {
      return vehicleOperatorEmployeeRepo.findByRole(role);
   }

   @Override
   public Optional<VehicleOperatorEmployee> loadById(Long id) {
      return vehicleOperatorEmployeeRepo.findById(id);
   }


   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return vehicleOperatorEmployeeRepo.findByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
   }
}
