/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
  @Column(name = "id_account")
  private long id;

  @Column(name = "Gebruikersnaam", nullable = false, unique = true)
  @Size(max = 15)
  @NotNull
  private String gebruikersnaam;

  @Column(name = "Wachtwoord", nullable = false)
  @NotNull
  @Size(max = 64)
  private String wachtwoord;

  @Column(name = "Accountstatus", nullable = false)
  private String accountStatus = "medewerker";

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "Datum_Aanmaak", nullable = false)
  private Date datum_aanmaak = new Date();

  @Column(name = "klant_id", nullable = true)
  private Long klant;

  @Column(name = "Deleted", nullable = false)
  private boolean deleted = false;
  
  @Transient
  private String wachtwoordPlainText;

  // -------------- Constructor ----------------
  public Account(Integer idAccount, String gebruikersnaam, String wachtwoord, String accountStatus, Date datum_aanmaak, Long klant, boolean deleted) {
    this.id = idAccount;
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
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getGebruikersnaam() {
    return gebruikersnaam;
  }

  public void setGebruikersnaam(String gebruikersnaam) {
    this.gebruikersnaam = gebruikersnaam;
  }

  public String getAccountStatus() {
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

  public Long getKlant() {
    return klant;
  }

  public void setKlant(Long klant) {
    if (klant != null) {
      this.klant = klant;
      this.accountStatus = "klant";
    } else {
      this.klant = null;      
    }
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  // ------------------ Public functions -------------------------
  public void setPlainTextWachtwoord(String wachtwoord) {
    setWachtwoord(makeWachtwoordHash(wachtwoord));
  }
  
  // ------------------ Private functions -------------------------
  @Override
  public String toString() {
    return "AccountPojo{" + "idAccount=" + id + ", gebruikersnaam=" + gebruikersnaam + ", wachtwoordHash=" + wachtwoord + ", accountStatus=" + accountStatus + ", datum_aanmaak=" + datum_aanmaak + ", klantId=" + klant + ", deleted=" + deleted + '}';
  }

  @Override
  public int compareTo(Account account) {
    if (id > account.id) {
      return 1;
    }
    if (id < account.id) {
      return -1;
    }

    if (!gebruikersnaam.equals(account.gebruikersnaam)
            || !wachtwoord.equals(account.wachtwoord)
            || !accountStatus.equals(account.accountStatus)
            || !Objects.equals(klant, account.klant)
            || deleted != account.deleted) {
      return 1;
    }

    return 0;
  }

  // ------------------ Public functions -------------------------
  /**
   * Hash function from:
   * http://stackoverflow.com/questions/3103652/hash-string-via-sha-256-in-java
   *
   * @param base
   * @return
   */
  private String makeWachtwoordHash(String base) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(base.getBytes("UTF-8"));
      StringBuilder hexString = new StringBuilder();

      for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString();
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
      throw new RuntimeException(ex);
    }
  }
}
