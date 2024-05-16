/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 16:27
 *  * @modified : 16/05/2024, 16:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.api.authentication;

public class AuthenticationRequestDto {
    private String email;
    private String password;

    public AuthenticationRequestDto() {
    }

    public AuthenticationRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
