package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.AccountCurrencyEnum;
import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AccountsDao {

    public void create(Accounts accounts) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(accounts);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    public Accounts findByIban(String iban) {
        Accounts result = null;
        String query = "select * from accounts where iban = '" + iban + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Accounts> nquery = session.createNativeQuery(query, Accounts.class);
            List<Accounts> foundAccountss = nquery.getResultList();
            if (foundAccountss.isEmpty()) {
                return result;
            } else {
                result = foundAccountss.get(0);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }




    public Accounts findById(Long accountsId) {
        Accounts result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(Accounts.class, accountsId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Accounts> findAllById(Long customerId) {
        List<Accounts> result = null;
        String query = "SELECT * FROM accounts where customer_customer_id = '" + customerId +"'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Accounts> nquery = session.createNativeQuery(query, Accounts.class);
            result = nquery.getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Accounts update(Long accountsId, Accounts accountsDetails) {
        Accounts result = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Accounts accountsToBeUpdated = session.find(Accounts.class, accountsId);

            transaction = session.beginTransaction();

            accountsToBeUpdated.setFriendlyName(accountsDetails.getFriendlyName());
            accountsToBeUpdated.setIban(accountsDetails.getIban());
            accountsToBeUpdated.setBalance(accountsDetails.getBalance());
            accountsToBeUpdated.setAccountCurrencyEnum(accountsDetails.getAccountCurrencyEnum());

            session.update(accountsToBeUpdated);

            transaction.commit();
            result = session.find(Accounts.class, accountsId);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }


    public void updateBalance (long id,  double balance){
        Transaction transaction = null;
        Accounts accounts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            accounts = session.find(Accounts.class, id);
            accounts.setBalance(balance);
            session.update(accounts);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }

    }


    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Accounts accountsToBeDeleted = session.find(Accounts.class, id);

            transaction = session.beginTransaction();

            session.delete(accountsToBeDeleted);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

}
