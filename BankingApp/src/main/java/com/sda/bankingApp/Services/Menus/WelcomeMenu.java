package com.sda.bankingApp.Services.Menus;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Services.Validation.LogIn;
import com.sda.bankingApp.Services.Validation.Register;


import java.util.Scanner;
import java.util.logging.Logger;

public class WelcomeMenu {
    private static final Logger logger = Logger.getLogger(WelcomeMenu.class.getName());


    public void WelcomeMenu() {
        Register register = new Register();
        LogIn logIn = new LogIn();
        Customer customer;
        LogInMenu logInMenu = new LogInMenu();

        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        logger.info ("Welcome!");


        logger.info ("       +----------------------------------------+\n"
                + "               |          WELCOME TO BANK PLUS!          | \n"
                + "+--------------------------------------------------------------------------+\n"
                + "| (1) Register                                                              |\n"
                + "| (2) Log in                                                                |\n"
                + "| (3) Exit                                                                  |\n"
                + "+--------------------------------------------------------------------------+");


        do {
            logger.info("*********************************************************");
            logger.info("Select your option");
            logger.info("*********************************************************");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    logger.info("**********************");
                    register.register();
                    logger.info("**********************");
                    System.out.println();
                    break;
                case 2:
                    logger.info("**********************");
                    customer = logIn.loginCheck();
                    logInMenu.showMenu(customer);
                    logger.info("**********************");
                    break;
                case 3:
                    logger.info("**********************");
                    break;
                default:
                    logger.info("**********************");
                    logger.info("Invalid choice. Select one of the above options.");
                    logger.info("**********************");
                    System.out.println();
                    break;
            }

        }while(choice != 3);
        logger.info("Good bye!");
    }

}
