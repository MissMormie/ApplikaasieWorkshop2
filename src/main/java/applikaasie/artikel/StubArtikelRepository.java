/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.artikel;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TODO make it work
 * @author Sonja
 */
@Repository
public class StubArtikelRepository implements ArtikelRepository {

  @Override
  public Boolean createArtikel(Artikel artikel) {
    return true;
  }

  public Boolean updateOrSaveArtikel(Artikel artikel) {
    return true;
  }
  
  
  @Override
  public Artikel getArtikelById(int id) {
    return new Artikel(1, "oude kaas", 12.34, 12, false);
  }

  @Override
  public List<Artikel> getAllArtikelen() {
    return createArtikelList(7);
  }

  @Override
  public Boolean updateArtikel(Artikel artikel) {
    return true;
  }

  @Override
  public Boolean deleteArtikel(Artikel artikel) {
    return true;
  }
  
  // TODO remove this function once I've implemented database connection.    
  private List<Artikel> createArtikelList(int count) {
    List<Artikel> accountsList = new ArrayList<Artikel>();
    for (int i = 0; i < count; i++) {
      accountsList.add(new Artikel(i, "oude kaas" + i, 12.34, 12, false));
    }
    return accountsList;
  }    

  @Override
  public Boolean deleteArtikelById(int id) {
    return true;
  }
  
}
