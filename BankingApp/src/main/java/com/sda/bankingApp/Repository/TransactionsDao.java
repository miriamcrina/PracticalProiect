package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Entities.Transactions;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class TransactionsDao {

    private static final Logger logger = Logger.getLogger(TransactionsDao.class.getName());
    GenericRepo<Transactions> genericRepo = new GenericRepo<>();

    public void create(Transactions transactions) {
        genericRepo.create(transactions);
    }

    public Transactions findById(Long transactionsId) {
        Transactions result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(Transactions.class, transactionsId);
        } catch (HibernateException e) {
           logger.warning(e.getMessage());
        }
        return result;
    }

    public Transactions findByIdReceiver(Long idReceiver) {
        Transactions result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(Transactions.class, idReceiver);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
        return result;
    }

    public List<Transactions> findByTransactionDate(LocalDate transactionDate) {
        List<Transactions> result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "select * from transactions where transaction_date = '" + transactionDate + "'";
                NativeQuery<Transactions> nquery = session.createNativeQuery(query, Transactions.class);
                result = nquery.getResultList();
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
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
            logger.warning(e.getMessage());
        }
        return result;
    }

    public void delete(Long id) {
        Transactions transaction = new Transactions();
        genericRepo.delete(id, transaction);
    }

}
