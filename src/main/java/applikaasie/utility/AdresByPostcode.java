/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.utility;

import applikaasie.klant.adres.Adres;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Sonja
 */
public class AdresByPostcode {
  public static Adres getAdresByPostcodeHuisnummer(Adres adres) {

    return adres;
  }

  public static JsonNode getJson(String postcode, String huisnummer) throws IOException {
    String URLstring = "http://rsas.rsvier.nl/uglyduck/postcodeData/?useKey=mt9c4wv7a13kydzq82be&postcode=" + postcode + "&huisnummer=" + huisnummer;
    System.out.println(URLstring);
    String json;
    try {
      java.net.URL url = new java.net.URL(URLstring);
      Scanner input = new Scanner(url.openStream());
      json = input.nextLine();
    } catch (Exception ex) {
      json = "";
    }
    return translateStringToJson(json);
  }
  
  public static JsonNode translateStringToJson(String jsonString) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode actualObj = mapper.readTree(jsonString);
//    JsonNode streetNode = actualObj.path("street");
    String street = actualObj.get("street").asText();
    System.out.println(street);
    return actualObj;
  }
}
