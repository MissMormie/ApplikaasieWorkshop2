package applikaasie.domein.klant;

import applikaasie.domein.klant.adres.KlantHeeftAdres;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "klant")
public class Klant implements Serializable{
      
    // ------------ VARIABLES ---------------------------------
    
    @GeneratedValue (strategy = GenerationType.AUTO) 
    @Id
    private int idKlant = 0;
    
    @NotNull
    @Column (name = "Voornaam") 
    private String voornaam;
    
    @NotNull
    @Column (name = "Achternaam")
    private String achternaam;
    
    @Column (name = "Tussenvoegsel")
    private String tussenvoegsel;
    
    @Column (name = "Telefoonnummer")
    private String telefoonnummer;
    
    @Column (name = "Emailadres")
    private String emailadres;
    
    @Column (name = "Deleted")
    private boolean deleted = false;
    
    // TODO change fetch type to lazy, because most of the time we dont' need all info. 
    // http://stackoverflow.com/a/22645558/7172179
    
    // mapped by  references the variable name in the KlantHeeftAdres class that it's mapped to. 
    // Beware, this won't change when using refactor.
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "klant")
    private Set<KlantHeeftAdres> adressen = new HashSet<>(0);
    
    // ------------ CONSTRUCTORS ---------------------------------
    
    /**
     * Initiates empty KlantPojo
     */
    public Klant() { }
    
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

    public int getIdKlant() {
        return idKlant;
    }

    public void setIdKlant(int idKlant) {
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

    public void setAdressen(Set<KlantHeeftAdres> adressen) {
        this.adressen = adressen;
    }

    @Override
    public String toString() {
        return "KlantPojo{" + "id=" + idKlant + ", voornaam=" + voornaam + ", achternaam=" + achternaam + ", tussenvoegsel=" + tussenvoegsel + ", telefoonnummer=" + telefoonnummer + ", emailadres=" + emailadres + ", deleted=" + deleted + '}';
    }
 
}
