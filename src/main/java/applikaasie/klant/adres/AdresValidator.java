/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Sonja
 */
@Component
public class AdresValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Adres.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Adres adres = (Adres) target;
    
    // straat
    if(adres.getStraat().length() > 45 || adres.getStraat().length() < 2)
      errors.rejectValue("straat", "", "Straatnaam te kort of the lang.");
    
    // huisnummer
    if(adres.getHuisnummer() < 1 || adres.getHuisnummer() > 99999)
      errors.rejectValue("huisnummer", "huisnummer.invalid", "Onjuist huisnummer");
    
    // postcode
    if(!isValidPostcode(adres.getPostcode()))
      errors.rejectValue("postcode", "Geen juiste postcode");
    
    // toevoeging
    if(adres.getToevoeging().length() > 30) 
      errors.rejectValue("toevoeging", "toevoeging.te.lang", "Toevoeging te lang");
    
    // woonplaats
    if(adres.getWoonplaats().length() > 30)
      errors.rejectValue("woonplaats", "woonplaats.te.lang", "woonplaats te lang");
  }

  // -------------------- private functions ------------------
  private boolean isValidPostcode(String postcode) {
      String regex ="[1-9][0-9]{3}\\s?\\w\\w";
      return postcode.matches(regex);
  }  
  
}
