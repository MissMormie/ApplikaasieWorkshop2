/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonja
 */
public class AdresLijst {
  
  @Valid
  @NotNull        
  List<Adres> adresLijst;

  public List<Adres> getAdresLijst() {
    return adresLijst;
  }

  public void setAdresLijst(List<Adres> adressen) {
    this.adresLijst = adressen;
  }

}
