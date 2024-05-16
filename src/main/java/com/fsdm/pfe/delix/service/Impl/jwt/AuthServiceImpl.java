
/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 16:22
 *  * @modified : 16/05/2024, 16:22
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.jwt;


import com.fsdm.pfe.delix.dto.api.authentication.AuthenticationRequestDto;
import com.fsdm.pfe.delix.dto.api.authentication.AuthenticationResponseDto;
import com.fsdm.pfe.delix.repository.DeliveryManRepo;
import com.fsdm.pfe.delix.service.jwt.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final DeliveryManRepo deliveryManRepo;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final DeliveryManRepo userRepository;


    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var deliveryMan = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(deliveryMan);
        return AuthenticationResponseDto.builder().accessToken(jwtToken).build();

    }
}
