package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {

    public void create(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
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
            System.out.println(e.getMessage());
        }
        return result;
    }


    public Customer findById(Long customerId) {
        Customer result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           result = session.find(Customer.class, customerId);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Customer findByPassword(String password) {
        Customer result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.find(Customer.class, password);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;

    }

    public long findIdByUsername(String username) {
        long result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String query = "select customer_id from customer where username =\"" + username + "\"";
            NativeQuery<Customer> nativeQuery = session.createNativeQuery(query);
            result = nativeQuery.getFirstResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public long findIdByPassword(String password) {
        long result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String query = "select customer_id from customer where username =\"" + password + "\"";
            NativeQuery<Customer> nativeQuery = session.createNativeQuery(query);
            result = nativeQuery.getFirstResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Customer update(int customerId, Customer customerDetails) {
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
            System.out.println(e.getMessage());
        }
        return result;
    }

}
