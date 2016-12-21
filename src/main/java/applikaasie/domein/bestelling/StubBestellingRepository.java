/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.bestelling;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sonja
 */
@Repository
public class StubBestellingRepository implements BestellingRepository{

  // -------------------------- CREATE & Update ---------------------
  
  @Override
  public Boolean updateOrSaveBestelling(Bestelling bestelling) {
    return true;
  }

  // -------------------------- READ --------------------------------
  
  @Override
  public Bestelling getBestellingById(int id) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Bestelling> getAllBestellingen() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Bestelling> getAllBestellingenByKlantId(int klantId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  // -------------------------- DELETE ------------------------------ 
  
  @Override
  public Boolean deleteBestelling(Bestelling bestelling) {
    return true;
  }

  @Override
  public Boolean deleteBestellingById(int id) {
    return true;
  }
 
  // -------------------------- HELPER ------------------------------ 
  
  
  public Bestelling createBestelling(int i)  {
    Bestelling bestelling = new Bestelling();
    return null;
  }
}
