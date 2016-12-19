/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant;

import java.util.List;

/**
 *
 * @author Sonja
 */
public interface KlantRepository {

    // -------------------------- CREATE ------------------------------
  
  public Boolean createKlant(Klant klant);

  // -------------------------- READ --------------------------------
 
  public Klant getKlantById(long id);
  
  public List<Klant> getAllKlanten();

  public List<Klant> getKlantenByVoornaamEnAchternaam(String voornaam, String achternaam);
  
  public List<Klant> getKlantenByVoornaam(String voornaam);

  public List<Klant> getKlantenByAchternaam(String achternaam);

  // -------------------------- UPDATE ------------------------------
  
  public Boolean updateKlant(Klant klant);

  // -------------------------- DELETE ------------------------------

  public Boolean deleteKlantById(int klantId);
  

}
