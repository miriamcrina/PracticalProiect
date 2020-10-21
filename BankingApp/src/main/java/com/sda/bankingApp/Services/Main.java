package com.sda.bankingApp.Services;

import com.sda.bankingApp.Repository.CustomerDao;
import com.sda.bankingApp.Services.Dashboard.DebitAccount;
import com.sda.bankingApp.Services.Menus.WelcomeMenu;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
         final Logger logger = Logger.getLogger(DebitAccount.class.getName());


         WelcomeMenu welcomeMenu = new WelcomeMenu();
         welcomeMenu.WelcomeMenu();

//        Register register = new Register();
//
//        register.register();

//
//        LogIn logIn = new LogIn();
//        Customer c = logIn.loginCheck();

//
//        LogInMenu showMenu = new LogInMenu();
//        showMenu.showMenu(c);

//        Portfolio portfolio = new Portfolio();
//        portfolio.viewPortfolio(c);

//        DebitAccount debitAccount = new DebitAccount();
//        debitAccount.createDebitAccount(c);

//         Deposit deposit = new Deposit();
//         deposit.depositMoney(c);
////
//        Transfer transfer = new Transfer();
//        transfer.transferMoney(c);

//

//        HibernateUtil.getSessionFactory();
    }
}
