/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant;

import applikaasie.klant.adres.KlantHeeftAdres;
import applikaasie.klant.adres.AdresType;
import applikaasie.klant.adres.Adres;
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
public class StubKlantRepository implements KlantRepository {

  // -------------------------- CREATE ------------------------------

  @Override
  public Boolean createKlant(Klant klant) {
    return true;
  }
  
  // -------------------------- READ --------------------------------
 
  @Override
  public Klant getKlantById(long id) {
    return createKlant(1);
  }

  @Override
  public List<Klant> getAllKlanten() {
    return createKlantList(10);
  }
  
  @Override
  public List<Klant> getKlantenByVoornaam(String voornaam) {
    return createKlantList(15);
  }
  
  @Override   
  public List<Klant> getKlantenByAchternaam(String achternaam) {
       return createKlantList(0);
  }

  @Override
  public List<Klant> getKlantenByVoornaamEnAchternaam(String voornaam, String achternaam) {
    return createKlantList(5);
  }
  
  // -------------------------- UPDATE ------------------------------
  

  @Override
  public Boolean updateKlant(Klant klant) {
    return true;
  }
  
  // -------------------------- DELETE ------------------------------

  @Override
  public Boolean deleteKlantById(int klantId) {
    return true;
  }
  
  
  
  // TODO remove this function once I've implemented database connection.    
  private List<Klant> createKlantList(int count) {
    List<Klant> klantList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      klantList.add(createKlant(i));
    }
    return klantList;
  }      
  
  private Klant createKlant(int i) {
    Klant klant = new Klant(i, "Marco" + i, "Laan", "van der", "0612345678", "email@gmail.com", false);
    Set<KlantHeeftAdres> kha = createKlantHeeftAdressen(klant);
    klant.setAdressen(kha);
    return klant;
  }

  private Adres createAdres(int i) {
    return new Adres(i, "straat" + i, 12, "", "2403XW", "Alphen aan den Rijn", false);
  }
  
  private Set<KlantHeeftAdres> createKlantHeeftAdressen(Klant klant) {
    Set<KlantHeeftAdres> khaSet = new HashSet<>(3); 
    khaSet.add(new KlantHeeftAdres(1, klant, createAdres(1), AdresType.BEZORGADRES));
    khaSet.add(new KlantHeeftAdres(2, klant, createAdres(2), AdresType.FACTUURADRES));
    khaSet.add(new KlantHeeftAdres(3, klant, createAdres(3), AdresType.POSTADRES));
    return khaSet;
  }
}
