/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

/**
 *
 * @author Sonja
 */
import applikaasie.klant.KlantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AccountRestController {


  // ------------------ VARIABLES --------------------------------------------
  private final AccountRepository accountRepository;
  private final KlantRepository klantRepository;

  
  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public AccountRestController(AccountRepository accountRepository, KlantRepository klantRepository) {
    this.accountRepository = accountRepository;
    this.klantRepository = klantRepository;
  }
  
  @RequestMapping("/rest/account/accounts")
  public List<Account> getAccounts() {
    return accountRepository.findAllAccountByDeletedFalse();
  }
  
  @RequestMapping(value="/rest/account/{accountId}", method = GET)
  public Account getAccount(@PathVariable long accountId) {
    Account account = accountRepository.getAccountByIdAndDeletedFalse(accountId);
    return account;
  }
  
  @RequestMapping(value="/rest/account/update/{accountId}")
  public Account updateAccount(@RequestBody Account account) {
    return accountRepository.save(account);
  }
  
  @RequestMapping(value="/rest/account/create")
  public Account createAccount(@RequestBody Account account) {
    return accountRepository.save(account);
  }
  
  @RequestMapping(value="/rest/account/empty")
  public Account getEmptyAccount() {
    return new Account();
  }

}
