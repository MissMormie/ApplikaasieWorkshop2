/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.account;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sonja
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {

  // ------------------ VARIABLES --------------------------------------------

  private final AccountRepository accountRepository;

  // ------------------ CONSTRUCTOR --------------------------------------------

  @Autowired 
  public AccountController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  // ------------------ MAPPED METHODS -----------------------------------------

  // TODO make list of accounts
  @RequestMapping(value = "/accounts", method = RequestMethod.GET)
  public String accountList(Model model) {
    model.addAttribute("accountList", accountRepository.getAllAccounts());
    return "account/accounts";
  }

  // TODO remove this dummy/test method when I no longer need it to understand 
  // how the rest works. Also shows home at the moment.
  @RequestMapping(value = "/", method = GET)
  public String home() {
    return "home";
  }

  // TODO show form to change username/password
  // TODO check you may see this info.
  @RequestMapping(value = "/edit", method = GET)
  public String showEditAccount(
          @RequestParam(value = "id", required = true) int accountId,
          Model model) {
    model.addAttribute(accountRepository.getAccountById(accountId));
    return "account/edit";
    // return account/edit rename home.html to edit
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public String saveEdit(@Valid Account account, Model model) {
    if (accountRepository.updatePassword(account)) {
      return "redirect:/account/saved";
    }
    model.addAttribute("error", "Could not save");
    return "account/edit";
  }
  

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(
          @RequestParam(value = "gebruikersnaam", required = true) String gebruikersnaam,
          @RequestParam(value = "wachtwoord", required = true) String wachtwoord,
          Model model) {
    
    Account account = accountRepository.getAccountByUsernamePassword(gebruikersnaam, wachtwoord);
    if (account == null) {
      return "redirect:/account/";
    }
    return "account/login";
  }

  @RequestMapping(value = "/saved")
  public String save() {
    return "account/saved";
  }

  @RequestMapping(value = "/delete")
  public String delete(
          @RequestParam(value = "id", required = true) int accountId,
          Model model) {
    // todo check if this user is allowed to delete. Aspect? Can this function only be called if logged in correctly?
    if (accountRepository.deleteAccountById(accountId) == false) {
      model.addAttribute("error", "could not delete");
    }
    return "account/deleted";
  }
}
