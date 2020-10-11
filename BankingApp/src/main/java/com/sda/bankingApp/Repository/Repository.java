package com.sda.bankingApp.Repository;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.util.logging.Logger;

public class Repository {

    private static final Logger logger = Logger.getLogger(Repository.class.getName());

    public void register() {


        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();
             Scanner scanner = new Scanner(System.in)) {

            transaction = session.beginTransaction();
            //id
            logger.info("Introduce your credentials:");
            logger.info("First name: " );
            String firstName = scanner.nextLine();

            if (firstName == null || firstName.isEmpty()) {
                logger.warning("This field should not be empty!\n");

            } else if (!firstName.matches("^[a-zA-Z]+$")) {
                logger.warning("This field is invalid\n");

            }

            logger.info("Last name: " );
            String lastName = scanner.nextLine();

            if (lastName == null || lastName.isEmpty()) {
                logger.warning("This field should not be empty!\n");

            } else if (!lastName.matches("^[a-zA-Z]+$")) {
                logger.warning("This field is invalid\n");

            }

            logger.info("Social Security Number: " );
            String ssn = scanner.nextLine();
            if (ssn == null || ssn.isEmpty()) {
                logger.warning("This field should not be empty!\n");

            } else if (ssn.matches("^[0-9]+$")) {
                if (!(ssn.length() == 13)) {
                    logger.warning("Social Security Number should be 13 digits.\n");

                }

            } else if (!ssn.matches("^[0-9]+$")) {
                logger.warning("Invalid Social Security Number");

            }


            logger.info("Email address: " );
            String email = scanner.nextLine();
            if (email == null || email.isEmpty()) {
                logger.warning("This field should not be empty!\n");

            } else if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
                logger.warning("This field is invalid\n");

            }

            logger.info("Username: " );
            String username = scanner.nextLine();

            if (username == null || username.isEmpty()) {
                logger.warning("This field should not be empty!\n");

            } else if (!username.matches("^[a-zA-Z0-9]+$")) {
                logger.warning("This field is invalid\n");

            }


            logger.info("Password - should contain at least one capital letter and one digit: " );
            String password = scanner.nextLine();
            if (password == null || password.isEmpty()) {
                logger.warning("This field should not be empty!\n");

            } else if (password.matches("^[a-zA-Z0-9]+$")) {
                if ((password.length() < 6)) {
                    logger.warning("Password is too short. It should contain at least 6 characters.\n");

                }

            } else if (!password.matches("^[0-9]+$")) {
                logger.warning("Invalid Password");
            }


           Customer customer = new Customer(firstName, lastName, ssn, email, username, password);

            session.save(customer);
            transaction.commit();
            logger.info("Successful registration");
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }
}
