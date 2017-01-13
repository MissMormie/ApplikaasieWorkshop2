/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
/**
 *
 * @author Sonja
 */
@Entity
@Table (name = "ADRES")
public class Adres implements Cloneable, Serializable {

    // ------------ VARIABLES ---------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long idAdres;
    
    @Size(max = 45, min = 2)
    @Column (name = "Straat", nullable = false)
    @NotNull
    private String straat;
    
    @Max(99999)
    @Column (name = "Huisnummer", nullable = false)
    @NotNull
    private int huisnummer;
    
    @Size(max = 10)
    @Column (name = "Toevoeging", nullable = true)
    private String toevoeging;
    
    @Size(max = 6, min = 6)
    @Column (name = "Postcode", nullable = false)
    @NotNull
    private String postcode;
    
    @Size(max = 30)
    @Column (name = "Woonplaats", nullable = false)
    @NotNull
    private String woonplaats;
    
    @Column (name = "Deleted")
    private boolean deleted = false;
    

    // ------------ CONSTRUCTORS ---------------------------------
    /**
     * Initiates AdresPojo with:
     * @param idAdres int
     * @param straat String
     * @param huisnummer int
     * @param toevoeging String
     * @param woonplaats String
     * @param deleted boolean
     */
    public Adres(long idAdres, String straat, int huisnummer, String toevoeging, String postcode, String woonplaats, boolean deleted) {
        this.idAdres = idAdres;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.deleted = deleted;
    }    
    
    public Adres() {    }
    
    // ------------ Getters and Setters ---------------------------------

    public long getIdAdres() {
        return idAdres;
    }

    public void setIdAdres(long idAdres) {
        this.idAdres = idAdres;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }    
    
    // ------------ PUBLIC FUNCTIONS ---------------------------------

    /**
     * Clones the currentAdresPojo and returns a new instance
     * @return Adres 
     * @throws java.lang.CloneNotSupportedException 
     */
    @Override 
    public Adres clone() throws CloneNotSupportedException{
        Adres clone = (Adres) super.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "AdresPojo{" + "idAdres=" + idAdres + ", straat=" + straat + ", huisnummer=" + huisnummer + ", toevoeging=" + toevoeging + ", woonplaats=" + woonplaats + ", deleted=" + deleted +"}";
    }

    /**
     * Changes adres to passed address, except adresId. If adresId is set this 
     * will not be overwritten. If it is 0 it will be overwritten.
     * @param adres 
     */
  public void updateAdres(Adres adres) {
    this.huisnummer = adres.huisnummer;
    this.deleted = adres.deleted;
    this.postcode = adres.postcode;
    this.straat = adres.straat;
    this.toevoeging = adres.toevoeging;
    this.woonplaats = adres.woonplaats;
    if(this.idAdres == 0)
      this.idAdres = adres.idAdres;
  }

  void delete() {
    deleted = true;
  }
    
    
}
