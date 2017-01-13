/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Sonja
 */
@Entity
@Table (name = "adres_type")
public class AdresTypeObject implements Serializable {

  @Id
  @GeneratedValue (strategy = GenerationType.AUTO)
  private long idadres_type;
  
  @Column (name = "Soort", nullable = false)
  @Enumerated(EnumType.STRING)
  private AdresType adresType;

  public long getIdadres_type() {
    return idadres_type;
  }

  public void setIdadres_type(long idadres_type) {
    this.idadres_type = idadres_type;
  }

  public AdresType getAdresType() {
    return adresType;
  }

  public void setAdresType(AdresType adresType) {
    this.adresType = adresType;
  }
  


  
}
