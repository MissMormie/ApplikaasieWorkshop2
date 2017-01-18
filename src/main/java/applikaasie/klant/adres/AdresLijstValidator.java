/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Sonja
 */
@Component
public class AdresLijstValidator implements Validator {

  private final AdresValidator adresValidator;

  @Autowired
  public AdresLijstValidator(AdresValidator adresValidator) {
    if (adresValidator == null) {
      throw new IllegalArgumentException("The supplied [Validator] is "
              + "required and must not be null.");
    }
    if (!adresValidator.supports(Adres.class)) {
      throw new IllegalArgumentException("The supplied [Validator] must "
              + "support the validation of [Adres] instances.");
    }
    this.adresValidator = adresValidator;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return AdresLijst.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AdresLijst adresLijst = (AdresLijst) target;
    for(int i =0; i < adresLijst.getAdresLijst().size(); i++) {
      try {
        errors.pushNestedPath("adres" + i);
        ValidationUtils.invokeValidator(adresValidator, adresLijst.getAdresLijst().get(i), errors);
      } finally {
        errors.popNestedPath();
      }
    }

  }

}
