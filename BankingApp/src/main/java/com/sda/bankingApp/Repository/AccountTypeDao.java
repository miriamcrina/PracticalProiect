package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.AccountType;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AccountTypeDao {

    public void create(AccountType accountType) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(accountType);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public AccountType findByAccountType (String accountType) {
        AccountType result = null;
        String query = "select * from account_type where acc_type = '" + accountType + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<AccountType> nquery = session.createNativeQuery(query, AccountType.class);
            List<AccountType> foundAccountTypes = nquery.getResultList();
            if (foundAccountTypes.isEmpty()) {
                return result;
            } else {
                result = foundAccountTypes.get(0);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<AccountType> findAllByAccountType (String accountType) {
        List<AccountType> result = null;
        String query = "select * from account_type where acc_type = '" + accountType + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<AccountType> nquery = session.createNativeQuery(query, AccountType.class);
            List<AccountType> foundAccountTypes = nquery.getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public AccountType findById(Long accountTypeId) {
        AccountType result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(AccountType.class, accountTypeId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public AccountType update(Long accountTypeId, AccountType accountTypeDetails) {
        AccountType result = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            AccountType accountTypeToBeUpdated = session.find(AccountType.class, accountTypeId);

            transaction = session.beginTransaction();

            accountTypeToBeUpdated.setAccountType(accountTypeDetails.getAccountType());

            session.update(accountTypeToBeUpdated);

            transaction.commit();
            result = session.find(AccountType.class, accountTypeId);
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
            AccountType accountTypeToBeDeleted = session.find(AccountType.class, id);

            transaction = session.beginTransaction();

            session.delete(accountTypeToBeDeleted);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
