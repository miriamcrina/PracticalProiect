package com.sda.bankingApp.Services;

import com.sda.bankingApp.Repository.CustomerDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class LogInSecond {
Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Register.class.getName());


    public void loginCheck() {
    boolean userInput = false;

        do {
            logger.info("Enter your username");
            String username = scanner.nextLine();
            logger.info("Enter your password");
            String password = scanner.nextLine();
            Map<String, String> logInDetails = new HashMap<>();
            logInDetails.put(username, password);
            CustomerDao customerDao = new CustomerDao();
            String dbusername = customerDao.findByUsernameString(username);
            String dbpassword = customerDao.findByPasswordString(password);
            for (Map.Entry<String, String> details : logInDetails.entrySet()){
             String username1 = details.getKey();
            String password1 = details.getValue();
            if ( !username1.equals(dbusername)){
        logger.warning("Invalid username or password");
        userInput= false;
    } else
        logger.info("Login successful");
        userInput = true;
}
        }while (!userInput);


    }
}
