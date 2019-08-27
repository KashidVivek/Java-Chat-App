package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
 
public class MysqlCon {
	public Connection conn;
	MysqlCon sqlobj;
	
  public MysqlCon(){
        String databaseURL = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "vivek";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } 
    }
  public void addUser(String u , String p){
	  try
	    {
		  
	      Statement st = conn.createStatement();
	      String query = "INSERT INTO users " + "VALUES(?,?)";
	      java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
	     preparedStmt.setString(1, u);
	     preparedStmt.setString(2,p);
	     preparedStmt.execute();
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	  }
  
  public boolean checkuser(String u , String p) {
	  Statement stmt;
	  
	try {
		stmt = conn.createStatement();
	  String sql = "SELECT user,pass FROM users";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
          String username = rs.getString("user");
          String password =  rs.getString("pass");
             if ((u.equals(username)) && (p.equals(password))) {
                return true; 
          }else {

           
          }
  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JOptionPane.showMessageDialog(null, "Please Check Username and Password ");
    return false; 
  }
//  public static void main(String[] args) {
//	MysqlCon obj = new MysqlCon();
//	try {
//		obj.addUser("asd", "qwe");
//	} catch (Exception e) {
//		// TODO: handle exception
//	}
//	
//}
}

  




