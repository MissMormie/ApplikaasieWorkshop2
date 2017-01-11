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
  /**
   * Checks for an active account with this username and password. 
   * Returns null if not found.
   * @param username
   * @param password
   * @return 
   */
  public Account getAccountByUsernamePassword(String username, String password);
  
  public Account getAccountById(long id);
  
  public List<Account> getAllAccounts();
  
  public Boolean usernameExists(String username);

  // -------------------------- UPDATE ------------------------------
  
  public Boolean updatePassword(Account account);

  // -------------------------- DELETE ------------------------------

  public Boolean deleteAccountById(int accountId);
  
}
