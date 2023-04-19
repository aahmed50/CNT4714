/* 
  Name: Asma Ahmed
  Course: CNT 4714 – Spring 2023 – Project Four
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: April 23, 2023
 
  Class:  authenticationServlet 
*/ 

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import javax.swing.JOptionPane;


public class authenticationServlet extends HttpServlet{

	//process get request
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String inBoundUsername = request.getParameter("username"); //in bound user name from front end
		String inBoundPassword = request.getParameter("password"); //in bound password from front end

	//credentials
	File credentialsFile = new File ("credentials.txt");
	
	//FileReader
	
	//BufferedReader
	
	//ScannerObjects
	
	try {
		//open credentials file
		//read first line from credential files
		
		while(credentials != null && !userCredentialsOK) {
			if (userCredentialsOK) {
				response.sendRedirect("/Project 4/clientHome.html");
			}
			}//close while
		//if do not match read next line in file
		//}
	}//end try 
	
	catch(FileNotFoundException fileNotFoundException) {
		JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
	} //end catch
	catch(IOException IOException) {
		JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
	}//end catch 
	
	if(userCredentialsOK) {
		//redirect
		response.sendRedirect( "/clientHome" );
	}//close if
	else {
		//redirect to error page
		response.sendRedirect( "/errorpage" );
	}//end else
		
	}//end doGet() method
}//end authenticationServlet
