package applikaasie.bestelling;

import applikaasie.artikel.Artikel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "BESTELARTIKEL")
public class BestelArtikel implements Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "id_bestelartikel")
  private int id;

  @NotNull
  @Column(name = "Aantal")
  private int aantal;

  @ManyToOne
  @JoinColumn(name = "Bestelling_id")
  private Bestelling bestelling;

  @NotNull
  @OneToOne
  @JoinColumn(name = "artikel_id")
  private Artikel artikel;

  @Column(name = "Deleted")
  private boolean deleted;

  @Column(name = "Bezorgd")
  private boolean bezorgd;
  
  public BestelArtikel() {  }
  
  /*
  @Autowired
  public BestelArtikel(int artikelId, int aantal, Artikel artikel) {
    //setArtikelId(artikelId, artikel);
    
    this.aantal = aantal;
  }
  */

  public Bestelling getBestelling() {
    return bestelling;
  }

  public void setBestelling(Bestelling bestelling) {
    this.bestelling = bestelling;
  }

  public Artikel getArtikel() {
    return artikel;
  }

  public void setArtikel(Artikel artikel) {
    this.artikel = artikel;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAantal() {
    return aantal;
  }

  public void setAantal(int aantal) {
    this.aantal = aantal;
  }

  
  /*
  @Autowired
  public void setArtikelId(int artikelId, Artikel artikel) {
    artikel.setId(artikelId);
    this.artikel = artikel;
    this.artikelId = artikelId;
  }
*/

  public boolean isDeleted() {
    return deleted;
  }


  public boolean isBezorgd() {
    return bezorgd;
  }

  public void setBezorgd(boolean bezorgd) {
    this.bezorgd = bezorgd;
  }

  void delete() {
    deleted = true;
  }
}
