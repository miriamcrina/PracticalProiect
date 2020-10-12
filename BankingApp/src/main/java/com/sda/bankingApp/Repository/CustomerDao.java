package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {

    public void create(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


//    public Customer findByUsername(String username) {
//        Person result = null;
//
//        try (Session session = getSession()) {
//            String findByUsernameHql = "FROM Person p WHERE p.firstName = :firstName";
//            Query<Person> query = session.createQuery(findByUsernameHql);
//            query.setParameter("firstName", username);
//
//            List<Person> foundPersons = query.getResultList();
//
//            if (foundPersons.isEmpty()) {
//                return result;
//            } else {
//                result = foundPersons.get(0);
//            }
//        } catch (HibernateException e) {
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }

//}
    }