/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.artikel;

import java.util.List;

/**
 *
 * @author Sonja
 */
public interface ArtikelRepository {
  
  // -------------------------- CREATE ------------------------------
  
  public Boolean createArtikel(Artikel artikel);
  
  public Boolean updateOrSaveArtikel(Artikel artikel);

  // -------------------------- READ --------------------------------

  public Artikel getArtikelById(int id);
  
  public List<Artikel> getAllArtikelen();
  
  // -------------------------- UPDATE ------------------------------
  
  public Boolean updateArtikel(Artikel artikel);

  // -------------------------- DELETE ------------------------------
  
  public Boolean deleteArtikel(Artikel artikel);
  
  public Boolean deleteArtikelById(int id);
  
}
