package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.CustomerDao;

import java.util.Scanner;
import java.util.logging.Logger;

public class LogInSecond {
    private static final Logger logger = Logger.getLogger(LogIn.class.getName());


    CustomerDao customerDao = new CustomerDao();
    Customer customerToValidate;
    boolean login = false;
    int logcounter = 0;

    public Customer loginCheck(){
     do {
        logger.info("Please Enter Username:");
        Scanner userscanner = new Scanner(System.in);
        String username = userscanner.nextLine();
        customerToValidate = customerDao.findByUsername(username);

        if ("".equals(username)) {
            logger.warning("Username field cannot be empty");
            login = false;

        } else if (customerToValidate == null) {
            logger.warning("User not found");
            logcounter++;
            logger.warning(3 - (logcounter) + " login attempts remaining");
            login = false;
        } else {
            login = false;
            logger.info("Please Enter Password:");
            Scanner passscanner = new Scanner(System.in);
            String password = passscanner.nextLine();

            if ("".equals(password)) {
                logger.warning("This field cannot be empty");
                login = false;
            } else if (customerToValidate != null && !password.equals(customerToValidate.getPassword())) {
                logger.warning("Wrong password! Please try again");
                logcounter++;
                logger.warning(3 - (logcounter) + " login attempts remaining");
                login = false;
            } else {
                logger.info("LOGIN SUCCESSFUL");
                login = true;
                return customerToValidate;
            }

        }
    } while (logcounter < 3 && !login);


return null;
}
}
