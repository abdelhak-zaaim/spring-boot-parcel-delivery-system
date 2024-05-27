/*
 *
 *  * @project : DeliX
 *  * @created : 27/05/2024, 13:30
 *  * @modified : 27/05/2024, 13:30
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

public interface LoginAttemptService {
    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked();

    void resetAttempt(String key);

}
