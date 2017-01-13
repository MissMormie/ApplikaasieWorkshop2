/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface AdresRepository extends CrudRepository<Adres, Long> {
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

  // -------------------------- READ --------------------------------
 
  public Adres findAdresByIdAdresAndDeletedFalse(long idAdres);
  
}
