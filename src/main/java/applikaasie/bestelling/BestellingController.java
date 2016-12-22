/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.bestelling;

import applikaasie.artikel.ArtikelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
  
  @Autowired
  public BestellingController(ArtikelRepository artikelRepository, BestellingRepository bestellingRepository) {
    this.artikelRepository = artikelRepository;
    this.bestellingRepository = bestellingRepository;
  }
  
  @RequestMapping(value="nieuweBestelling", method=GET)
  public String nieuweBestelling(Model model) {
    List artikelen = artikelRepository.getAllArtikelen();
    model.addAttribute("artikelLijst", artikelen);
    return "/bestelling/nieuw";
  }
  
  // TODO make sure the article id is saved in db. 
  // TODO get actual klant id van ingelogde klant. Die bestaat nu nog niet, dus doen we alsof ;)
  @RequestMapping(value="nieuweBestelling", method=POST)
  public String nieuweBestellingOpslaan(BestelArtikelLijst bestelArtikelLijst, Errors errors, Bestelling bestelling, Model model) {
   int klantId = 2;
   bestelling.setKlantId(klantId);
    
   bestelling.setBestelArtikelenList(bestelArtikelLijst);
   bestellingRepository.updateOrSaveBestelling(bestelling);
   return "/bestelling/saved"; 
  }
  
  @RequestMapping(value="mijnBestellingen")
  // TODO get actual klant id van ingelogde klant. Die bestaat nu nog niet, dus doen we alsof ;)
  public String mijnBestellingen(Model model) {
    int klantId = 2;
    List<Bestelling> bestellingenLijst = bestellingRepository.getAllBestellingenByKlantId(klantId);
    model.addAttribute("bestellingenLijst", bestellingenLijst);
    return "/bestelling/bestellingen";
  }
  
  @RequestMapping(value="{id}/delete")
  public String deleteBestelling(@PathVariable int id) {
    bestellingRepository.deleteBestellingById(id);
    return "/bestelling/deleted";
  }
  
  @RequestMapping(value="{id}/edit")
  public String editBestelling(@PathVariable int id, Model model) {
    Bestelling bestelling = bestellingRepository.getBestellingById(id);
    model.addAttribute("bestelling", bestelling);
    
    List artikelen = artikelRepository.getAllArtikelen();
    model.addAttribute("artikelLijst", artikelen);

    return "bestelling/edit";
  }
}