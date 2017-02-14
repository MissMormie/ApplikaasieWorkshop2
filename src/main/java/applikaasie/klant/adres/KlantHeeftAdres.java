package applikaasie.klant.adres;

import applikaasie.klant.Klant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "KLANT_HEEFT_ADRES")
public class KlantHeeftAdres implements Serializable {

  // ------------ VARIABLES ---------------------------------
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long idKlant_heeft_adres;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "Klant_Id")
  private Klant klant;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "Adres_Id")
  private Adres adres;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "Adres_typeId")
  private AdresTypeObject adresType;

  @Column(name = "Deleted")
  private boolean deleted = false;

  // ------------ CONSTRUCTORS ---------------------------------
  public KlantHeeftAdres() {

  }

  public KlantHeeftAdres(int idKlant_heeft_adres, Klant klant, Adres adres, AdresTypeObject adresType) {
    this.idKlant_heeft_adres = idKlant_heeft_adres;
    this.klant = klant;
    this.adres = adres;
    this.adresType = adresType;
  }
  
  public KlantHeeftAdres(Klant klant, Adres adres, AdresTypeObject adresType) {
    this.klant = klant;
    this.adres = adres;
    this.adresType = adresType;
  }  

  // ------------ Getters and Setters ---------------------------------
  public long getIdKlant_heeft_adres() {
    return idKlant_heeft_adres;
  }

  public void setIdKlant_heeft_adres(long idKlant_heeft_adres) {
    this.idKlant_heeft_adres = idKlant_heeft_adres;
  }

  public Klant getKlant() {
    return klant;
  }

  public void setKlant(Klant klant) {
    this.klant = klant;
  }

  public Adres getAdres() {
    return adres;
  }

  public void setAdres(Adres adres) {
    this.adres = adres;
  }

  public AdresTypeObject getAdresTypeObject() {
    return adresType;
  }

  public void setAdresType(AdresTypeObject adresType) {
    this.adresType = adresType;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void delete() {
    deleted = true;
    adres.delete();
  }

}
