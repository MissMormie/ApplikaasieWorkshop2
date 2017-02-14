/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.artikel;

import applikaasie.utility.storage.StorageService;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Sonja
 */
@Controller
@RequestMapping(value = "artikel")
public class ArtikelController {

  private final ArtikelRepository artikelRepository;
  private final StorageService storageService;

  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public ArtikelController(ArtikelRepository artikelRepository, StorageService storageService) {
    this.artikelRepository = artikelRepository;
    this.storageService = storageService;
  }

  // ----------------- MODEL ATTRIBUTES ----------------------------------------
  // Thymeleaf zou automatisch gegevens van login rollen moeten hebben, maar krijg dat niet werkend, daarom deze work-around in elke controller. 
  // TODO: Get it working!
  @ModelAttribute("login")
  public String addLogin(Authentication auth) {
    for (GrantedAuthority a : auth.getAuthorities()) {
      return a.getAuthority();
    }
    return "";
  }

  // ------------------ MAPPED METHODS -----------------------------------------
  @RequestMapping(value = "/artikelen")
  public String artikelList(Model model) {
    model.addAttribute("artikelList", artikelRepository.findAllArtikelByDeletedFalse());
    return "artikel/artikelen";
  }

  @RequestMapping(value = "/{id}/delete")
  public String deleteArtikel(@PathVariable long id) {
    Artikel artikel = artikelRepository.findOne(id);
    artikel.setDeleted(true);
    artikelRepository.save(artikel);
    return "artikel/deleted";

  }

  @RequestMapping(value = "/{id}/edit")
  public String editArtikel(@PathVariable long id, Model model) {
    Artikel artikel = artikelRepository.findArtikelByIdAndDeletedFalse(id);
    model.addAttribute("artikel", artikel);
    return "artikel/newAndEdit";
  }

  @RequestMapping(value = "/nieuwArtikel")
  public String newArtikel(Model model) {
    model.addAttribute("artikel", new Artikel());
    return "artikel/newAndEdit";
  }

  @RequestMapping(value = {"/saveArtikel", "/nieuwArtikel"}, method = POST)
  public String saveOrUpdateArtikel(@Valid Artikel artikel, Errors errors, Model model, @RequestParam("file") MultipartFile file) {
    if (errors.hasErrors()) {
      model.addAttribute("artikel", artikel);
      return "artikel/newAndEdit";
    }
    if(!file.isEmpty()) {
      String imageName = storageService.store(file);
      artikel.setImage(imageName);
    } 
    
    artikelRepository.save(artikel);

    return "redirect:/artikel/saved";

  }

  @RequestMapping(value = "/saved")
  public String ArtikelSaved() {
    return "artikel/saved";
  }

}
