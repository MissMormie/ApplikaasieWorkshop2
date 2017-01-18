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
import javax.persistence.*;

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
  @Column(name = "id_bestelling")
  private long id;
  
  @Column(name = "klant_key")
  private long klantId;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date bestelDatum = new Date();
  
  // TODO remove this value once people are logged in and I can get the actual accountId.
  @Column(name = "account_key")
  private long accountId = 5; // created by 
  
  private boolean deleted;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bestelling")
  @Column(name = "bestel_artikel")
  private Set<BestelArtikel> bestelArtikelSet = new HashSet<>(0);

  
  // ------------ CONSTRUCTORS ---------------------------------
  
  public Bestelling() {
  }
    
  public Bestelling(long klantKey, int accountKey) {
    this.klantId = klantKey;
    this.accountId = accountKey;
  }

  public Bestelling(long klantKey, int accountKey, boolean deleted) {
    this.klantId = klantKey;
    this.accountId = accountKey;
    this.deleted = deleted;
  }

  
  // ------------ Getters and Setters ---------------------------------

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getKlantId() {
    return klantId;
  }

  public void setKlantId(long klantId) {
    this.klantId = klantId;
  }

  public Date getBestelDatum() {
    return bestelDatum;
  }

  public void setBestelDatum(Date bestelDatum) {
    this.bestelDatum = bestelDatum;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public Set<BestelArtikel> getBestelArtikelSet() {
    return bestelArtikelSet;
  }

  public void setBestelArtikelSet(Set<BestelArtikel> bestelArtikelSet) {
    this.bestelArtikelSet = bestelArtikelSet;
  }
  

  // ------------ DEFAULT FUNCTIONS ------------------------------------------
  
  // TODO check what happens when bestelling is changed.
  void updateBestelArtikelenList(BestelArtikelLijst bestelLijst) {
//    bestelArtikelSet.clear();
    for(BestelArtikel bestelArtikel : bestelLijst.getBestelArtikelen()) {
      if(ifBestelArtikelAlreadyInListSetAantal(bestelArtikel)) {
        continue;
      }
      if(bestelArtikel.getAantal() != 0) {
        bestelArtikel.setBestelling(this);
        this.bestelArtikelSet.add(bestelArtikel);
      }
    }
  }
  
  public String getBesteldeArtikelen() {
    String artikelen = "";
    for(BestelArtikel bestelArtikel : this.bestelArtikelSet) {
      if(bestelArtikel.getAantal() != 0) {
        artikelen += bestelArtikel.getAantal() + " x " + bestelArtikel.getArtikel().getNaam() + ", ";
      }
    }
    return artikelen;
  }

  void delete() {
    for (BestelArtikel ba: bestelArtikelSet) {
      ba.delete();
    }
    deleted = true;
  }

  private boolean ifBestelArtikelAlreadyInListSetAantal(BestelArtikel checkBestelArtikel) {
    for(BestelArtikel ba: bestelArtikelSet) {
      if(ba.getArtikel().getId() == checkBestelArtikel.getArtikel().getId()) {
        ba.setAantal(checkBestelArtikel.getAantal());
        return true;
      }
    }
    return false;
  }
  
}
