package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.AccountType;
import com.sda.bankingApp.Entities.Transactions;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionsDao {

    public void create(Transactions transactions) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(transactions);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Transactions findById(Long transactionsId) {
        Transactions result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(Transactions.class, transactionsId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Transactions findByIdReceiver(Long idReceiver) {
        Transactions result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(Transactions.class, idReceiver);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Transactions> findByTransactionDate(LocalDate transactionDate) {
        List<Transactions> result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "select * from transactions where transaction_date = '" + transactionDate + "'";
                NativeQuery<AccountType> nquery = session.createNativeQuery(query, AccountType.class);
                List<AccountType> foundAccountTypes = nquery.getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;

    }

    public Transactions update(Long transactionsId, Transactions transactionsDetails) {
        Transactions result = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transactions transactionsToBeUpdated = session.find(Transactions.class, transactionsId);

            transaction = session.beginTransaction();

            transactionsToBeUpdated.setDescription(transactionsDetails.getDescription());
            transactionsToBeUpdated.setAmountSent(transactionsDetails.getAmountSent());
            transactionsToBeUpdated.setTransactionDate(transactionsDetails.getTransactionDate());

            session.update(transactionsToBeUpdated);

            transaction.commit();
            result = session.find(Transactions.class, transactionsId);
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
            Transactions transactionsToBeDeleted = session.find(Transactions.class, id);

            transaction = session.beginTransaction();

            session.delete(transactionsToBeDeleted);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

}
