package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.CustomerDao;

import java.util.Scanner;
import java.util.logging.Logger;

public class LogIn {

    private static final Logger logger = Logger.getLogger(LogIn.class.getName());



    public Customer loginCheck() {

        CustomerDao customerDao = new CustomerDao();
        Customer customerToValidate;
        boolean login = false;
        int logcounter = 0;


    while (logcounter < 3 && login == false) {

        logger.info("Please Enter Username:");
        Scanner userscanner = new Scanner(System.in);
        String username = userscanner.nextLine();
        customerToValidate  = customerDao.findByUsername(username);

        if ("".equals(username)) {
            logger.warning("Username field cannot be empty");
        } else if (customerToValidate == null) {
            logger.warning("User not found");
            logcounter++;
            logger.warning(3 - (logcounter) + " login attempts remaining");
        } else {

            System.out.println("-------------------");

            while (logcounter < 3 && login == false) {
                logger.info("Please Enter Password:");
                Scanner passscanner = new Scanner(System.in);
                String password = passscanner.nextLine();

                if ("".equals(password)) {
                    logger.warning("This field cannot be empty");

                } else if (!password.equals(customerToValidate.getPassword())) {
                    logger.warning("Wrong password! Please try again");
                    logcounter++;
                    logger.warning(3 - (logcounter) + " login attempts remaining");

                } else
                    logger.info("LOGIN SUCCESSFUL");
                login = true;
                break;
            }
            return customerToValidate;

        }
    }

return  null;
}

}
