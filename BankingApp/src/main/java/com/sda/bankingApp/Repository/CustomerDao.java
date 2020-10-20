package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.Accounts;
import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Services.Dashboard.Transfer;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;

public class CustomerDao {
    private static final Logger logger = Logger.getLogger(CustomerDao.class.getName());
    GenericRepo<Customer> genericRepo = new GenericRepo<>();

    public void create(Customer customer) {
       genericRepo.create(customer);
    }

    public Customer findByUsername(String username) {
        Customer result = null;
        String query = "select * from customer where username = '" + username + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Customer> nquery = session.createNativeQuery(query, Customer.class);
            List<Customer> foundCustomers = nquery.getResultList();
            if (foundCustomers.isEmpty()) {
                return result;
            } else {
                result = foundCustomers.get(0);
            }
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
        return result;
    }

    public Customer findById (Long customerId) {
        Customer result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           result = session.find(Customer.class, customerId);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
        return result;
    }

    public Customer update (Long customerId, Customer customerDetails) {
        Customer result = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Customer customerToBeUpdated = session.find(Customer.class, customerId);

            transaction = session.beginTransaction();

            customerToBeUpdated.setFirstName(customerDetails.getFirstName());
            customerToBeUpdated.setLastName(customerDetails.getLastName());
            customerToBeUpdated.setCnp(customerDetails.getCnp());
            customerToBeUpdated.setEmail(customerDetails.getEmail());
            customerToBeUpdated.setUsername(customerDetails.getUsername());
            customerToBeUpdated.setPassword(customerDetails.getPassword());

            session.update(customerToBeUpdated);

            transaction.commit();
            result = session.find(Customer.class, customerId);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.warning(e.getMessage());
        }
        return result;
    }

    public void delete (Long id) {
        Customer customer = new Customer();
        genericRepo.delete(id, customer);
    }


}
