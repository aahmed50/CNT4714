/* 
  Name: Asma Ahmed
  Course: CNT 4714 Spring 2023 
  Assignment title: Project 3 – A Two-tier Client-Server Application 
  Date:  March 9, 2023 
 
  Class:  Interface
*/ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.naming.AuthenticationException;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Interface extends JFrame{
	
	//Create GUI
		private final String defaultStatusText = "NO CONNECTION";
		private final Color defaultStatusColor = Color.WHITE;
		private final Color invalidStatusColor = Color.GRAY;
		private final Color statusBackgroundColor = (Color.BLACK);

		private final Font panelLabelFont = new Font("Consolas", Font.BOLD, 15);
		private final Color panelLabelColor = Color.BLACK;
		
		//--------------------------Main JFrame---------------------------------//		
		//interface
		@SuppressWarnings({ "rawtypes"})
		public Interface(JDBCHandler connectionHandler, int width, int height, String[] propertiesList) {

		//--------------------------PANELS---------------------------------//		
			int margin = 15;
			
			//initialize panels
			JPanel leftPanel = new JPanel();
			JPanel rightPanel = new JPanel();
			JPanel bottomPanel = new JPanel();

			add(leftPanel, BorderLayout.WEST);
			add(rightPanel, BorderLayout.EAST);
			add(bottomPanel, BorderLayout.SOUTH);

		//LEFT
			leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
			leftPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

			JPanel leftLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel connectionLabel = new JLabel("Connection Details", SwingConstants.LEFT);
			connectionLabel.setFont(panelLabelFont);
			connectionLabel.setForeground(panelLabelColor);

			//PROPERTIES
			JPanel propertiesPanel = new JPanel();
			JLabel propertiesLabel = new JLabel("Properties", SwingConstants.LEFT);
			String[] propertiesArray = propertiesList; //initialize array
			JComboBox propertiesDropDown = new JComboBox<String>(propertiesArray);
			propertiesDropDown.setPreferredSize(new Dimension(205, 30));
			propertiesLabel.setPreferredSize(new Dimension(90, 30));
			propertiesPanel.add(propertiesLabel);
			propertiesPanel.add(propertiesDropDown);

			//USERNAME
			JPanel usernamePanel = new JPanel();
			JLabel usernameLabel = new JLabel("Username", SwingConstants.LEFT);
			JTextField usernameField = new JTextField(20);
			usernameField.setPreferredSize(new Dimension(200, 30));
			usernameLabel.setPreferredSize(new Dimension(90, 30));
			usernamePanel.add(usernameLabel);
			usernamePanel.add(usernameField);

			//PASSWORD
			JPanel passwordPanel = new JPanel();
			JLabel passwordLabel = new JLabel("Password", SwingConstants.LEFT);
			JPasswordField passwordField = new JPasswordField(20);
			passwordField.setPreferredSize(new Dimension(200, 30));
			passwordLabel.setPreferredSize(new Dimension(90, 30));
			passwordPanel.add(passwordLabel);
			passwordPanel.add(passwordField);

			//Connect Button
			JButton connectButton = new JButton("Connect to Database");
				connectButton.setBackground(new Color(95, 125, 160));
				connectButton.setForeground(Color.WHITE);

			//build panel
			leftLabelPanel.add(connectionLabel);
			leftPanel.add(leftLabelPanel);
			leftPanel.add(propertiesPanel);
			leftPanel.add(usernamePanel);
			leftPanel.add(passwordPanel);
			leftPanel.add(connectButton);

		//RIGHT
			rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
			rightPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

			//ENTER COMMAND
			JPanel rightLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel queryLabel = new JLabel("Enter an SQL Command", SwingConstants.LEFT);
			JTextArea commandTextArea = new JTextArea(10, 30);
			queryLabel.setFont(panelLabelFont);
			queryLabel.setForeground(panelLabelColor);
			rightLabelPanel.add(queryLabel);

			//BUTTONS
			JScrollPane queryScrollPane = new JScrollPane(commandTextArea);
			JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			JButton clearCommand = new JButton("Clear SQL Command");
			JButton executeCommand = new JButton("Execute SQL Command");
			rightButtonsPanel.add(clearCommand);
			rightButtonsPanel.add(executeCommand);
			
				clearCommand.setBackground(new Color(160, 95, 125));
				clearCommand.setForeground(Color.WHITE);
			
				executeCommand.setBackground((new Color(95, 160, 160)));
				executeCommand.setForeground(Color.WHITE);

			//build panel
			rightPanel.add(rightLabelPanel);
			rightPanel.add(queryScrollPane);
			rightPanel.add(rightButtonsPanel);

		//BOTTOM
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
			bottomPanel.setPreferredSize(new Dimension(width, 250));

			JPanel statusPanel = new JPanel(new GridLayout(3, 1));
			JLabel statusLabel = new JLabel(defaultStatusText, SwingConstants.CENTER);
			statusPanel.setPreferredSize(new Dimension(width, 2));
			
			statusPanel.setBackground(statusBackgroundColor);
			statusLabel.setForeground(defaultStatusColor);
			statusPanel.add(new JLabel());
			statusPanel.add(statusLabel);
			statusPanel.add(new JLabel());

			JPanel bottomLabelPanel = new JPanel(new GridLayout(2, 2));
			JLabel resultLabel = new JLabel("SQL Execution Result Window", SwingConstants.LEFT);
			resultLabel.setFont(panelLabelFont);
			resultLabel.setForeground(panelLabelColor);
			bottomLabelPanel.add(new JLabel());
			bottomLabelPanel.add(resultLabel);

			JTable resultsTable = new JTable();
			JScrollPane resultsScrollPane = new JScrollPane(resultsTable);

			//Button
			JPanel clearResultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JButton clearResult = new JButton("Clear Result Window");
			clearResultPanel.add(clearResult);

				clearResult.setBackground(new Color(95, 125, 160));
				clearResult.setForeground(Color.WHITE);

			bottomPanel.add(statusPanel);
			bottomPanel.add(bottomLabelPanel);
			bottomPanel.add(resultsScrollPane);
			bottomPanel.add(clearResultPanel);

	//-------------------------- Action Listeners ---------------------------------//		

			propertiesDropDown.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {

					connectionHandler.close();
					statusLabel.setText(defaultStatusText);
					statusLabel.setForeground(defaultStatusColor);
					usernameField.setText(null);
					passwordField.setText(null);

				}//close itemStateChanged
			}); //close propertiesDropDown

			connectButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					String propertiesItems = (String) propertiesDropDown.getSelectedItem();
					String username = usernameField.getText();
					String password = new String(passwordField.getPassword());

					try {

						String url = connectionHandler.connect(propertiesItems, username, password);
						statusLabel.setText("CONNECTED TO: " + url);
						statusLabel.setForeground(Color.YELLOW);

					} catch (IOException ioe) {

						JOptionPane.showMessageDialog(null, "An unexpected error has occurred.");
						ioe.printStackTrace();

					} catch (SQLException sqle) {

						JOptionPane.showMessageDialog(null, "An SQL Error has occurred.");
						sqle.printStackTrace();

					} catch (AuthenticationException ae) {

						statusLabel.setText(defaultStatusText + " - User Credentials Do Not Match Properties File");
						statusLabel.setForeground(invalidStatusColor);
						connectionHandler.close();

					}//close catch
				}//close actionPerformed
			}); //close connectButton

			clearCommand.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					commandTextArea.setText(null);
				} //close actionPerformed
			}); //close clearCommand

			executeCommand.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent e) {

					String command = commandTextArea.getText();

					if (command.isEmpty()) {
						JOptionPane.showMessageDialog(null, "NO SQL command to be executed");
						return;
					} //close if
					try {

						if (command.toUpperCase().startsWith("SELECT")) {
							Vector[] result = connectionHandler.executeQuery(command);
							DefaultTableModel resultsTableModel = new DefaultTableModel(result[1], result[0]);
							resultsTable.setModel(resultsTableModel);

						} else {

							int rowsAffected = connectionHandler.executeUpdate(command);
							String displayRowsAffected = rowsAffected == 1 ? rowsAffected + " row updated"
									: rowsAffected + " rows updated";
							JOptionPane.showMessageDialog(null, "Successful Update: " + displayRowsAffected);

						}//close else
					} catch (NullPointerException npe) {

						JOptionPane.showMessageDialog(null, "not connected to database", "Application Error",
								JOptionPane.ERROR_MESSAGE);

					} catch (SQLException sqle) {

						JOptionPane.showMessageDialog(null, sqle.getMessage(), "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);

					}//close catch
				} //close actionPerformed
			}); //close executeCommand

			clearResult.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					DefaultTableModel resultsTableModel = new DefaultTableModel();
					resultsTable.setModel(resultsTableModel);

				}//close actionPerformed
			}); //close clearResult

		}//close Interface
} //close Interface class