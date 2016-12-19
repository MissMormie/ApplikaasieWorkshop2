/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant.adres;

import java.util.List;

/**
 *
 * @author Sonja
 */
public interface AdresRepository {
  
    // -------------------------- CREATE ------------------------------
  
  public Boolean createAdres(Adres adres);

  // -------------------------- READ --------------------------------
 
  public Adres getAdresById(long id);
  
  // -------------------------- UPDATE ------------------------------
  
  public Boolean updateAdres(Adres adres);

  // -------------------------- DELETE ------------------------------

  public Boolean deleteAdresById(int idAdres);
  
}
