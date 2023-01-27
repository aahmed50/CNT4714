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
	
	//text boxes
	private JTextField jtfitemID = new JTextField();
	private JTextField jtfQuantity = new JTextField();
	private JTextField jtfItemDetails = new JTextField(); //ItemInfo
	private JTextField jtfTotalItems = new JTextField();
	
	//buttons
	private JButton jbtProcessItem = new JButton("Process Item #1");
	private JButton jbtConfirmItem = new JButton("Confirm Item #1");
	private JButton jbtViewOrder = new JButton("View Order");
	private JButton jbtFinishOrder = new JButton("Finish Order");
	private JButton jbtNewOrder = new JButton("New Order");
	private JButton jbtExit = new JButton("Exit");

	//Jlabels
	JLabel jlbItemID = new JLabel("Enter Item ID for Item #1");
	JLabel jlbQuantity = new JLabel("Enter Quantity for Item #1");
	JLabel jlbItemDetails= new JLabel("Enter Item Details for Item #1");
	JLabel jlbTotalItems = new JLabel("Enter Subtotal for Item #1"); //jlbSubtotal

	public NileDotCom() throws FileNotFoundException{
		this.getInventoryFromFile(); //init inventory
		
		//create JPanel p1 5x2 grid
		JPanel p1 = new JPanel(new GridLayout(5, 2));
		
		//JLabels & JTextField Boxes
		p1.add(jlbItemID);
		p1.add(jtfitemID);
		
		p1.add(jlbQuantity);
		p1.add(jtfQuantity);
		
		p1.add(jlbItemDetails);
		p1.add(jtfItemDetails);
		
		p1.add(jlbTotalItems);
		p1.add(jtfTotalItems);
		
		//create JPanel p2 
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		
		//JButtons
		p2.add(jbtProcessItem);
		p2.add(jbtConfirmItem);
		p2.add(jbtViewOrder);
		p2.add(jbtFinishOrder);
		p2.add(jbtNewOrder);
		p2.add(jbtExit);
		
			//disable default behaviour
			this.jbtConfirmItem.setEnabled(false);
			this.jbtViewOrder.setEnabled(false);
			this.jbtFinishOrder.setEnabled(false);
			//this.jbtNewOrder.setEnabled(false);
			this.jtfTotalItems.setEnabled(false);
			this.jtfItemDetails.setEditable(false);
			
		//design
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		
		//button actionListeners 
	}
	
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