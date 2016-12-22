/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.artikel;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Sonja
 */
@Controller
@RequestMapping(value = "artikel")
public class ArtikelController {

  private final ArtikelRepository artikelRepository;

  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public ArtikelController(ArtikelRepository artikelRepository) {
    this.artikelRepository = artikelRepository;
  }

  // ------------------ MAPPED METHODS -----------------------------------------
  @RequestMapping(value = "/artikelen")
  public String artikelList(Model model) {
    model.addAttribute("artikelList", artikelRepository.getAllArtikelen());
    return "artikel/artikelen";
  }

  @RequestMapping(value = "/{id}/delete")
  public String deleteArtikel(@PathVariable int id) {
    if (artikelRepository.deleteArtikelById(id)) {
      return "artikel/deleted";
    }
    return "error";

  }

  @RequestMapping(value = "/{id}/edit")
  public String editArtikel(@PathVariable int id, Model model) {
    Artikel artikel = artikelRepository.getArtikelById(id);
    model.addAttribute("artikel", artikel);
    return "artikel/newAndEdit";
  }

  @RequestMapping(value = "/nieuwArtikel")
  public String newArtikel(Model model) {
    model.addAttribute("artikel", new Artikel());
    return "artikel/newAndEdit";
  }

  @RequestMapping(value = {"/saveArtikel", "/nieuwArtikel"}, method = POST)
  public String saveOrUpdateArtikel(@Valid Artikel artikel, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute("artikel", artikel);
      return "artikel/newAndEdit";
    }
    // TODO create expection catcher. Aspect spring thing ;)
    artikelRepository.updateOrSaveArtikel(artikel);
    // TODO add flash attribute to make sure we can show the artikel once its saved. 
    // Spring site offline atm, so can't look at docs how to do this. 
    return "redirect:/artikel/saved";

  }

  @RequestMapping(value = "/saved")
  public String ArtikelSaved() {
    return "artikel/saved";
  }

}
