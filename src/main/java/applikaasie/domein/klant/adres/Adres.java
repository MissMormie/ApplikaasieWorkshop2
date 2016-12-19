/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant.adres;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Sonja
 */
@Entity
@Table (name = "adres")
public class Adres implements Cloneable, Serializable {

    // ------------ VARIABLES ---------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int idAdres;
    
    @Column (name = "Straat", nullable = false)
    @NotNull
    private String straat;
    
    @Column (name = "Huisnummer", nullable = false)
    @NotNull
    private int huisnummer;
    
    @Column (name = "Toevoeging", nullable = true)
    private String toevoeging;
    
    @Column (name = "Postcode", nullable = false)
    @NotNull
    private String postcode;
    
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
    public Adres(int idAdres, String straat, int huisnummer, String toevoeging, String postcode, String woonplaats, boolean deleted) {
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

    public int getIdAdres() {
        return idAdres;
    }

    public void setIdAdres(int idAdres) {
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

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
    
    
}
