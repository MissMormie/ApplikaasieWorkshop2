/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.bestelling;

import applikaasie.artikel.Artikel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    return createBestelling(2);
  }

  @Override
  public List<Bestelling> getAllBestellingen() {
    return createBestellingLijst(12);
  }

  @Override
  public List<Bestelling> getAllBestellingenByKlantId(int klantId) {
    return createBestellingLijst(4);
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
  
  public List<Bestelling> createBestellingLijst(int count) {
    List<Bestelling> bestellingLijst = new ArrayList<>();
    for(int i = 0; i < count; i++) {
      bestellingLijst.add(createBestelling(i));
    }
    return bestellingLijst;
     
  }
  
  
  public Bestelling createBestelling(int num)  {
    Bestelling bestelling = new Bestelling();
    bestelling.setId(num);
    bestelling.setBestelArtikel(createBestelArtikelSet(5));
    return bestelling;
  }
  
  public Set createBestelArtikelSet(int count) {
    Set orderList = new HashSet();
    for(int i = 0; i < 5; i++) {
      orderList.add(new BestelArtikel(i, i+3, new Artikel(i, "artikel" + i, 12.50, 3, false)));
    }
    return orderList;
  }
}
