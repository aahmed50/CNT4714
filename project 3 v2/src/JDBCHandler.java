/* 
  Name: Asma Ahmed
  Course: CNT 4714 Spring 2023 
  Assignment title: Project 3 – A Two-tier Client-Server Application 
  Date:  March 9, 2023 
 
  Class:  JDBCHandler - main database connection
*/ 

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.AuthenticationException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class JDBCHandler {
		private Connection connection;

		//operations log
		private Connection opsConn;
		private final String operationPropertiesFilename = ".operations.properties";
		private final String opsQueryColumnName = "num_queries";
		private final String opsUpdateColumnName = "num_updates";

		//directory
		private String propertiesDirectory;

		public JDBCHandler(String propertiesDirectory) {
			this.propertiesDirectory = propertiesDirectory;
		} //close constructor

		//returns URL if connected to database
		public String connect(String propertiesFile, String username, String password) throws IOException, SQLException, AuthenticationException {

		//--------------------------Main Database---------------------------------//		

			//get selected properties file
			FileInputStream filein = new FileInputStream(this.propertiesDirectory + "/" + propertiesFile);
			Properties properties = new Properties();
			properties.load(filein);

			//read files
			String url = properties.getProperty("MYSQL_DB_URL");
			String user = properties.getProperty("MYSQL_DB_USERNAME");
			String pass = properties.getProperty("MYSQL_DB_PASSWORD");

			//validate UserName and Password
			if (!user.equals(username) || !pass.equals(password)) {
				throw new AuthenticationException();
			} //close if

			//initialize dataSource
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setURL(url);
			dataSource.setUser(user);
			dataSource.setPassword(pass);
			
			//establish a connection to the dataSource - the database
			this.connection = dataSource.getConnection();


			//--------------------------Operations Log---------------------------------//		

			//get selected properties file
			properties.load(new FileInputStream(this.propertiesDirectory + "/" + operationPropertiesFilename));

			MysqlDataSource opsSource = new MysqlDataSource();
			opsSource.setURL(properties.getProperty("MYSQL_DB_URL"));
			opsSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
			opsSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));

			this.opsConn = opsSource.getConnection();

			return url;

		} //close string connect

//-------------------------- Vector ---------------------------------//		
		//return result of query as vectors
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Vector[] executeQuery(String query) throws SQLException, NullPointerException {

			Statement stmt = this.connection.createStatement(); //execute query
			ResultSet rs = stmt.executeQuery(query);

			ResultSetMetaData metaData = rs.getMetaData(); //results
			int columnCount = metaData.getColumnCount();
			Vector columnNames = new Vector();
			Vector data = new Vector();

			for (int i = 1; i <= columnCount; i++) {
				columnNames.addElement(metaData.getColumnName(i));
			} //close loop

			while (rs.next()) {
				Vector row = new Vector();
				for (int i = 1; i <= columnCount; i++) {
					row.addElement(rs.getObject(i));
				}//close for loop
				data.addElement(row);
			}//close while loop

			Vector[] results = { columnNames, data };

			rs.close();
			stmt.close();

			// Update the OperationsLog database
			updateOperationsLog(opsQueryColumnName);
			return results;
		}//close vector

//-------------------------- Execute Update ---------------------------------//		
		//returns number of rows affected by an update
		public int executeUpdate(String update) throws SQLException, NullPointerException {

			Statement stmt = this.connection.createStatement();
			int rowsAffected = stmt.executeUpdate(update);

			//update operations log database
			updateOperationsLog(opsUpdateColumnName);
			return rowsAffected;
		} //close executeUpdate

//-------------------------- Close Connection ---------------------------------//		
		public void close() {
			//check if connections are open
			try {
				if (this.connection != null) {
					this.connection.close();
					this.connection = null;
				}//close if
				
				if (this.opsConn != null) {
					this.opsConn.close();
					this.opsConn = null;
				}//close if

			} catch (Exception e) {
				e.printStackTrace();
			}//close catch
		}//close close

	//--------------------------Update Operations Log ---------------------------------//		
		private void updateOperationsLog(String columnName) throws SQLException, NullPointerException {

			String command = String.format("UPDATE operationscount SET %s = %s + 1 LIMIT 1", columnName, columnName); //Increment by one
			this.opsConn.createStatement().executeUpdate(command);

		} //close updateOperationsLog
} //close Handler
