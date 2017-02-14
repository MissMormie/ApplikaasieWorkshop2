/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant;

import applikaasie.account.Account;
import applikaasie.account.AccountRepository;
import applikaasie.klant.adres.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sonja
 */
@Controller
@RequestMapping(value="/klant")
public class KlantController {
  // ------------------ VARIABLES --------------------------------------------
  
  KlantRepository klantRepository;
  AdresRepository adresRepository;
  AdresTypeObjectRepository adresTypeObjectRepository;
  AccountRepository accountRepository;
  
  
  // ------------------ CONSTRUCTOR --------------------------------------------
  
  @Autowired  
  public KlantController(KlantRepository klantRepository, 
                          AdresRepository adresRepository, 
                          AdresTypeObjectRepository adresTypeObjectRepository,
                          AccountRepository accountRepository) {
    this.klantRepository = klantRepository;
    this.adresRepository = adresRepository;
    this.adresTypeObjectRepository = adresTypeObjectRepository;
    this.accountRepository = accountRepository;
  }
  
  // ----------------- MODEL ATTRIBUTES ----------------------------------------
  
   
  
  // ------------------ MAPPED METHODS -----------------------------------------
  // ------------------ Klanten -----------------------------------------

  @RequestMapping(value="/edit")
  public String editKlantZelf(Model model, Authentication authentication) {   
    model.addAttribute("klant", getKlantByAccount(authentication));
    return "klant/editKlant";
  }  
  
  @RequestMapping(value="/mijngegevens")
  public String showKlantZelf(Model model, Authentication authentication) {
    model.addAttribute("klant", getKlantByAccount(authentication));
    return "klant/klant";
  }
  
  @RequestMapping(value="/{adresId}/editAdres")
  public String editOwnAdres(@PathVariable long adresId, Model model, Authentication authentication) {

    Klant klant = getKlantByAccount(authentication);
    Adres adres = klant.heeftDitAdres(adresId);
    
    // TODO once Klant throws Exception instead of null remove this check. 
    if(adres != null) {
      model.addAttribute("adres", adres);
      return "klant/editAdres";
    }
    return "home";
  }
    
  @RequestMapping(value="/{adresId}/editAdres", method = RequestMethod.POST)
  public String editOwnAdres(@PathVariable int adresId,
                          @Valid Adres adres, 
                          Errors errors, 
                          Model model,
                          Authentication authentication) {
    // Check if this adres belongs to this account
    Klant klant = getKlantByAccount(authentication);
    if(klant.heeftDitAdres(adres.getIdAdres()) == null) 
      return "home";
    
    if(errors.hasErrors())
      return "klant/editAdres";
    
    
    adresRepository.save(adres);
    
    // TODO find klant id by adres. 
    return "redirect:/klant/mijngegevens";
  }  
  
  
  // ------------------ Admin -----------------------------------------

  /**
   * AllKlanten gets a list of all customers and adds it to the model.
   * @param model
   * @return 
   */
  @RequestMapping(value="/klanten")
  public String allKlanten(Model model) {
    model.addAttribute("klantLijst", klantRepository.findAllKlantByDeletedFalse());
    return "klant/klanten";
  }

  
  /**
   * showKlant gets the klant information, passes it to the model and returns the edit view.
   * @param id
   * @param model
   * @return 
   */
  @RequestMapping(value="/{id}/editKlant")
  public String editKlant(@PathVariable long id, Model model) {
    model.addAttribute("klant", klantRepository.findOne(id));
    return "klant/editKlant";
  }
  
  /**
   * editKlant receives klantInfo through Post method and saves it.
   */
  @RequestMapping(value="/{id}/editKlant", method = RequestMethod.POST)
  public String editKlant(@Valid Klant klant, Errors errors, Model model) {
    if(errors.hasErrors())
      return "klant/editKlant";
    klantRepository.save(klant);
    return "redirect:/klant/"+ klant.getIdKlant() +"/klant";
  }

  
    /**
   * showKlant gets the klant information, passes it to the model and returns the edit view.
   * @param id
   * @param model
   * @return 
   */
  @RequestMapping(value="/{klantId}/{adresId}/editAdres")
  public String editAdres(@PathVariable int klantId, 
                          @PathVariable long adresId, Model model) {
    model.addAttribute("adres", adresRepository.findAdresByIdAdresAndDeletedFalse(adresId));
    model.addAttribute("klant", klantId);
    return "klant/editAdres";
  }
  
  /**
   * editKlant receives klantInfo through Post method and saves it.
   */
  @RequestMapping(value="/{klantId}/{adresId}/editAdres", method = RequestMethod.POST)
  public String editAdres(@PathVariable int klantId, 
                          @PathVariable int adresId,
                          @Valid Adres adres, 
                          Errors errors, 
                          Model model) {
    
    if(errors.hasErrors())
      return "klant/" + adres.getIdAdres() + "/editAdres";
    adresRepository.save(adres);
    
    // TODO find klant id by adres. 
    return "redirect:/klant/" + klantId + "/klant";
  }

  
  
