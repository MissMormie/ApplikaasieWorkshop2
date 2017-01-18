/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Sonja
 */
public interface AccountRepository extends CrudRepository<Account, Long>{

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
  
//  public Boolean createAccount(Account account);

  // -------------------------- READ --------------------------------

  public Account getAccountByGebruikersnaamAndWachtwoord(String gebruikersnaam, String wachtwoord);
  
  public Account getAccountByIdAndDeletedFalse(long id);
  
  public Account getAccountByGebruikersnaamAndDeletedFalse(String gebruikersnaam);
  
  public List<Account> findAllAccountByKlantAndDeletedFalse(Long id);
  
  public List<Account> findAllAccountByDeletedFalse();
//  public List<Account> getAllAccounts();
  
  @Query("SELECT CASE WHEN COUNT(gebruikersnaam) > 0 THEN 'true' ELSE 'false' END FROM Account a WHERE a.gebruikersnaam = ?1")
  public Boolean ExistsByGebruikersnaam(String gebruikersnaam);
  

  // -------------------------- UPDATE ------------------------------
  
//  public Boolean updatePassword(Account account);

  // -------------------------- DELETE ------------------------------

  //public Boolean deleteAccountById(int accountId);
  
}
