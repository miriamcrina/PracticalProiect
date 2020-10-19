package com.sda.bankingApp.Services;

import com.sda.bankingApp.Entities.Customer;
import com.sda.bankingApp.Entities.Transactions;

import java.util.Scanner;
import java.util.logging.Logger;

public class ShowMenu {

    private static final Logger logger = Logger.getLogger(ShowMenu.class.getName());

    public void showMenu(Customer customer) {
        Portfolio portfolio = new Portfolio();
        Transfer transfer = new Transfer();
        Deposit deposit = new Deposit();
        DebitAccount debitAccount = new DebitAccount();
        CreditAccount creditAccount = new CreditAccount();

        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        logger.info ("Welcome " + customer.getUsername());


        logger.info ("               +----------------------------------------+\n"
                + "               |    BANKING SYSTEM CONTROL PANEL        | \n"
                + "+--------------------------------------------------------------------------+\n"
                + "| (1) View portfolio and balance                                            |\n"
                + "| (2) Transfer money                                                        |\n"
                + "| (3) Deposit money                                                         |\n"
                + "| (4) Create debit account                                                  |\n"
                + "| (5) Create credit account                                                 |\n"
                + "| (6) Exit                                                                  |\n"
                + "+--------------------------------------------------------------------------+");

        do {
            System.out.println("*********************************************************");
            System.out.println("Select your option");
            System.out.println("*********************************************************");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    logger.info("**********************");
                    portfolio.viewPortfolio(customer);
                    logger.info("**********************");
                    System.out.println();
                    break;
                case 2:
                    logger.info("**********************");
                    transfer.transferMoney(customer);
                    logger.info("**********************");
                    break;
                case 3:
                    logger.info("**********************");
                    deposit.depositMoney(customer);
                    logger.info("**********************");
                    System.out.println();
                    break;
                case 4:
                    logger.info("**********************");
                    debitAccount.createDebitAccount(customer);
                    logger.info("**********************");
                    System.out.println();
                    break;
                case 5:
                    logger.info("**********************");
                    creditAccount.createCreditAccount(customer);
                    logger.info("**********************");
                    break;
                case 6:
                    logger.info("**********************");
                    break;
                default:
                    logger.info("**********************");
                    logger.info("Invalid choice. Select one of the above options.");
                    logger.info("**********************");
                    System.out.println();
                    break;
            }

        }while(choice != 6);
        System.out.println("Thank you for using our services. Good bye!");
    }
}
