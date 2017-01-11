/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.bestelling;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "BESTELLING")
public class Bestelling  implements Serializable{

  // ------------ VARIABLES ---------------------------------

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  private int klantId;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date bestelDatum = new Date();
  private int accountId; // created by 
  private boolean deleted;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bestelling")
  private Set<BestelArtikel> bestelArtikel = new HashSet<>(0);

  
  // ------------ CONSTRUCTORS ---------------------------------
  
  public Bestelling() {
  }
    
  public Bestelling(int klantKey, int accountKey) {
    this.klantId = klantKey;
    this.accountId = accountKey;
  }

  public Bestelling(int klantKey, int accountKey, boolean deleted) {
    this.klantId = klantKey;
    this.accountId = accountKey;
    this.deleted = deleted;
  }

  
  // ------------ Getters and Setters ---------------------------------

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getKlantId() {
    return klantId;
  }

  public void setKlantId(int klantId) {
    this.klantId = klantId;
  }

  public Date getBestelDatum() {
    return bestelDatum;
  }

  public void setBestelDatum(Date bestelDatum) {
    this.bestelDatum = bestelDatum;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Set<BestelArtikel> getBestelArtikel() {
    return bestelArtikel;
  }

  public void setBestelArtikel(Set<BestelArtikel> bestelArtikel) {
    this.bestelArtikel = bestelArtikel;
  }
  

  // ------------ DEFAULT FUNCTIONS ------------------------------------------
  
  // TODO check what happens when bestelling is changed.
  void setBestelArtikelenList(BestelArtikelLijst bestelLijst) {
    bestelArtikel.clear();
    for(BestelArtikel bestelArtikel : bestelLijst.getBestelArtikelen()) {
      if(bestelArtikel.getAantal() != 0)
        this.bestelArtikel.add(bestelArtikel);
    }
  }
  
  public String getBesteldeArtikelen() {
    String artikelen = "";
    for(BestelArtikel bestelArtikel : this.bestelArtikel) {
      artikelen += bestelArtikel.getAantal() + " x " + bestelArtikel.getArtikel().getNaam() + ", ";
    }
    return artikelen;
  }
  
}
