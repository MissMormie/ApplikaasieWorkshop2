/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.home;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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
  
  @RequestMapping(value="/rest")
  public String restHomePage() {
    return "rest/rest";
  }
  
}
