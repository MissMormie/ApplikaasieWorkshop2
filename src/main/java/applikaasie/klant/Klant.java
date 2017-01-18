package applikaasie.klant;

import applikaasie.klant.adres.Adres;
import applikaasie.klant.adres.AdresType;
import applikaasie.klant.adres.AdresTypeObject;
import applikaasie.klant.adres.KlantHeeftAdres;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "KLANT")
public class Klant implements Serializable {

  // ------------ VARIABLES ---------------------------------
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long idKlant = 0;

  @Size(max = 55)
  @NotNull
  @Column(name = "Voornaam")
  private String voornaam;

  @Size(max = 55)
  @NotNull
  @Column(name = "Achternaam")
  private String achternaam;

  @Size(max = 15)
  @Column(name = "Tussenvoegsel")
  private String tussenvoegsel;

  @Size(max = 20)
  @Column(name = "Telefoonnummer")
  @Pattern(regexp="^(?:0|(?:\\+|00) ?31 ?)(?:(?:[1-9] ?(?:[0-9] ?){8})|"
                + "(?:6 ?-? ?[1-9] ?(?:[0-9] ?){7})|"
                + "(?:[1,2,3,4,5,7,8,9]\\d ?-? ?[1-9] ?(?:[0-9] ?){6})|"
                + "(?:[1,2,3,4,5,7,8,9]\\d{2} ?-? ?[1-9] ?(?:[0-9] ?){5}))$", message="Vul een juist nummer in")
  private String telefoonnummer;

  @Size(max = 45)
  @Column(name = "Emailadres")
  @Email
  private String emailadres;

  @Column(name = "Deleted")
  private boolean deleted = false;

  // TODO change fetch type to lazy, because most of the time we dont' need all info. 
  // http://stackoverflow.com/a/22645558/7172179
  // mapped by  references the variable name in the KlantHeeftAdres class that it's mapped to. 
  // Beware, this won't change when using refactor.
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "klant")
  private Set<KlantHeeftAdres> adressen = new HashSet<>(0);

  // ------------ CONSTRUCTORS ---------------------------------
  /**
   * Initiates empty KlantPojo
   */
  public Klant() {
  }

  /**
   * Initiates KlantPojo with:
   *
   * @param voornaam String
   * @param achternaam String
   * @param tussenvoegsel String
   * @param telefoonnummer String
   * @param emailadres String
   * @param deleted Boolean
   */
  public Klant(String voornaam, String achternaam, String tussenvoegsel, String telefoonnummer, String emailadres, boolean deleted) {
    this.voornaam = voornaam;
    this.achternaam = achternaam;
    this.tussenvoegsel = tussenvoegsel;
    this.telefoonnummer = telefoonnummer;
    this.emailadres = emailadres;
    this.deleted = deleted;
  }

  // TODO check if this is used.
  public Klant(int id, String voornaam, String achternaam, String tussenvoegsel, String telefoonnummer, String emailadres, boolean deleted) {
    this.idKlant = id;
    this.voornaam = voornaam;
    this.achternaam = achternaam;
    this.tussenvoegsel = tussenvoegsel;
    this.telefoonnummer = telefoonnummer;
    this.emailadres = emailadres;
    this.deleted = deleted;
  }

  // ------------ Getters and Setters ---------------------------------
  public long getIdKlant() {
    return idKlant;
  }

  public void setIdKlant(long idKlant) {
    this.idKlant = idKlant;
  }

  public String getVoornaam() {
    return voornaam;
  }

  public void setVoornaam(String voornaam) {
    this.voornaam = voornaam;
  }

  public String getAchternaam() {
    return achternaam;
  }

  public void setAchternaam(String achternaam) {
    this.achternaam = achternaam;
  }

  public String getTussenvoegsel() {
    return tussenvoegsel;
  }

  public void setTussenvoegsel(String tussenvoegsel) {
    this.tussenvoegsel = tussenvoegsel;
  }

  public String getTelefoonnummer() {
    return telefoonnummer;
  }

  public void setTelefoonnummer(String telefoonnummer) {
    this.telefoonnummer = telefoonnummer;
  }

  public String getEmailadres() {
    return emailadres;
  }

  public void setEmailadres(String emailadres) {
    this.emailadres = emailadres;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Set<KlantHeeftAdres> getAdressen() {
    return adressen;
  }
/*
  public void setAdressen(Set<KlantHeeftAdres> adressen) {
    this.adressen = adressen;
  }
*/
  void setAdres(Adres adres, AdresTypeObject adresTypeObject) {
    // Kijk of er al een KlantHeeftAdres is met dit adresType.
    for (KlantHeeftAdres kha : adressen) {
      if (kha.getAdresTypeObject() == adresTypeObject) {
        kha.getAdres().updateAdres(adres);
        return;
      }
    }
    
    // bestaat nog niet, maak er dan zelf 1. 
    // Moet ik hier zelf een instance aanmaken?
    adressen.add(new KlantHeeftAdres(this, adres, adresTypeObject));

  }

  // ------------ DEFAULT FUNCTIONS ------------------------------------------
  @Override
  public String toString() {
    return "KlantPojo{" + "id=" + idKlant + ", voornaam=" + voornaam + ", achternaam=" + achternaam + ", tussenvoegsel=" + tussenvoegsel + ", telefoonnummer=" + telefoonnummer + ", emailadres=" + emailadres + ", deleted=" + deleted + '}';
  }

  void delete() {
    for(KlantHeeftAdres kha : adressen ) {
      kha.delete();
    }
    deleted = true;
      
  }

  protected Adres heeftDitAdres(long adresId) {
    for(KlantHeeftAdres kha : adressen) {
      if( kha.getAdres().getIdAdres() == adresId)
        return kha.getAdres();
    }
    // TODO throw Exception adres hoort niet bij deze klant. 
    return null;
  }

}
