/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant;

import applikaasie.account.Account;
import applikaasie.klant.adres.Adres;
import applikaasie.klant.adres.AdresRepository;
import applikaasie.klant.adres.PostcodeData;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Sonja
 */
@RestController
public class KlantRestController {
  // ------------------ VARIABLES --------------------------------------------
  private final KlantRepository klantRepository;
  private final AdresRepository adresRepository;

  
  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public KlantRestController(KlantRepository klantRepository, AdresRepository adresRepository) {
    this.klantRepository = klantRepository;
    this.adresRepository = adresRepository;
  }  
  
  @RequestMapping("/rest/klant/klanten")
  public List<Klant> getAccounts() {
    List<Klant> klanten = klantRepository.findAllKlantByDeletedFalse();
    return klanten;
  }  
  
  @RequestMapping("/rest/klant/{klantId}")
  public Klant getKlantById(@PathVariable long klantId) {
    return klantRepository.findKlantByIdKlantAndDeletedFalse(klantId);
  }
  
  @RequestMapping("/rest/klant/editAdres/{adresId}")
  public Adres getAdresById(@PathVariable long adresId) {
    return adresRepository.findAdresByIdAdresAndDeletedFalse(adresId);
  }
  
  @RequestMapping("/rest/klant/getAdres")
  public Adres getAdresByPostcodeAndHuisnummer(@RequestBody Adres adres) {
    /*
    RestTemplate restTemplate = new RestTemplate();
    PostcodeData pd = restTemplate.getForObject("http://rsas.rsvier.nl/uglyduck/postcodeData/?useKey=mt9c4wv7a13kydzq82be&postcode=2402RC&huisnummer=86", PostcodeData.class);
    adres.setStraat(pd.getStreet());
    adres.setWoonplaats(pd.getCity());
    
    return adres;*/

    //json
    return adres;

  }
}
