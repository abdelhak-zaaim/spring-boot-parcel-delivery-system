/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 12:15
 *  * @modified : 13/05/2024, 12:15
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.User;

public interface LoginLogService {
    void saveLoginLog(User user, String userAgent, String ipAddress, boolean loginStatus, String loginMethod);
}
