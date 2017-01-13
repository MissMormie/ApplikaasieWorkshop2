/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.artikel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonja
 */
@Entity
@Table(name = "ARTIKEL")
public class Artikel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id_artikel")
  private long id;

  @NotNull
  private String naam;

  @NotNull
  @Digits(integer = 6, fraction = 2)
  private double prijs;

  @NotNull
  private int voorraad;

  private boolean deleted;

  public Artikel() {
  }
    
  public Artikel(long id, String naam, double prijs, int voorraad, boolean deleted) {
    this.id = id;
    this.naam = naam;
    this.prijs = prijs;
    this.voorraad = voorraad;
    this.deleted = deleted;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNaam() {
    return naam;
  }

  public void setNaam(String naam) {
    this.naam = naam;
  }

  public double getPrijs() {
    return prijs;
  }

  public void setPrijs(double prijs) {
    this.prijs = prijs;
  }

  public int getVoorraad() {
    return voorraad;
  }

  public void setVoorraad(int voorraad) {
    this.voorraad = voorraad;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}
