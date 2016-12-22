/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant;

import applikaasie.klant.Klant;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sonja
 */
public class KlantTest {
  
  public KlantTest() {
  }

  
  @Test
  public void shouldCreateKlant() {
    int i = 1;
    new Klant(i, "voornaam" + i, "achternaam", "van de", "0612345678", "e-m@i.l", false);

  }
  
}
