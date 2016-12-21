/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.bestelling;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sonja
 */
public class Bestelling {

  private int id;
  private int klantId;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date bestelDatum = new Date();
  private int accountId;
  private boolean deleted;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bestelid")
  private Set<BestelArtikel> bestelArtikel = new HashSet<>(0);

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

}
