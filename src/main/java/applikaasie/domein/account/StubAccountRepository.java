/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.account;

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

  @Override
  public Account getAccountByUsernamePassword(String username, String password) {
    //return new Account(1, "gebruikersnaam", "wachtwoord", "accountStatus", new Date(), null, false);
    return null;
  }

  @Override
  public List<Account> getAllAccounts() {
    return createAccountList(15);
  }
  


  
  @Override
  public Account getAccountById(long id) {
    return new Account(1, "Sonja", "123", "admin", new Date(), null, false);
  }

  @Override
  public Boolean updatePassword(Account account) {
    return true;
  }

  @Override
  public Boolean deleteAccountById(int accountId) {
    return true;
  }

  @Override
  public Boolean createAccount(Account account) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  // TODO remove this function once I've implemented database connection.    
  private List<Account> createAccountList(int count) {
    List<Account> accountsList = new ArrayList<Account>();
    for (int i = 0; i < count; i++) {
      accountsList.add(new Account(i, "gebruikersnaam" + i, "wachtwoord" + i, "accountStatus", new Date(), null, false));
    }
    return accountsList;
  }  
}
