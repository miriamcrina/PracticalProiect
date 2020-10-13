package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.AccountCurrency;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AccountCurrencyDao {

    public void create(AccountCurrency accountCurrency) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(accountCurrency);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    public AccountCurrency findByCurrency (String currency) {
        AccountCurrency result = null;
        String query = "select * from account_currency where currency = '" + currency + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<AccountCurrency> nquery = session.createNativeQuery(query, AccountCurrency.class);
            List<AccountCurrency> foundAccountCurrencys = nquery.getResultList();
            if (foundAccountCurrencys.isEmpty()) {
                return result;
            } else {
                result = foundAccountCurrencys.get(0);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<AccountCurrency> findAllByCurrency (String currency) {
        List<AccountCurrency> result = null;
        String query = "select * from account_currency where currency = '" + currency + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<AccountCurrency> nquery = session.createNativeQuery(query, AccountCurrency.class);
            List<AccountCurrency> foundAccountCurrencys = nquery.getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public AccountCurrency findById(Long accountCurrencyId) {
        AccountCurrency result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(AccountCurrency.class, accountCurrencyId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public AccountCurrency update(Long accountCurrencyId, AccountCurrency accountCurrencyDetails) {
        AccountCurrency result = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            AccountCurrency accountCurrencyToBeUpdated = session.find(AccountCurrency.class, accountCurrencyId);

            transaction = session.beginTransaction();

            accountCurrencyToBeUpdated.setCurrency(accountCurrencyDetails.getCurrency());

            session.update(accountCurrencyToBeUpdated);

            transaction.commit();
            result = session.find(AccountCurrency.class, accountCurrencyId);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            AccountCurrency accountCurrencyToBeDeleted = session.find(AccountCurrency.class, id);

            transaction = session.beginTransaction();

            session.delete(accountCurrencyToBeDeleted);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

}
