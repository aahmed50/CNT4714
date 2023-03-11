/* 
  Name: Asma Ahmed
  Course: CNT 4714 Spring 2023 
  Assignment title: Project 3 – A Two-tier Client-Server Application 
  Date:  March 9, 2023 
 
  Class:  Main Driver
*/ 

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {

		String propertiesDirectory = System.getProperty("user.dir") + "/properties";
		System.out.println(propertiesDirectory);

		int width = 750, height = 500; //Interface size

		//initialize JDBCHandler class
		JDBCHandler handler = new JDBCHandler(propertiesDirectory);
		
		//initialize drop down menus 
		//String[] PropertiesItems = {"db.properties", "client.properties"};
		String[] propertiesItems = getPropertiesItemsnames(propertiesDirectory);

		//initialize Interface 
		Interface frame = new Interface (handler, width, height, propertiesItems);

		frame.setTitle("SQL Two-tier Client APP | Spring 2023");
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.getContentPane().requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//windowListener
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				handler.close();
				System.exit(0);
			} //close windowClosing
		}); //close WindowListener
		
		ImageIcon image = new ImageIcon("logo-white.png"); //create icon
		frame.setIconImage(image.getImage()); //change icon of frame
	} //close main

	public static String[] getPropertiesItemsnames(String directory) {

		ArrayList<String> filenames = new ArrayList<>();

		//IO
		File folder = new File(directory);
		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".properties") && !file.getName().startsWith(".")) {
				String filename = file.getName();
				filenames.add(filename);
			}//close if
		}//close for loop

		String[] propertiesArray = new String[filenames.size()];
		return filenames.toArray(propertiesArray);
	}//close get
} //close main class
