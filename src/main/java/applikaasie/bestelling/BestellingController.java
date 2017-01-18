/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.bestelling;

import applikaasie.account.Account;
import applikaasie.account.AccountRepository;
import applikaasie.artikel.ArtikelRepository;
import applikaasie.klant.Klant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Sonja
 */
@Controller
@RequestMapping("bestelling/")
public class BestellingController {

  private final ArtikelRepository artikelRepository;
  private final BestellingRepository bestellingRepository;
  private final AccountRepository accountRepository;

  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public BestellingController(ArtikelRepository artikelRepository, BestellingRepository bestellingRepository, AccountRepository accountRepository) {
    this.artikelRepository = artikelRepository;
    this.bestellingRepository = bestellingRepository;
    this.accountRepository = accountRepository;
  }

  // ------------------ MAPPED METHODS -----------------------------------------  
  
  // ------------------ Klanten -----------------------------------------  
  @RequestMapping(value = "mijnBestellingen")
  public String mijnBestellingen(Model model, Authentication auth) {
    long klantId = getKlantIdByAccount(auth);
    List<Bestelling> bestellingenLijst = bestellingRepository.findAllBestellingenByKlantIdAndDeletedFalse(klantId);
    model.addAttribute("bestellingenLijst", bestellingenLijst);
    return "/bestelling/bestellingen";
  }

  @RequestMapping(value = "nieuweBestelling", method = GET)
  public String nieuweBestelling(Model model) {
    List artikelen = artikelRepository.findAllArtikelByDeletedFalse();
    model.addAttribute("artikelLijst", artikelen);
    return "/bestelling/nieuw";
  }

  // TODO make sure the article id is saved in db. 
  @RequestMapping(value = "nieuweBestelling", method = POST)
  public String nieuweBestellingOpslaan(BestelArtikelLijst bestelArtikelLijst,
          Errors errors,
          Bestelling bestelling,
          Model model,
          Authentication auth) {
    if (errors.hasErrors()) {
      return "/bestelling/nieuw";
    }

    long klantId = getKlantIdByAccount(auth);
    bestelling.setKlantId(klantId);

    bestelling.updateBestelArtikelenList(bestelArtikelLijst);
    bestellingRepository.save(bestelling);
    return "redirect:/bestelling/saved";
  }

  @RequestMapping(value = "saved")
  public String bestellingSaved() {
    return "/bestelling/saved";
  }
  
  // ------------------ Admin -------------------------------------------  
  @RequestMapping(value = "admin/{id}/delete")
  public String adminDeleteBestelling(@PathVariable long id) {
    Bestelling bestelling = bestellingRepository.findBestellingByIdAndDeletedFalse(id);
    bestelling.delete();
    bestellingRepository.save(bestelling);
    return "/bestelling/deleted";
  }

  @RequestMapping(value = "admin/{id}/edit")
  public String adminEditBestelling(@PathVariable long id, Model model) {
    Bestelling bestelling = bestellingRepository.findBestellingByIdAndDeletedFalse(id);

    // TODO move this to bestelArtikel class?
    Map<Long, Integer> bestelMap = new HashMap<>();
    for (BestelArtikel bestelArtikel : bestelling.getBestelArtikelSet()) {
      bestelMap.put(bestelArtikel.getArtikel().getId(), bestelArtikel.getAantal());
    }
    model.addAttribute("bestelMap", bestelMap);
    model.addAttribute("bestelId", id);

    List artikelen = artikelRepository.findAllArtikelByDeletedFalse();
    model.addAttribute("artikelLijst", artikelen);

    return "bestelling/edit";
  }
  
    // TODO make sure the article id is saved in db. 
  @RequestMapping(value = "admin/{id}/edit", method = POST)
  public String editBestellingOpslaan(@PathVariable long id, BestelArtikelLijst bestelArtikelLijst,
          Errors errors,
          Model model,
          Authentication auth) {
    if (errors.hasErrors()) {
      return "/bestelling/nieuw";
    }

    Bestelling bestelling = bestellingRepository.findOne(id);
    bestelling.updateBestelArtikelenList(bestelArtikelLijst);
    bestellingRepository.save(bestelling);
    return "redirect:/bestelling/saved";
  }
  
  @RequestMapping(value = "alleBestellingen")
  public String alleBestellingen(Model model) {
    List<Bestelling> bestellingenLijst = bestellingRepository.findAllBestellingenByDeletedFalse();
    model.addAttribute("bestellingenLijst", bestellingenLijst);
    return "/bestelling/bestellingen";
  }
  
  @RequestMapping(value = "{klantId}/klant")
  public String klantBestellingen(@PathVariable long klantId, Model model) {
    List<Bestelling> bestellingenLijst = bestellingRepository.findAllBestellingenByKlantIdAndDeletedFalse(klantId);
    model.addAttribute("bestellingenLijst", bestellingenLijst);
    return "/bestelling/bestellingen";
  }  
  

  // ------------------- HELPER FUNCTIONS --------------------------------
  private Long getKlantIdByAccount(Authentication authentication) {
    String gebruikersnaam = authentication.getName();
    Account account = accountRepository.getAccountByGebruikersnaamAndDeletedFalse(gebruikersnaam);

    return account.getKlant();
  }
}
