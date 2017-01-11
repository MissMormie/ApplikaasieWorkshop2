/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO change this class from stub to get actual data
 *
 * @author Sonja
 */
@Repository
@Transactional
public class HibernateAccountRepository implements AccountRepository {

  @PersistenceUnit
  protected EntityManagerFactory emf;

  // -------------------------- CREATE ------------------------------
  @Override
  public Boolean createAccount(Account account) {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist( account );
    entityManager.getTransaction().commit();
    entityManager.close();    
    return true;
  }

  // -------------------------- READ --------------------------------
  @Override
  public Account getAccountByUsernamePassword(String username, String password) {
    EntityManager entityManager = emf.createEntityManager();
//    entityManager.getTransaction().begin();
    String hql = "FROM Account a "
               + "WHERE a.deleted = :deleted "
               + "AND a.gebruikersnaam = :gebruikersnaam"
               + "AND a.wachtwoord = :wachtwoord";

    Account account = (Account) entityManager
            .createQuery(hql)
            .setParameter("deleted", false)
            .setParameter("gebruikersnaam", username)
            .setParameter("wachtwoord", password)
            .getSingleResult();
    
//    entityManager.getTransaction().commit();
    entityManager.close();
    return account;
  }

  @Override
  public List<Account> getAllAccounts() {
    return (List<Account>) emf.createEntityManager().createQuery("FROM " + Account.class.getName() + " a WHERE a.deleted = :deleted")
            .setParameter("deleted", false)
            .getResultList();
  }

  @Override
  public Account getAccountById(long id) {
    Account ac = emf.createEntityManager().find(Account.class, id);
    if (ac == null) {
      // TODO throw custom exception noSuchAccount;
    }
    return ac;
  }

  public Boolean usernameExists(String username) {
    EntityManager em = emf.createEntityManager();
    String hql = "FROM Account a WHERE a.deleted = :deleted AND a.gebruikersnaam = :gebruikersnaam";

    List<Account> accountList = (List<Account>) em
            .createQuery(hql)
            .setParameter("deleted", false)
            .setParameter("gebruikersnaam", username)
            .getResultList();
    
    return !accountList.isEmpty();
  }

  // -------------------------- UPDATE ------------------------------
  @Override
  @Transactional
  public Boolean updatePassword(Account account) {
    EntityManager em = emf.createEntityManager();

    int rowsAffected = em
            .createQuery("UPDATE " + Account.class.getName() + " as a set a.wachtwoord = :wachtwoord")
            .setParameter("wachtwoord", account.getWachtwoord())
            .executeUpdate();

    // TODO throw custom error instead. 
    if (rowsAffected != 1) {
      return false;
    }
    return true;
  }

  // -------------------------- DELETE ------------------------------
  @Override
  public Boolean deleteAccountById(int accountId) {
    return true;
  }

  // -------------------------- HELPERS  ------------------------------
  // TODO remove this function once I've implemented database connection.    
  private List<Account> createAccountList(int count) {
    List<Account> accountsList = new ArrayList<Account>();
    for (int i = 0; i < count; i++) {
      accountsList.add(new Account(i, "Sonja" + i, "123" + i, "admin", new Date(), null, false));
    }
    return accountsList;
  }
}
