/* 
  Name: Asma Ahmed
  Course: CNT 4714 Spring 2023 
  Assignment title: Project 3 – A Two-tier Client-Server Application 
  Date:  March 9, 2023 
 
  Class:  Interface
*/ 

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource; 

public class Interface {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		
		//CURRENTLY BUILDING OFF OF SimpleJDBC_PropertiesFile.java -- connected to project 3
		//fork properties file MAKE THIS A SEPARATE FILE into connect button in GUI

		//using a properties file  
	    Properties properties = new Properties();
	    FileInputStream filein = null;
	    MysqlDataSource dataSource = null;
	    
	    //read a properties file
	    try {
	     filein = new FileInputStream("db.properties");
	     properties.load(filein);
	     dataSource = new MysqlDataSource();
	     dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
	     dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
	     dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));  
	    } catch (IOException e) {
	     e.printStackTrace();
	    } //close catch
	    
	    System.out.println("Output from SimpleJDBC_PropertiesFile:   Using a properties file to hold connection details.");
	    java.util.Date date = new java.util.Date();
	    System.out.println(date);  System.out.println(); 
	    
	  //establish a connection to the dataSource - the database
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3312/project3", "root", "password"); //this is from static load
	    //Connection connection = dataSource.getConnection("jdbc:mysql://localhost:3312/project3", "root"); //IDK what the difference is YET - this is from properties

	        System.out.println("Database connected");
	        DatabaseMetaData dbMetaData = connection.getMetaData();
	    System.out.println("JDBC Driver name " + dbMetaData.getDriverName() );
	    System.out.println("JDBC Driver version " + dbMetaData.getDriverVersion());
	    System.out.println("Driver Major version " +dbMetaData.getDriverMajorVersion());
	    System.out.println("Driver Minor version " +dbMetaData.getDriverMinorVersion() );
	    System.out.println();
	        // Create a statement
	    Statement statement = connection.createStatement();
	        // Execute a statement
	        ResultSet resultSet = statement.executeQuery ("select bikename,cost from bikes");
	        
	        // Iterate through the result set and print the returned results
	        System.out.println("Results of the Query: . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n");
	        while (resultSet.next())
	          System.out.println(resultSet.getString("bikename") + "         \t" + resultSet.getString("cost") + "         \t");
	    //the following print statement works exactly the same  
	          //System.out.println(resultSet.getString(1) + "         \t" +
	          //  resultSet.getString(2) + "         \t" + resultSet.getString(3));
	        System.out.println();  System.out.println();
	        // Close the connection
	        connection.close();
		
	}//close main
} //close Interface
