package applikaasie.domein.klant.adres;

import applikaasie.domein.klant.Klant;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "klant_heeft_adres")
public class KlantHeeftAdres implements Serializable {

  // ------------ VARIABLES ---------------------------------
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int idKlant_heeft_adres;

  @ManyToOne
  @JoinColumn(name = "KlantId")
  private Klant klant;

  @OneToOne
  @JoinColumn(name = "AdresId")
  private Adres adres;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "Adres_typeId")
  private AdresType adresType;

  @Column(name = "Deleted")
  private boolean deleted = false;

  // ------------ CONSTRUCTORS ---------------------------------
  public KlantHeeftAdres() {

  }

  public KlantHeeftAdres(int idKlant_heeft_adres, Klant klant, Adres adres, AdresType adresType) {
    this.idKlant_heeft_adres = idKlant_heeft_adres;
    this.klant = klant;
    this.adres = adres;
    this.adresType = adresType;
  }

  // ------------ Getters and Setters ---------------------------------
  public int getIdKlant_heeft_adres() {
    return idKlant_heeft_adres;
  }

  public void setIdKlant_heeft_adres(int idKlant_heeft_adres) {
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

  public AdresType getAdresType() {
    return adresType;
  }

  public void setAdresType(AdresType adresType) {
    this.adresType = adresType;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

}
