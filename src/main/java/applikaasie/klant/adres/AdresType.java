/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.klant.adres;

/**
 *
 * @author Sonja
 */
public enum AdresType {
  POSTADRES(1), FACTUURADRES(2), BEZORGADRES(3);
  private int id;

  AdresType(int id) {
    this.id = id;
  }

 
  public int getValue() { return id; }

  public static AdresType parse(int id) {
    AdresType right = null; // Default
    for (AdresType item : AdresType.values()) {
      if (item.getValue() == id) {
        right = item;
        break;
      }
    }
    return right;
  }

}
