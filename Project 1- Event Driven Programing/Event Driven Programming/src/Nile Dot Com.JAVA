/*  Name:  Asma Ahmed
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.*;
import java.util.*;

public class NileDotCom extends JFrame{
	
	//private String inventoryFile = "inventory.txt";
	private ArrayList<Item> inventory;
	private Order order = new Order();
	
	//main GUI
	public static void main(String[] args) throws FileNotFoundException {
		NileDotCom frame = new NileDotCom();
		
		frame.pack(); //fit to screen
		frame.setLocationRelativeTo(null); //center on screen
		frame.setTitle("Nile Dot Com | Spring 2023"); //frame title
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close window w/o exiting program
		frame.setVisible(true); //display window
	} //close main
}