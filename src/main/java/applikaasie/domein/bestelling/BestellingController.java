/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.bestelling;

import applikaasie.domein.artikel.ArtikelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  
  @RequestMapping(value="nieuweBestelling", method=POST)
  public String nieuweBestellingOpslaan(BestelArtikelLijst bestelArtikelLijst, Model model) {
   int i = 1;
   i++;
   return "home"; 
  }
  
  @RequestMapping(value="mijnBestellingen")
  // TODO get actual klant id van ingelogde klant. Die bestaat nu nog niet, dus doen we alsof ;)
  public String mijnBestellingen() {
    int klantId = 2;
    List<Bestelling> bestellingenLijst = bestellingRepository.getAllBestellingenByKlantId(klantId);
    return "";
  }
  
}
