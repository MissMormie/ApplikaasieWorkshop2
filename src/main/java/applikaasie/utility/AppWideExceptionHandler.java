/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.utility;

import applikaasie.account.AccountNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 *
 * @author Sonja
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class AppWideExceptionHandler {
  
  @ExceptionHandler(IllegalArgumentException.class)
  public String illegalArgumentException() {
    return "error/genericError";
  }
    
  @ExceptionHandler(AccountNotFoundException.class)
  public String accountNotFoundException() {
    return "error/accountNotFound";
  }  
  
}
