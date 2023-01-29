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
import javax.swing.border.EtchedBorder;


public class NileDotCom extends JFrame {
	//create GUI
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

		//--------------------------Main JFrame---------------------------------//		
		//interface
		public NileDotCom() throws FileNotFoundException{
			
			this.inventoryFromFile(); //initialize inventory
			
			//create JPanel for fields
			JPanel fieldsPanel = new JPanel();
			fieldsPanel.setBounds(145, 21, 400, 105);
			fieldsPanel.setLayout(null);
			
			//JLabels & JTextField Boxes
			jlbItemID.setFont(new Font("Consolas", Font.PLAIN, 10));
			jlbItemID.setBounds(0, 4, 191, 21);
			jlbItemID.setHorizontalAlignment(SwingConstants.RIGHT);
			fieldsPanel.add(jlbItemID);
			jtfItemID.setBounds(201, 4, 189, 21);
			fieldsPanel.add(jtfItemID);
			
			jlbQuantity.setFont(new Font("Consolas", Font.PLAIN, 10));
			jlbQuantity.setBounds(0, 29, 191, 21);
			jlbQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
			fieldsPanel.add(jlbQuantity);
			jtfQuantity.setBounds(201, 29, 189, 21);
			fieldsPanel.add(jtfQuantity);
			
			jlbItemDetails.setFont(new Font("Consolas", Font.PLAIN, 10));
			jlbItemDetails.setBounds(0, 54, 191, 21);
			jlbItemDetails.setHorizontalAlignment(SwingConstants.RIGHT);
			fieldsPanel.add(jlbItemDetails);
			jtfItemDetails.setBounds(201, 54, 189, 21);
			fieldsPanel.add(jtfItemDetails);
			
			jlbSubtotal.setFont(new Font("Consolas", Font.PLAIN, 10));
			jlbSubtotal.setBounds(0, 79, 191, 21);
			jlbSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
			fieldsPanel.add(jlbSubtotal);
			jtfTotalItems.setBounds(201, 79, 189, 21);
			fieldsPanel.add(jtfTotalItems);
			
			//create JPanel for button menu 
			JPanel buttonsPanel = new JPanel(); 
			buttonsPanel.setBounds(145, 140, 400, 70);
			buttonsPanel.setLayout(null);
			
			//JButtons
			jbtProcessItem.setBackground(new Color(255, 255, 255));
			jbtProcessItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtProcessItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(64, 128, 128)));
			jbtProcessItem.setFont(new Font("Consolas", Font.BOLD, 7));
			jbtProcessItem.setBounds(18, 9, 109, 21);
			buttonsPanel.add(jbtProcessItem);
						
			jbtConfirmItem.setBackground(new Color(255, 255, 255));
			jbtConfirmItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtConfirmItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(64, 128, 128)));
			jbtConfirmItem.setFont(new Font("Consolas", Font.BOLD, 7));
			jbtConfirmItem.setBounds(18, 39, 109, 21);
			buttonsPanel.add(jbtConfirmItem);
			
			jbtViewOrder.setBackground(new Color(255, 255, 255));
			jbtViewOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtViewOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(64, 128, 128)));
			jbtViewOrder.setFont(new Font("Consolas", Font.BOLD, 7));
			jbtViewOrder.setBounds(145, 9, 109, 21);
			buttonsPanel.add(jbtViewOrder);
			
			jbtFinishOrder.setBackground(new Color(255, 255, 255));
			jbtFinishOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtFinishOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(64, 128, 128)));
			jbtFinishOrder.setFont(new Font("Consolas", Font.BOLD, 7));
			jbtFinishOrder.setBounds(145, 39, 109, 21);
			buttonsPanel.add(jbtFinishOrder);
			
			jbtNewOrder.setBackground(new Color(255, 255, 255));
			jbtNewOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtNewOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(64, 128, 128)));
			jbtNewOrder.setFont(new Font("Consolas", Font.BOLD, 7));
			jbtNewOrder.setBounds(272, 9, 109, 21);
			buttonsPanel.add(jbtNewOrder);
			
			jbtExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtExit.setBackground(new Color(174, 215, 215));
			jbtExit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(64, 128, 128)));
			jbtExit.setFont(new Font("Consolas", Font.BOLD, 7));
			jbtExit.setBounds(272, 39, 109, 21);
			buttonsPanel.add(jbtExit);
			
				//disable default behavior
				this.jbtConfirmItem.setEnabled(false);
				this.jbtViewOrder.setEnabled(false);
				this.jbtFinishOrder.setEnabled(false);
				getContentPane().setLayout(null);
				this.jtfTotalItems.setEnabled(false);
				this.jtfItemDetails.setEditable(false);
			
			//decorative panel
			JLabel companel = new JLabel();
			companel.setBounds(-157, -104, 514, 449);
			companel.setIcon(new ImageIcon("logo.png"));
				
			//design - add to frame
			getContentPane().add(fieldsPanel);
			getContentPane().add(buttonsPanel);
			getContentPane().add(companel);

			//decorative label
			JLabel thankYou = new JLabel("Thank You for Shopping at NileDotCom!");
			thankYou.setHorizontalTextPosition(SwingConstants.RIGHT);
			thankYou.setHorizontalAlignment(SwingConstants.RIGHT);
			thankYou.setFont(new Font("Consolas", Font.ITALIC, 15));
			thankYou.setBounds(145, 230, 393, 20);
			getContentPane().add(thankYou);
			
			//--------------------------Action Listeners---------------------------------//
			
			//processItem actionListener
			jbtProcessItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//parse .TXT file
					String itemID = String.valueOf(jtfItemID.getText());
					int itemQuantity = Integer.parseInt(jtfQuantity.getText());
					
					int itemIndex = linearSearch(itemID); //call linearSearch
					
					System.out.println(itemIndex);
					
					//check inventory
					if(itemIndex != -1) {
						Item foundItem = inventory.get(itemIndex);
						order.setItemInfo(foundItem.getItemID() + " ", foundItem.getName(), foundItem.getPrice() + " ", itemQuantity + " ", order.getDiscountPercentage(itemQuantity) + " ", order.getTotalDiscount(itemQuantity, foundItem.getPrice()) + " ");
						String itemInfo = foundItem.getItemID() + foundItem.getName() + " $" + foundItem.getPrice() + " " + itemQuantity + " " + order.getDiscountPercentage(itemQuantity) + "%" + order.getTotalDiscount(itemQuantity, foundItem.getPrice());
						jtfItemDetails.setText(itemInfo);
						jbtConfirmItem.setEnabled(true);
						jbtProcessItem.setEnabled(false);
						order.setOrderSubtotal(itemQuantity, foundItem.getPrice());
						jtfItemDetails.setEnabled(false);
						jtfTotalItems.setEnabled(false);
					}//oof
					else {
						JOptionPane.showMessageDialog(null, "Item ID " + itemID + " could not be found.");
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
					
					//transaction.txt
					order.prepareInvoice();

					//adds item to viewOrder dialog
					order.addToViewOrder(jtfItemDetails.getText());
					
					//configure buttons
					jbtProcessItem.setEnabled(true);
					jbtViewOrder.setEnabled(true);
					jbtFinishOrder.setEnabled(true);
					jbtConfirmItem.setEnabled(false);

					//update counter
					jbtProcessItem.setText("Process Item #" + (order.getTotalItems() + 1));
					jbtConfirmItem.setText("Confirm Item #" + (order.getTotalItems() + 1));

					//update textFields boxes	
					jtfItemID.setText("");
					jtfQuantity.setText("");
					jtfTotalItems.setText("$" + new DecimalFormat("#0.00").format(order.getOrderSubtotal()));

					//update labels
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
		
		}//close interface constructor

		//-------------------------Auxiliary Functions--------------------------//

		//linearSearch returns integer
		public int linearSearch(String itemID) {
			for(int i=0; i < this.inventory.size(); i++) {
				Item currentItem = inventory.get(i);
				String currentItemID = String.valueOf((currentItem.getItemID()));
				if(currentItemID.equals(itemID)) {
					return i;
				}//close if
			}//close for loop
			return -1;
		}//close linearSearch 
		
		//cycle through
		/* public Item linearSearch(String itemID) {
			for(Item currentItem:inventory) {
				if(currentItem.getItemID() == itemID)
					return currentItem;
			}
			return new Item();
		}
		*/
		
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
				String[] itemDetails = item.split(", ");
				
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

		}//close inventoryFromFile
		
		//getInventory
		public ArrayList<Item> getInventory(){
			return inventory;
		}//close getInventory
		
		//setInventory
		public void setInventory(ArrayList<Item> inventory) {
			this.inventory = inventory;
		}//close setInventory
			
		//----------------------------MAIN FUNCTION--------------------------------------//
		
	public static void main(String[] args) throws FileNotFoundException {
		
		NileDotCom frame = new NileDotCom();
		
		frame.pack(); //fit to screen
		frame.setLocationRelativeTo(null); //center on screen
		frame.setTitle("NileDotCom | Spring 2023"); //frame title
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close window w/o exiting program
		frame.setVisible(true); //display window
		frame.setSize(570, 300); //frame size
		frame.setResizable(false); //disable resize
		frame.getContentPane().setBackground(new Color(95, 158, 160)); //background color
		
		
		ImageIcon image = new ImageIcon("logo-white.png"); //create icon
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
    private static final long serialVersionUID = 1L;
}//close NileDotCom