  /**
   * ZoekInKlanten gets parameters from url and finds klanten that fit 
   * the description and adds them to the model.
   * @param voornaam
   * @param achternaam
   * @param model
   * @return 
   */
  @RequestMapping(value="/klanten", method = RequestMethod.GET, params={"voornaam", "achternaam"})
  public String zoekInKlanten(
          @RequestParam(value="voornaam", required=false) String voornaam,
          @RequestParam(value="achternaam", required=false) String achternaam,
          Model model){

    // Enkel achternaam
    if(voornaam.equals("") && !achternaam.equals("")) 
      model.addAttribute("klantLijst", klantRepository.findKlantByAchternaamAndDeletedFalse(achternaam));
    
    // Enkel voornaam
    else if(!voornaam.equals("") && achternaam.equals("")) 
      model.addAttribute("klantLijst", klantRepository.findKlantByVoornaamAndDeletedFalse(voornaam));
    
    // Zowel voor als achternaam
    else if(!voornaam.equals("") && !achternaam.equals("")) 
      model.addAttribute("klantLijst", klantRepository.findKlantByVoornaamAndAchternaamAndDeletedFalse(voornaam, achternaam));
    
    // Geen achter en voornaam
    else 
      model.addAttribute("klantLijst", klantRepository.findAllKlantByDeletedFalse());
    
    return "klant/klanten";
  }
  

  /**
   * DeleteKlant ask repository to delete the klant
   * @param id
   */
  // TODO add expection in case delete failed, klant does not exists, other corner cases. 
  @RequestMapping(value="/{id}/delete")
  public String deleteKlant(@PathVariable long id) {
    // delete klant accounts
    deleteAllKlantAccounts(id);

    // delete klant
    Klant klant = klantRepository.findOne(id);
    klant.delete();
    klantRepository.save(klant);
    return "klant/deleted";
  }
  
  
  @RequestMapping(value="/{id}/klant")
  public String showKlant(@PathVariable long id, Model model) {
    model.addAttribute("klant", klantRepository.findOne(id));
    return "klant/klant";
  }
  
  
  @RequestMapping(value="/nieuw")
  public String nieuweKlant(AdresLijst adresLijst, Adres adres, Klant klant, Model model) {
    // TODO fix the dirty workaround to get this work. Should not need Adres, lijst or klant here.. 
    List<Adres> adressenLijst = new ArrayList(3);
    adressenLijst.add(adres);
    adressenLijst.add(adres);
    adressenLijst.add(adres);
    
    adresLijst.setAdresLijst(adressenLijst);
    model.addAttribute(adresLijst);
    model.addAttribute(klant);
    return "klant/nieuw";
  }
  
  
  @RequestMapping(value="/nieuw", method=POST)
  public String saveNieuweKlant(@Valid AdresLijst adresLijst, Errors adresErrors, @Valid Klant klant, Errors errors, Model model) {
    // TODO make a smarter way to figure out which adres is which. 
    // TODO handle errors
    if(errors.hasErrors() || adresErrors.hasErrors())
      return "klant/nieuw";

    // adres0 = bezorgadres
    // adres1 = factuuradres
    // adres2 = postadres
    // TODO add validation for Adres & Klant. 
    klant.setAdres(adresLijst.getAdresLijst().get(0), adresTypeObjectRepository.findAdresTypeObjectByAdresType(AdresType.BEZORGADRES));
    klant.setAdres(adresLijst.getAdresLijst().get(1), adresTypeObjectRepository.findAdresTypeObjectByAdresType(AdresType.FACTUURADRES));
    klant.setAdres(adresLijst.getAdresLijst().get(2), adresTypeObjectRepository.findAdresTypeObjectByAdresType(AdresType.POSTADRES));
    
    klantRepository.save(klant);
    
    return "redirect:/klant/" + klant.getIdKlant() +"/klant";
  }


  // ------------------- JSON FUNCTIONS --------------------------------
  /*
  @RequestMapping(value="/postcode", method = RequestMethod.GET, params={"postcode", "huisnummer"}, produces = "application/json")
  public String getAdresByPostcode(
          @RequestParam(value="postcode", required=true) String postcode,
          @RequestParam(value="huisnummer", required=true) int huisnummer,
          Model model){
    
    return "{\"status\":\"ok\",\"street\":\"Sterkenburg\",\"city\":\"Alphen aan den Rijn\",\"municipality\":\"Alphen aan den Rijn\",\"province\":\"Zuid-Holland\",\"postcode\":\"2402RC\",\"pnum\":\"2402\",\"pchar\":\"RC\",\"rd_x\":\"106377.75987500000000000000\",\"rd_y\":\"462560.45733333333333333333\",\"lat\":\"52.1490854191017\",\"lon\":\"4.6767987818837\"}";
  }
  */
  // ------------------- HELPER FUNCTIONS --------------------------------
  
  private Klant getKlantByAccount(Authentication authentication) {
    String gebruikersnaam = authentication.getName();
    Account account = accountRepository.getAccountByGebruikersnaamAndDeletedFalse(gebruikersnaam);
    Klant klant = klantRepository.findOne(account.getKlant());
    
    return klant;
  }
  
  private void deleteAllKlantAccounts(long klantId) {
    List<Account> accounts = accountRepository.findAllAccountByKlantAndDeletedFalse(klantId);
    for(Account account: accounts) {
      account.setDeleted(true);
    }
    accountRepository.save(accounts);

  }
}
