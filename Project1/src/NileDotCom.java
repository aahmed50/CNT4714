/*  Name:  Asma Ahmed
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 

import javax.swing.*;
import javax.swing.ImageIcon;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;


public class NileDotCom extends JFrame{
	//private String inventoryFile = "inventory.txt";
		private ArrayList<Item> inventory;
		private Order order = new Order();
		
		//text boxes
		private JTextField jtfItemID = new JTextField();
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

		//JLabels
		JLabel jlbItemID = new JLabel("Enter Item ID for Item #1");
		JLabel jlbQuantity = new JLabel("Enter Quantity for Item #1");
		JLabel jlbItemDetails= new JLabel("Enter Item Details for Item #1");
		JLabel jlbSubtotal = new JLabel("Enter Subtotal for Item #1"); //jlbSubtotal

		//-----------------------------------------------------------------------------------//
		
		//constructor
		public NileDotCom() throws FileNotFoundException{
			this.inventoryFromFile(); //initialize inventory
			
			//create JPanel p1 5x2 grid
			JPanel p1 = new JPanel(new GridLayout(5, 2));
			
			//JLabels & JTextField Boxes
			p1.add(jlbItemID);
			p1.add(jtfItemID);
			
			p1.add(jlbQuantity);
			p1.add(jtfQuantity);
			
			p1.add(jlbItemDetails);
			p1.add(jtfItemDetails);
			
			p1.add(jlbSubtotal);
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
			
				//disable default behavior
				this.jbtConfirmItem.setEnabled(false);
				this.jbtViewOrder.setEnabled(false);
				this.jbtFinishOrder.setEnabled(false);
				//this.jbtNewOrder.setEnabled(false);
				this.jtfTotalItems.setEnabled(false);
				this.jtfItemDetails.setEditable(false);
				
			//design - add to frame
			add(p1, BorderLayout.NORTH);
			add(p2, BorderLayout.SOUTH);
			
			//button actionListeners
			//processItem actionListener
			jbtProcessItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//parse .txt file
					int itemID = Integer.parseInt(jtfItemID.getText());
					int itemQuantity = Integer.parseInt(jtfQuantity.getText()); //quantityOfItem
					
					int itemIndex = linearSearch(itemID);
					
					//title = name
					System.out.println(itemIndex);
					if(itemIndex != -1) {
						Item foundItem = inventory.get(itemIndex);
						order.setItemInfo(foundItem.getItemID() + "", foundItem.getName(), foundItem.getPrice() + "", itemQuantity + "", order.getDiscountPercentage(itemQuantity) + "", order.getTotalDiscount(itemQuantity, foundItem.getPrice()) + "");
						String itemInfo = foundItem.getItemID() + foundItem.getName() + " $" + foundItem.getPrice() + " " + itemQuantity + " " + order.getDiscountPercentage(itemQuantity) + "%" + order.getTotalDiscount(itemQuantity, foundItem.getPrice());
						jtfItemDetails.setText(itemInfo);
						jbtConfirmItem.setEnabled(true);
						jbtProcessItem.setEnabled(false);
						order.setOrderSubtotal(itemQuantity, foundItem.getPrice());
						jtfItemDetails.setEnabled(false);
						jtfTotalItems.setEnabled(false);
					}//oof
					else {
						JOptionPane.showMessageDialog(null, "Item ID " + itemID + " is not in the file.");
					}//else
					
				}//close actionPerformed
			});//close processItem actionListener

			//confirmItem button actionListener
			jbtConfirmItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e){
					
					int itemQuantity = Integer.parseInt(jtfQuantity.getText());

					order.setCurrentNumItems(itemQuantity);
					order.setTotalItems(order.getTotalItems() + 1);

					JOptionPane.showMessageDialog(null, "Item #" +  order.getTotalItems() + " accepted." + " Added to your cart.");
					
					// Prepares transaction.txt line
					order.prepareInvoice();

					// Adds item to viewOrder
					order.addToViewOrder(jtfItemDetails.getText());
					
					// Enable Buttons
					jbtProcessItem.setEnabled(true);
					jbtViewOrder.setEnabled(true);
					jbtFinishOrder.setEnabled(true);
					jbtConfirmItem.setEnabled(false);

					// Update Button Text
					jbtProcessItem.setText("Process Item #" + (order.getTotalItems() + 1));
					jbtConfirmItem.setText("Confirm Item #" + (order.getTotalItems() + 1));

					// Update textFields boxes	
					jtfItemID.setText("");
					jtfQuantity.setText("");
					jtfTotalItems.setText("$" + new DecimalFormat("#0.00").format(order.getOrderSubtotal()));

					// Update labels
					jlbSubtotal.setText("Order subtotal for " + order.getCurrentNumItems() + " item(s)");
					jlbItemID.setText("Enter Item ID for Item #" + (order.getTotalItems() + 1) + ":");
					jlbQuantity.setText("Enter quantity for Item #" + (order.getTotalItems() + 1) + ":");
					jlbItemDetails.setText("Details for Item #" + (order.getTotalItems() + 1) + ":");
				}//close actionPerformed
			}); //close confirmItem actionListener
			
			//ViewOrder button actionListener
			jbtViewOrder.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, order.getViewOrder());
				}//close actionPerformed
			}); //ViewOrder actionListener
			
			//FinishOrder button actionListener
			jbtFinishOrder.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e){
					
					try {
						order.printInvoice();
						JOptionPane.showMessageDialog(null, order.getFinishOrder());
					} catch (IOException el){
						el.printStackTrace();
					}//close catch
					
					NileDotCom.super.dispose();
				}//close actionPerformed
			}); //close FinishOrder actionListener
			
			//newOrder button actionListener
			jbtNewOrder.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					NileDotCom.super.dispose();
					try {
						NileDotCom.main(null);
					} catch (FileNotFoundException el) {
						el.printStackTrace();
					}//close catch
				} //close actionPerformed
			}); //close newOrder actionListener
			
			//exit button actionListener
			jbtExit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					NileDotCom.super.dispose();
				} //close action performed
			}); //close exit actionListener
		
		}//close constructor

		//-------------------------------------------------------------------//

		//linearSearch returns integer
		public int linearSearch(int itemID) {
			for(int i=0; i < this.inventory.size(); i++) {
				Item currentItem = inventory.get(i);
				int currentItemID = Integer.parseInt((currentItem.getItemID()));
				if(currentItemID == itemID) {
					return i;
				}//close if
			}//close for loop
			return -1;
		}//close linearSearch
		
		//inventory from file
		public void inventoryFromFile() throws FileNotFoundException {
			
			//creates items into an ArrayList
			this.inventory = new ArrayList<Item>(); 
			File file = new File("inventory.txt"); //open file
			Scanner textFile = new Scanner(file);
			
			//scan inventory into ArrayList
			while(textFile.hasNextLine()) {
				
				//grab next line & parse into strings separated by comma
				String item = textFile.nextLine();
				String[] itemDetails = item.split(",");
				
				//create new Item
				//Each line in this file contains four entries: item id (a string), quoted string description, in stock status (a string), and the unit price (a double)
				Item currentItem = new Item();
				currentItem.setItemID(itemDetails[0]); //string
				currentItem.setName(itemDetails[1]); //string
				currentItem.setStockStatus(itemDetails[2]); //string
				currentItem.setPrice(Double.parseDouble(itemDetails[3])); //double/decimal
				
				//add currentItem into ArrayList
				inventory.add(currentItem);
			}//close while
			textFile.close(); //close scanner
			
			//test
			for(int i = 0 ; i < inventory.size(); i++){
				Item current = inventory.get(i);
				System.out.println(current.getItemID() + ", " + current.getName() + ", " + current.getPrice());
			}//close test
		}//close inventoryFromFile
		
		//getInventory
		public ArrayList<Item> getInventory(){
			return inventory;
		}//close getInventory
		
		//setInventory
		public void setInventory(ArrayList<Item> inventory) {
			this.inventory = inventory;
		}//close setInventory
			
		//------------------------------------------------------------------//
		
	

	public static void main(String[] args) throws FileNotFoundException {
		
		NileDotCom frame = new NileDotCom();
		
		frame.pack(); //fit to screen
		frame.setLocationRelativeTo(null); //center on screen
		frame.setTitle("Nile Dot Com | Spring 2023"); //frame title
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close window w/o exiting program
		frame.setVisible(true); //display window
		
		ImageIcon image = new ImageIcon("logo.png"); //create icon
		frame.setIconImage(image.getImage()); //change icon of frame
		
		/*tester GUI
		JFrame frame = new JFrame();
		frame.setTitle("NileDotCom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(420, 420);
		frame.setVisible(true);
		
		ImageIcon image = new ImageIcon("logo.png"); //create icon
		frame.setIconImage(image.getImage()); //change icon of frame
		*/
		
	}//close main
}//close NileDotCom
