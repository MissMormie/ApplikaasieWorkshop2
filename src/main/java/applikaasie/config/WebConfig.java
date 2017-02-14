/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Sonja
 */
public class WebConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
            .allowedMethods("PUT", "DELETE", "POST", "GET", "HEAD");
  }
}
