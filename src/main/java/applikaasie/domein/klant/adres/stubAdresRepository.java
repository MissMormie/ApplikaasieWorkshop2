/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant.adres;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Sonja
 */
@Repository
public class stubAdresRepository implements AdresRepository {

  @Override
  public Boolean createAdres(Adres adres) {
    return true;
  }

  @Override
  public Adres getAdresById(long id) {
    return createAdres(2);
  }

  @Override
  public Boolean updateAdres(Adres adres) {
    return true;
  }

  @Override
  public Boolean deleteAdresById(int idAdres) {
    return true;
  }
  
  
  private Adres createAdres(int i) {
    return new Adres(i, "straat" + i, 12, "", "2403XW", "Alphen aan den Rijn", false);
  }
  
}
