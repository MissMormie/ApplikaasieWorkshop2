/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import java.util.List;

/**
 *
 * @author Sonja
 */
public interface AccountRepository {

  // -------------------------- CREATE ------------------------------
  
  public Boolean createAccount(Account account);

  // -------------------------- READ --------------------------------

  public Account getAccountByUsernamePassword(String username, String password);
  
  public Account getAccountById(long id);
  
  public List<Account> getAllAccounts();

  // -------------------------- UPDATE ------------------------------
  
  public Boolean updatePassword(Account account);

  // -------------------------- DELETE ------------------------------

  public Boolean deleteAccountById(int accountId);
  
}
