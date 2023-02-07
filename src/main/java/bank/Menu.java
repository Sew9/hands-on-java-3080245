package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args){
    System.out.print("Welcome to Globe Bank International");

    Menu menu = new Menu();

    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId())
      menu.showMemu(customer,account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.print("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    try{
    customer = Authentictor.login(username, password);
    } catch(LoginException e) {
        System.out.println("There was an error: " + e.getMessage());
    }
     return customer;
  }


  private void showMemu(Customer customer, Account account){ 

    int selection = 0;

    while(slection != 4 && customer.isAuthenticated()){
      System.out.println("==============================================");
      System.out.println("please select one of the following options: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("================================================");

      selection = scanner.nextInt();
      double amount;

      switch(selection){
        case 1: System.out.println("How much would you like to deposit?"); 
                amount = scanner.nextDouble();
                account.deposit(amount);
                break;
        case 2: System.out.println("How much would you like to withdraw?"); 
                amount = scanner.nextDouble();
                account.withdraw(amount);
                break;
        case 3: System.out.println("Current balamce: " + account.getBalance()); 
                break;
        case 4: Authentictor.logout(customer);
                System.out.println("Thanks for banking at Globe Bamk International!"); 
                account.deposit(amount);
                break;
        default:
                System.out.println("Invalid option. Please try again"); 
                break;
      }
    }
  }
}
