/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 12:16
 *  * @modified : 13/05/2024, 12:16
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.LoginLog;
import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.repository.LoginLogRepo;
import com.fsdm.pfe.delix.service.LoginLogService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogRepo loginLogRepo;

    public LoginLogServiceImpl(LoginLogRepo loginLogRepo) {
        this.loginLogRepo = loginLogRepo;
    }

    public void saveLoginLog(User user, String userAgent, String ipAddress, boolean loginStatus, String loginMethod) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setLoginDate(new Date());
        loginLog.setUserAgent(userAgent);
        loginLog.setIpAddress(ipAddress);
        loginLog.setLoginStatus(loginStatus);
        loginLog.setLoginMethod(loginMethod);


        loginLogRepo.save(loginLog);
    }
}
