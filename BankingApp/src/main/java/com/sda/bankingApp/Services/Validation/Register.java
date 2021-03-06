package com.sda.bankingApp.Services.Validation;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.CustomerDao;

import java.util.Scanner;
import java.util.logging.Logger;

public class Register {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Register.class.getName());

    public void register() {

        String emptyMessage = "This field should not be empty!\n";
        String invalidMessage = "This field is invalid\n";


        logger.info("Insert your credentials:");


        String firstName = getFirstName( emptyMessage, invalidMessage);
        String lastName = getLastName( emptyMessage, invalidMessage);
        String ssn = getSsn(emptyMessage, invalidMessage);
        String email = getEmail( emptyMessage, invalidMessage);
        String username = getUsername( emptyMessage, invalidMessage);
        String password = getPassword(emptyMessage, invalidMessage);

        Customer customer = new Customer(firstName, lastName, ssn, email, username, password);
        CustomerDao customerDao = new CustomerDao();
        customerDao.create(customer);

        logger.info("User registration successful!");

    }

    private String getLastName( String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String lastName;
        do{
            logger.info("Last name: " );
            lastName = scanner.nextLine();

            if (lastName == null || lastName.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect= false;

            } else if (!lastName.matches("^[a-zA-Z]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect= false;
            } else
                userInputCorrect=true;

        }while(!userInputCorrect);
        return lastName;
    }

    private String getFirstName(String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String firstName = null;
        do{
            logger.info("First name: " );
            firstName=scanner.nextLine();

            if (firstName == null || firstName.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect =false;

            } else if (!firstName.matches("^[a-zA-Z]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect =false;

            }else
                userInputCorrect= true;
        }while (!userInputCorrect);
        return firstName;
    }

    private String getPassword( String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String password;
        do{
            logger.info("Password (for your security it should contain at least one capital letter and one digit): " );
            password = scanner.nextLine();

            if (password == null || password.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect=false;

            } else if (password.matches("^[a-zA-Z0-9]+$") && password.length() < 6) {
                    logger.warning("Password is too short. It should contain at least 6 characters.\n");
                    userInputCorrect=false;

            } else if (!password.matches("[a-zA-Z0-9]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect=false;

            }else
                userInputCorrect= true;

        }while(!userInputCorrect);
        return password;
    }

    private String getUsername( String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String username;
        do{
            logger.info("Username: " );
            username = scanner.nextLine();

            if (username == null || username.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect=false;

            } else if (!username.matches("^[a-zA-Z0-9]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect=false;

            } else if (username.matches("^[a-zA-Z0-9]+$") && username.length() < 5) {
                logger.warning("Username is too short. It should contain at least 5 characters.\n");
                userInputCorrect=false;

            }else
                userInputCorrect=true;

        }while(!userInputCorrect);
        return username;
    }

    private String getEmail(String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String email;
        do{
            logger.info("Email address: " );
            email = scanner.nextLine();

            if (email == null || email.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect=false;

            } else if (!email.matches("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$")) {
                logger.warning(invalidMessage);
                userInputCorrect=false;

            } else
                userInputCorrect= true;

        }while(!userInputCorrect);
        return email;
    }

    private String getSsn( String emptyMessage, String invalidMessage) {
        boolean userInputCorrect;
        String ssn;
        do {
            logger.info("Social Security Number: " );
            ssn = scanner.nextLine();

            if (ssn == null || ssn.isEmpty()) {
                logger.warning(emptyMessage);
                userInputCorrect= false;

            } else if (ssn.matches("^[0-9]+$") && ssn.length() != 13 ) {
                    logger.warning("Social Security Number should be 13 digits.\n");
                    userInputCorrect= false;

            } else if (!ssn.matches("^[0-9]+$")) {
                logger.warning(invalidMessage);
                userInputCorrect= false;

            } else
                userInputCorrect=true;

        } while(!userInputCorrect);
        return ssn;
    }


}
