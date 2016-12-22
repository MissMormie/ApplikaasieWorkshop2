/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TODO change this class from stub to get actual data
 * @author Sonja
 */
@Repository
public class StubAccountRepository implements AccountRepository{

    // -------------------------- CREATE ------------------------------
  
  @Override
  public Boolean createAccount(Account account) {
    return true;
  }

  // -------------------------- READ --------------------------------

  @Override
  public Account getAccountByUsernamePassword(String username, String password) {
    return new Account(1, "gebruikersnaam", "wachtwoord", "accountStatus", new Date(), null, false);
    //return null;
  }

  @Override
  public List<Account> getAllAccounts() {
    return createAccountList(15);
  }
  
  @Override
  public Account getAccountById(long id) {
    return new Account(1, "Sonja", "123", "admin", new Date(), null, false);
  }

  // -------------------------- UPDATE ------------------------------
  
  @Override
  public Boolean updatePassword(Account account) {
    return true;
  }

  // -------------------------- DELETE ------------------------------
  
  @Override
  public Boolean deleteAccountById(int accountId) {
    return true;
  }


  // -------------------------- HELPERS  ------------------------------
  
  // TODO remove this function once I've implemented database connection.    
  private List<Account> createAccountList(int count) {
    List<Account> accountsList = new ArrayList<Account>();
    for (int i = 0; i < count; i++) {
      accountsList.add(new Account(i, "Sonja" + i, "123" + i, "admin", new Date(), null, false));
    }
    return accountsList;
  }  
}
