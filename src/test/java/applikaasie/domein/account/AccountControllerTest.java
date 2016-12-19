/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Before;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Sonja
 */
//@RunWith(SpringRunner.class)
public class AccountControllerTest {

  AccountController controller;

  private MockMvc mockMvc;
  private AccountRepository mockRepository;

  
  @Before
  public void setup() {
    mockRepository = mock(AccountRepository.class);
    controller = new AccountController(mockRepository);
    mockMvc = standaloneSetup(controller).build();
  }

  @Test
  public void testHome() throws Exception {
    mockMvc.perform(get("/account/"))
            .andExpect(view().name("home"));
  }

  @Test
  public void shouldShowListOfAccounts() throws Exception {
    // Create expected result
    String url = "/account/accounts";
    String viewName = "account/accounts";
    List<Account> expectedAccounts = createAccountList(10);
    
    // Set reaction of mock repository
    when(mockRepository.getAllAccounts()).thenReturn(expectedAccounts);
    
    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("accountList"))
           .andExpect(model().attribute("accountList", hasItems(expectedAccounts.toArray())));
  }
  
  /**
   *
   * @throws Exception
   */
  @Test
  public void shouldShowAccountById() throws Exception {
    // Create expected result
    String url = "/account/edit?id=1";
    String viewName = "account/edit";
    Account expectedAccount = createAccount(1);
    
    // Set reaction of mock repository
    when(mockRepository.getAccountById(1)).thenReturn(expectedAccount);
    
    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("account"))
           .andExpect(model().attribute("account", expectedAccount));
  }
  
  @Test
  public void shouldEditAccount() throws Exception {
    // Create expected result
    String url = "/account/edit?id=1";
    String viewName = "redirect:/account/saved";
    Account expectedAccount = new Account(1, "sonja", "123", null, new Date(), 0, false);
    System.out.println("mock: " +expectedAccount);
    // Set reaction of mock repository
    when(mockRepository.updatePassword(expectedAccount)).thenReturn(true);
    
    System.out.println(mockRepository.updatePassword(expectedAccount));
    // check result equals expected
    mockMvc.perform(post(url)
                        .param("idAccount", "1")
                        .param("gebruikersnaam", "sonja")
                        .param("wachtwoord", "123")
                        .param("klant", "0")) // Param klant id wordt niet meegestuurd. Ik krijg het neit aan de praat anders, verstuurd maakt er null van, account zelf 0.
           .andExpect(view().name(viewName));
  }

  private List<Account> createAccountList(int count) {
    List<Account> accountsList = new ArrayList<Account>();
    for (int i = 0; i < count; i++) {
      accountsList.add(createAccount(i));
    }
    return accountsList;
  }
  
  private Account createAccount(int i) {
    return new Account(i, "gebruikersnaam", "wachtwoord", "accountStatus", new Date(), null, false);
  }

}
