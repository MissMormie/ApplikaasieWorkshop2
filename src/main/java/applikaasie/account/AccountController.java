/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
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
    model.addAttribute("accountList", accountRepository.findAllAccountByDeletedFalse());
//    model.addAttribute("accountList", accountRepository.getAllAccounts());
    return "account/accounts";
  }

  // TODO remove this dummy/test method when I no longer need it to understand 
  // how the rest works. Also shows home at the moment.
  @RequestMapping(value = "/", method = GET)
  public String home() {
    return "home";
  }

  /**
   * adds klantId to model and returns view.
   *
   * @param klantId
   * @param model
   * @return
   */
  @RequestMapping(value = "/{klantId}/nieuwAccount")
  public String nieuwKlantAccount(@PathVariable int klantId, Model model) {
    model.addAttribute("klantId", klantId);
    return "account/new";
  }

  @RequestMapping(value = "/nieuwAccount")
  public String nieuwKlantAccount(Model model) {
    return "account/new";
  }

  @RequestMapping(value = "/nieuwAccount", method = POST)
  public String saveNieuwAccount(@Valid Account account, Errors errors, Model model) {
    accountRepository.save(account);
    return "account/newsaved";
      
    // TODO figure out what happens if save goes wrong and move the code.. 
    /*
    model.addAttribute("klantId", account.getKlant());
    model.addAttribute("error", "error");
    return "account/new";
    */
  }
  
  
  @RequestMapping(value ="/nieuwAccount/saved")
  public String nieuwAccountSaved() {
    return "account/newSaved";
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
    // TODO figure out how to do password hash
/*    Account a = accountRepository.findOne(account.getId());
    a.setPlainTextWachtwoord(account.);
    // TODO look at not valid account possibility
    if (accountRepository.updatePassword(account)) {
      return "redirect:/account/saved";
    }
    model.addAttribute("error", "Could not save");
    return "account/edit"; */
      return "redirect:/account/saved";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(
          @RequestParam(value = "gebruikersnaam", required = true) String gebruikersnaam,
          @RequestParam(value = "wachtwoord", required = true) String wachtwoord,
          Model model) {

    Account account = accountRepository.getAccountByGebruikersnaamAndWachtwoord(gebruikersnaam, wachtwoord);
    if (account == null) {
      return "redirect:/";
    }
    model.addAttribute("account", account);
    return "account/login";
  }

  @RequestMapping(value = "/saved")
  public String save() {
    return "account/saved";
  }

  @RequestMapping(value = "/delete")
  public String delete(
          @RequestParam(value = "id", required = true) long accountId,
          Model model) {
    Account account = accountRepository.findOne(accountId);
    account.setDeleted(true);
    
    // todo check if this user is allowed to delete. Aspect? Can this function only be called if logged in correctly?
    accountRepository.save(account);
    return "account/deleted";
  }
}
