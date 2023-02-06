package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
    
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("we're connected");
    }catch(SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String username){

    //? is use to replace the raw data to protect agains malicious input 
    String sql = "select * from customers where username = ?"; 

    Customer customer = null;
    try(Connection connection = connect(); 
        PreparedStatement statement = connection.prepareStatement(sql)){
         
        statement.setString(1, username);
         try(ResultSet resultSet = statement.executeQuery()){
           customer = new Customer( 
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
         } 

    }catch(SQLException e){
      e.printStackTrace();
    }

    return customer;
  }

  public static Account getAccount(int accountId) {

    String sql = "select * from account where id = ?";
    Account accouter = null;
    try(Connection connection = connect();
    PreparedStatement statement = connection.prepareStatement(sql)){
      
      statement.setInt(1, accountId);
      try(ResultSet resultSet = statement.executeQuery()){
        accouter = new Account(
          resultSet.getInt("id"),
          resultSet.getString("type"),
          resultSet.getInt("balance"));
      }

    }catch(SQLException e){
      e.printStackTrace();
    }

    return accouter;
  }

  public static void main(String[] args) {

   Customer customer = getCustomer(
   "tWest80@friendfee.com");
   System.out.println(customer.getName()); 

   Account accouter = getAccount(57187);
   System.out.println(accouter.getBalance());
  
  }
}
