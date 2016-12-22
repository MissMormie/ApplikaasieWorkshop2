/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.bestelling;

import java.util.List;

/**
 *
 * @author Sonja
 */
public interface BestellingRepository {
      
  // -------------------------- CREATE & Update ---------------------
    
  Boolean updateOrSaveBestelling(Bestelling bestelling);

  // -------------------------- READ --------------------------------

  Bestelling getBestellingById(int id);
  
  List<Bestelling> getAllBestellingen();
  
  List<Bestelling> getAllBestellingenByKlantId(int klantId);
  

  // -------------------------- DELETE ------------------------------
  
  Boolean deleteBestelling(Bestelling bestelling);
  
  Boolean deleteBestellingById(int id);
  
}
