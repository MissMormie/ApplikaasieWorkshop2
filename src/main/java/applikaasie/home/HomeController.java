/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.home;

import applikaasie.account.Account;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sonja
 */
@Controller
public class HomeController {
  
  // ----------------- MODEL ATTRIBUTES ----------------------------------------
 
  // ------------------ MAPPED METHODS -----------------------------------------
  @RequestMapping(value="/")
  public String homePage(Authentication auth) {
    
    return "home";
  }
  
}
