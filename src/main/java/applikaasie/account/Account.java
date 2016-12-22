/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "ACCOUNT", uniqueConstraints = {
  @UniqueConstraint(columnNames = "Gebruikersnaam")})
public class Account implements Serializable, Comparable<Account> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idAccount;

  @Column(name = "Gebruikersnaam", nullable = false, unique = true)
  @NotNull
  private String gebruikersnaam;

  @Column(name = "Wachtwoord", nullable = false)
  @NotNull
  private String wachtwoord;

  @Column(name = "Accountstatus", nullable = false)
  private String accountStatus = "medewerker";

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "Datum_Aanmaak", nullable = false)
  private Date datum_aanmaak = new Date();

  @Column(name = "KlantId", nullable = true)
  private Integer klant;

  @Column(name = "Deleted", nullable = false)
  private boolean deleted = false;

  // -------------- Constructor ----------------
  public Account(Integer idAccount, String gebruikersnaam, String wachtwoord, String accountStatus, Date datum_aanmaak, Integer klant, boolean deleted) {
    this.idAccount = idAccount;
    this.gebruikersnaam = gebruikersnaam;
    this.wachtwoord = wachtwoord;
    this.accountStatus = accountStatus;
    this.datum_aanmaak = datum_aanmaak;
    this.klant = klant;
    this.deleted = deleted;
  }

  public Account(String gebruikersnaam, String wachtwoord) {
    this.gebruikersnaam = gebruikersnaam;
    this.wachtwoord = wachtwoord;
  }

  public Account() {
  }
  // -------------- Getters and Setters ----------------

  public void setWachtwoord(String wachtwoord) {
    this.wachtwoord = wachtwoord;
  }

  public String getWachtwoord() {
    return wachtwoord;
  }

  /*
    public void setWachtwoordPlainText(String wachtwoord) {
        this.wachtwoord = makeWachtwoordHash(wachtwoord);
    }
   */

  public int getIdAccount() {
    return idAccount;
  }

  public void setIdAccount(int idAccount) {
    this.idAccount = idAccount;
  }

  public String getGebruikersnaam() {
    return gebruikersnaam;
  }

  public void setGebruikersnaam(String gebruikersnaam) {
    this.gebruikersnaam = gebruikersnaam;
  }

  public String getAccountStatus() {
    // TODO check if this is necessary here. I want it be right before.
    if (klant != 0)
      this.accountStatus = "klant";
    
    return accountStatus;
  }

  public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
  }

  //TODO
  public Date getDatum_aanmaak() {
    return datum_aanmaak;
  }

  public void setDatum_aanmaak(Date Datum_aanmaak) {
    this.datum_aanmaak = Datum_aanmaak;
  }

  public Integer getKlant() {
    return klant;
  }

  public void setKlant(Integer klant) {
    if (klant != 0)
      this.accountStatus ="klant";
    this.klant = klant;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  // ------------------ Public functions -------------------------
  // ------------------ Private functions -------------------------
  @Override
  public String toString() {
    return "AccountPojo{" + "idAccount=" + idAccount + ", gebruikersnaam=" + gebruikersnaam + ", wachtwoordHash=" + wachtwoord + ", accountStatus=" + accountStatus + ", datum_aanmaak=" + datum_aanmaak + ", klantId=" + klant + ", deleted=" + deleted + '}';
  }

  @Override
  public int compareTo(Account account) {
    if(idAccount > account.idAccount)
      return 1;
    if(idAccount < account.idAccount)
      return -1;

    if(!gebruikersnaam.equals(account.gebruikersnaam) ||
       !wachtwoord.equals(account.wachtwoord)         ||
       !accountStatus.equals(account.accountStatus)   ||
       !Objects.equals(klant, account.klant)          ||
       deleted != account.deleted)
      return 1;
    
    return 0;
  }

}
