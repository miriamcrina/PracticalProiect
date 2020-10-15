package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Repository.CustomerDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class LogInThird {
Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(LogInThird.class.getName());


    public Customer loginCheck() {
    boolean userInput = false;
    int logcount = 0;
        Customer customerToValidate;

        do {
            logger.info("Enter your username");
            String username = scanner.nextLine();
            logger.info("Enter your password");
            String password = scanner.nextLine();
            Map<String, String> logInDetails = new HashMap<>();
            logInDetails.put(username, password);
            CustomerDao customerDao = new CustomerDao();
            customerToValidate = customerDao.findByUsername(username);

            for (Map.Entry<String, String> details : logInDetails.entrySet()) {
                String username1 = details.getKey();
                String password1 = details.getValue();
                if ( customerToValidate == null) {
                    logger.warning("Invalid username");
                    userInput = false;
                } else if (!password1.equals(customerToValidate.getPassword())){
                    logger.warning("Invalid password]");
                    logcount++;
                    userInput = false;
                }  else {
                    logger.info("Login successful");
                    userInput = true;
                    return customerToValidate;
                }

            }


        }while (!userInput && logcount <3) ;

        return null;
}

}
