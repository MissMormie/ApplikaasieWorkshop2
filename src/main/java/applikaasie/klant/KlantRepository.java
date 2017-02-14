/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface KlantRepository extends CrudRepository<Klant, Long> {
  /*
    In Crud Repository: 
    count(); long
    delete(id);
    delete(<T>);
    deleteAll();
    exists(id); boolean
    findAll(); List<T>
    findAll(Iterable<ID>); List<T>
    findOne(id); T
    save(Iterable<S> entities); <S extends T> Iterable<S>
    save(S entity); <S extends T> S 
  */
  
    // -------------------------- CREATE ------------------------------
  
//  public Boolean createKlant(Klant klant);

  // -------------------------- READ --------------------------------
  
  public List<Klant> findAllKlantByDeletedFalse();

  public List<Klant> findKlantByVoornaamAndAchternaamAndDeletedFalse(String voornaam, String achternaam);
  
  public List<Klant> findKlantByVoornaamAndDeletedFalse(String voornaam);

  public List<Klant> findKlantByAchternaamAndDeletedFalse(String achternaam);
  
  public Klant findKlantByIdKlantAndDeletedFalse(long idKlant);

  // -------------------------- UPDATE ------------------------------
  
//  public Boolean updateKlant(Klant klant);

  // -------------------------- DELETE ------------------------------

//  public Boolean deleteKlantById(int klantId);
  

}
