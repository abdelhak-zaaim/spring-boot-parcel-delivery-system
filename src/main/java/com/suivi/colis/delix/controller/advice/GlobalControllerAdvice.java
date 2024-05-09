
/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 20:02
 *  * @modified : 08/05/2024, 20:02
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.suivi.colis.delix.controller.advice;

//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import com.suivi.colis.delix.entity.User;
//
//@ControllerAdvice
//public class GlobalControllerAdvice {
//
//    @ModelAttribute
//    public void addAttributes(Model model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication()==null?null:SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            User user = (User) principal;
//            model.addAttribute("currentUser", user.toUserResponseDto());
//
//        }
//    }
//}