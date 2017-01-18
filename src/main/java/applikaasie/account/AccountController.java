/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import applikaasie.klant.KlantRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  private final KlantRepository klantRepository;

  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public AccountController(AccountRepository accountRepository, KlantRepository klantRepository) {
    this.accountRepository = accountRepository;
    this.klantRepository = klantRepository;
  }

  // ----------------- MODEL ATTRIBUTES ----------------------------------------
  

  
  
  // ------------------ MAPPED METHODS -----------------------------------------
  // ------------------ Everyone -----------------------------------------
  @RequestMapping(value = "/wijzigwachtwoord", method = GET)
  public String wijzigWachtwoord(Model model, Authentication auth) {
    model.addAttribute(accountRepository.getAccountByGebruikersnaamAndDeletedFalse(auth.getName()));
    model.addAttribute("editType", "own");
    return "account/edit";
  }


  @RequestMapping(value = "/wijzigwachtwoord", method = RequestMethod.POST)
  public String saveWachtwoord(@Valid Account account, Errors errors, Model model, Authentication auth) {
    if(errors.hasErrors()) {
      model.addAttribute("error", "Could not save");
      return "account/edit"; 
    }

    Account a = accountRepository.getAccountByGebruikersnaamAndDeletedFalse(auth.getName());
    a.setWachtwoord(account.getWachtwoord());
    // TODO look at not valid account possibility
    accountRepository.save(a);
    return "redirect:/account/saved";
    
  }

  // ------------------ Admin -----------------------------------------
  // TODO make list of accounts
  @RequestMapping(value = "/accounts", method = RequestMethod.GET)
  public String accountList(Model model) {
    model.addAttribute("accountList", accountRepository.findAllAccountByDeletedFalse());
    return "account/accounts";
  }

  @RequestMapping(value = "/{klantId}/klantAccounts")
  public String klantAccountLijst(@PathVariable long klantId, Model model) {
    model.addAttribute("accountList", accountRepository.findAllAccountByKlantAndDeletedFalse(klantId));
    model.addAttribute("klant", klantRepository.findOne(klantId));
    return "account/accounts";
  }
  

  /**
   * adds klantId to model and returns view.
   *
   * @param klantId
   * @param model
   * @return
   */
  @RequestMapping(value = "/{klantId}/nieuwAccount")
  public String nieuwKlantAccount(@PathVariable int klantId, Model model, Account account) {
    model.addAttribute("klantId", klantId);
    model.addAttribute(account);
    return "account/new";
  }

  @RequestMapping(value = "/nieuwAccount")
  public String nieuwKlantAccount(Model model, Account account) {
    model.addAttribute(account);
    return "account/new";
  }


  @RequestMapping(value = "/nieuwAccount", method = POST)
  public String saveNieuwAccount(@Valid Account account, Errors errors, Model model) {
    // Was the form filled out correctly?
    if(errors.hasErrors())
      return "account/new";
    
    // check if gebruikersnaam isn't already in use. 
    if(!accountRepository.ExistsByGebruikersnaam(account.getGebruikersnaam())) {
      accountRepository.save(account);
      return "redirect:/account/saved";
    }
    
    // duplicate gebruikersnaam
    model.addAttribute("duplicateName", account.getGebruikersnaam());
    return "account/new";  
  }
    
  
  @RequestMapping(value ="/nieuwAccount/saved")
  public String nieuwAccountSaved() {
    return "account/newSaved";
  }


  @RequestMapping(value = "/edit", method = GET)
  public String showEditAccount(
          @RequestParam(value = "id", required = true) int accountId,
          Model model) {
    Account account = accountRepository.getAccountByIdAndDeletedFalse(accountId);
    if (account == null) {
      throw new AccountNotFoundException();
    }
    model.addAttribute(account);
    return "account/edit";
    // TODO? return account/edit rename home.html to edit
  }


  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public String saveEdit(@Valid Account account, Errors errors, Model model) {
    if(errors.hasErrors()) {
      model.addAttribute("error", "Could not save");
      return "account/edit"; 
    }

    Account updateAccount = accountRepository.getAccountByIdAndDeletedFalse(account.getId());
    if(updateAccount == null) {
      throw new AccountNotFoundException();
    }
    
    updateAccount.setWachtwoord(account.getWachtwoord());
    accountRepository.save(updateAccount);
    return "redirect:/account/saved";
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
    if(account == null) {
      throw new AccountNotFoundException();
    }
    account.setDeleted(true);
    
    // todo check if this user is allowed to delete. Aspect? Can this function only be called if logged in correctly?
    accountRepository.save(account);
    return "account/deleted";
  }
}
