/*
 *
 *  * @project : DeliX
 *  * @created : 15/05/2024, 00:49
 *  * @modified : 15/05/2024, 00:49
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.util.helpers;


import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {
    /**
     * <p> this function is used to get the server URL
     * <p> it takes the request as a parameter
     * <p> it returns the server URL
     * @param request
     * @return
     */
    public static String getServerUrl(HttpServletRequest request) {
        String scheme = request.getScheme(); // http or https
        String serverName = request.getServerName(); // hostname
        int serverPort = request.getServerPort(); // port number
        String contextPath = request.getContextPath(); // includes application context path

        // Construct server URL
        String url = scheme + "://" + serverName;

        if ((serverPort != 80) && (serverPort != 443)) {
            url += ":" + serverPort;
        }

        url += contextPath;

        return url;
    }
}