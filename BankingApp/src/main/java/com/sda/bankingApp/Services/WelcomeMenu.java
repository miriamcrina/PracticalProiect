package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class WelcomeMenu {
    private static final Logger logger = Logger.getLogger(WelcomeMenu.class.getName());


    public void WelcomeMenu() {
        Register register = new Register();
        LogIn logIn = new LogIn();
        Customer customer;
        ShowMenu showMenu = new ShowMenu();

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
            System.out.println("*********************************************************");
            System.out.println("Select your option");
            System.out.println("*********************************************************");
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
                    Customer c = logIn.loginCheck();
                    showMenu.showMenu(c);
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
        System.out.println("Good bye!");
    }

}
