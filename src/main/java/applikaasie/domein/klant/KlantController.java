/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant;

import applikaasie.domein.klant.adres.Adres;
import applikaasie.domein.klant.adres.AdresLijst;
import applikaasie.domein.klant.adres.AdresRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  
  // ------------------ CONSTRUCTOR --------------------------------------------
  
  @Autowired  
  public KlantController(KlantRepository klantRepository, AdresRepository adresRepository) {
    this.klantRepository = klantRepository;
    this.adresRepository = adresRepository;
  }
            
  // ------------------ MAPPED METHODS -----------------------------------------
  
  /**
   * AllKlanten gets a list of all customers and adds it to the model.
   * @param model
   * @return 
   */
  @RequestMapping(value="/klanten")
  public String allKlanten(Model model) {
    model.addAttribute("klantLijst", klantRepository.getAllKlanten());
    return "klant/klanten";
  }

  
  /**
   * showKlant gets the klant information, passes it to the model and returns the edit view.
   * @param id
   * @param model
   * @return 
   */
  @RequestMapping(value="/{id}/editKlant")
  public String editKlant(@PathVariable int id, Model model) {
    model.addAttribute("klant", klantRepository.getKlantById(id));
    return "klant/editKlant";
  }
  
  /**
   * editKlant receives klantInfo through Post method and saves it.
   */
  @RequestMapping(value="/{id}/editKlant", method = RequestMethod.POST)
  public String editKlant(@Valid Klant klant, Errors errors, Model model) {
    if(errors.hasErrors())
      return "klant/editKlant";
    klantRepository.updateKlant(klant);
    return "redirect:/klant/"+ klant.getIdKlant() +"/klant";
  }

  
    /**
   * showKlant gets the klant information, passes it to the model and returns the edit view.
   * @param id
   * @param model
   * @return 
   */
  @RequestMapping(value="/{id}/editAdres")
  public String editAdres(@PathVariable int id, Model model) {
    model.addAttribute("adres", adresRepository.getAdresById(id));
    return "klant/editAdres";
  }
  
  /**
   * editKlant receives klantInfo through Post method and saves it.
   */
  @RequestMapping(value="/{id}/editAdres", method = RequestMethod.POST)
  public String editAdres(@Valid Adres adres, Errors errors, Model model) {
    if(errors.hasErrors())
      return "klant/" + adres.getIdAdres() + "/editAdres";
    adresRepository.updateAdres(adres);
    
    // TODO find klant id by adres. 
    return "redirect:/klant/1/klant";
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
      model.addAttribute("klantLijst", klantRepository.getKlantenByAchternaam(achternaam));
    
    // Enkel voornaam
    else if(!voornaam.equals("") && achternaam.equals("")) 
      model.addAttribute("klantLijst", klantRepository.getKlantenByVoornaam(voornaam));
    
    // Zowel voor als achternaam
    else if(!voornaam.equals("") && !achternaam.equals("")) 
      model.addAttribute("klantLijst", klantRepository.getKlantenByVoornaamEnAchternaam(voornaam, achternaam));
    
    // Geen achter en voornaam
    else 
      model.addAttribute("klantLijst", klantRepository.getAllKlanten());
    
    return "klant/klanten";
  }
  

  /**
   * DeleteKlant ask repository to delete the klant
   * @param id
   */
  // TODO add expection in case delete failed, klant does not exists, other corner cases. 
  @RequestMapping(value="/{id}/delete")
  public String deleteKlant(@PathVariable int id) {
    klantRepository.deleteKlantById(id);
    return "klant/deleted";
  }
  
  
  @RequestMapping(value="/{id}/klant")
  public String showKlant(@PathVariable int id, Model model) {
    model.addAttribute("klant", klantRepository.getKlantById(id));
    return "klant/klant";
  }
  
  
  @RequestMapping(value="/nieuw")
  public String nieuweKlant() {
    return "klant/nieuw";
  }
  
  @RequestMapping(value="/nieuw", method=POST)
  public String saveNieuweKlant(@Valid Klant klant, Errors errors, Adres adresLijst, Model model) {
    int i = 1;
    i++;
    return "klant/nieuw";
    
  }
}
