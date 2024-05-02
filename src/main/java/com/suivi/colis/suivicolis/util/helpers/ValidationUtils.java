/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 17:10
 *  * @modified : 26/04/2024, 17:10
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.util.helpers;

import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isAcceptedPassword(String password) {
        return isValidPassword(password) && !isEasyPassword(password);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8 && !(password.length() > 50);
    }

    public static boolean isEasyPassword(String password) {
        // we use sseparate  validation for easy password ,because we may need to change it later

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return !pat.matcher(password).matches();
    }
}
