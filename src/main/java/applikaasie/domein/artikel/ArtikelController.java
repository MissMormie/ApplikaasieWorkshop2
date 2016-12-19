/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.artikel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sonja
 */
@Controller
@RequestMapping(value= "artikel")
public class ArtikelController {
  private final ArtikelRepository artikelRepository;
  
  // ------------------ CONSTRUCTOR --------------------------------------------
  
  @Autowired
  public ArtikelController(ArtikelRepository artikelRepository) {
    this.artikelRepository = artikelRepository;
  }

  // ------------------ MAPPED METHODS -----------------------------------------
  
  @RequestMapping(value="/artikelen")
  public String artikelList(Model model) {
    model.addAttribute("artikelList", artikelRepository.getAllArtikelen());
    return "artikel/artikelen";
  }
  
  
}
