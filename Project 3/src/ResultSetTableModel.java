/* 
  Name: Asma Ahmed
  Course: CNT 4714 Spring 2023 
  Assignment title: Project 3 – A Two-tier Client-Server Application 
  Date:  March 9, 2023 
 
  Class:  ResultSetTableModel -- adds ResultSet data to JTable
*/ 

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLSyntaxErrorException;
import javax.swing.JOptionPane;

public class ResultSetTableModel extends AbstractTableModel{

	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;

	// keep track of database connection status
	private boolean connectedToDatabase = false;
	   
	// constructor initializes resultSet and obtains its meta data object;
	// determines number of rows
	public ResultSetTableModel ( MysqlDataSource dataSource, Connection connection, String query ) throws SQLException, ClassNotFoundException {
		try {
	       connection = dataSource.getConnection();
	       statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	       connectedToDatabase = true;

	      // set query and execute it
//	       if (query.substring(0, query.indexOf(' ')).equals("select"))
//	       {
//	        setQuery(query);
//	       } else {
//	        setUpdate(query);
//	       }

		  } //end try
	      catch ( SQLException sqlException ) 
	      {
	         sqlException.printStackTrace();
	         System.exit( 1 );
	      } // end catch
//	      catch (IOException e) {
//	   	     e.printStackTrace();
//	      }  
	   } // end constructor ResultSetTableModel

	   // get class that represents column type
	   public Class getColumnClass( int column ) throws IllegalStateException
	   {
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );

	      // determine Java class of column
	      try 
	      {
	         String className = metaData.getColumnClassName( column + 1 );
	         
	         // return Class object that represents className
	         return Class.forName( className );
	      } // end try
	      catch ( Exception exception ) 
	      {
	         exception.printStackTrace();
	      } // end catch
	      
	      return Object.class; // if problems occur above, assume type Object
	   } // end method getColumnClass

	   // get number of columns in ResultSet
	   public int getColumnCount() throws IllegalStateException
	   {   
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );

	      // determine number of columns
	      try 
	      {
	         return metaData.getColumnCount(); 
	      } // end try
	      catch ( SQLException sqlException ) 
	      {
	         sqlException.printStackTrace();
	      } // end catch
	      
	      return 0; // if problems occur above, return 0 for number of columns
	   } // end method getColumnCount

	   // get name of a particular column in ResultSet
	   public String getColumnName( int column ) throws IllegalStateException
	   {    
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );

	      // determine column name
	      try 
	      {
	         return metaData.getColumnName( column + 1 );  
	      } // end try
	      catch ( SQLException sqlException ) 
	      {
	         sqlException.printStackTrace();
	      } // end catch
	      
	      return ""; // if problems, return empty string for column name
	   } // end method getColumnName

	   // return number of rows in ResultSet
	   public int getRowCount() throws IllegalStateException
	   {      
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );
	 
	      return numberOfRows;
	   } // end method getRowCount

	   // obtain value in particular row and column
	   public Object getValueAt( int row, int column ) 
	      throws IllegalStateException
	   {
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );

	      // obtain a value at specified ResultSet row and column
	      try 
	      {
			   resultSet.next();  /* fixes a bug in MySQL/Java with date format */
	         resultSet.absolute( row + 1 );
	         return resultSet.getObject( column + 1 );
	      } // end try
	      catch ( SQLException sqlException ) 
	      {
	         sqlException.printStackTrace();
	      } // end catch
	      
	      return ""; // if problems, return empty string object
	   } // end method getValueAt
	   
	   // set new database query string
	   public void setQuery( String query ) 
	      throws SQLException, IllegalStateException 
	   {
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );
	      
	      
	      try {
	      // specify query and execute it
	      resultSet = statement.executeQuery( query );

	      // obtain meta data for ResultSet
	      metaData = resultSet.getMetaData();

	      // determine number of rows in ResultSet
	      resultSet.last();                   // move to last row
	      numberOfRows = resultSet.getRow();  // get row number 
	      } catch (SQLException sql) {
	          sql.printStackTrace();
	          JOptionPane.showMessageDialog(null, sql.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	      }
	      
	      // notify JTable that model has changed
	      fireTableStructureChanged();
	   } // end method setQuery


	// set new database update-query string
	   public void setUpdate( String query ) 
	      throws SQLException, IllegalStateException 
	   {
		  int res;
	      // ensure database connection is available
	      if ( !connectedToDatabase ) 
	         throw new IllegalStateException( "Not Connected to Database" );

	      // specify query and execute it
	      try {
	      res = statement.executeUpdate( query );
	      } catch (SQLSyntaxErrorException sql) {
	          sql.printStackTrace();
	          JOptionPane.showMessageDialog(null, sql.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	      }
	/*    
	      // obtain meta data for ResultSet
	      metaData = resultSet.getMetaData();
	      // determine number of rows in ResultSet
	      resultSet.last();                   // move to last row
	      numberOfRows = resultSet.getRow();  // get row number      
	*/    
	      // notify JTable that model has changed
	      fireTableStructureChanged();
	   } // end method setUpdate

	   // close Statement and Connection               
	//   public void disconnectFromDatabase()            
	//   {              
//	      if ( !connectedToDatabase )                  
//	         return;
//	      // close Statement and Connection            
//	      try                                          
//	      {                                            
//	         statement.close();                        
//	         connection.close();                       
//	      } // end try                                 
//	      catch ( SQLException sqlException )          
//	      {                                            
//	         sqlException.printStackTrace();           
//	      } // end catch                               
//	      finally  // update database connection status
//	      {                                            
//	         connectedToDatabase = false;              
//	      } // end finally                             
	//   } // end method disconnectFromDatabase          
	
} //end ResultSetTableModel
