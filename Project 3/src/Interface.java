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
import java.sql.Connection;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource; 

import javax.swing.*;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Interface extends JFrame {
	
	//variables
	private ResultSetTableModel tableModel;
    private Connection connection;
    private MysqlDataSource dataSource = new MysqlDataSource();
 
	//private JButton clearResultsButton, clearSQLButton, connectButton, executeButton;
    //private JLabel databaseTitle, sqlTitle, tableTitle, driverLabel, urlLabel, userLabel, passLabel;

    private JTextField connectionTextField, userTextField, passTextField;
    
    private JScrollPane jScrollPane1, jScrollPane2;

    private JTable resultTable;
    private JTextArea sqlTextArea;
    
    private JComboBox driverDropDownBox;
    private JComboBox urlDropDownBox;
			
			//GUI instance
			public Interface() {
				//drop down menu
				String[] PropertiesItems = {"db.properties", "client.properties"};
				
				JScrollPane jScrollPane1 = new JScrollPane();
		        JTextArea sqlTextArea = new JTextArea();
		     
		        JLabel sqlTitle = new JLabel();
		        JLabel driverLabel = new JLabel();
		        JLabel urlLabel = new JLabel();
		        JLabel userLabel = new JLabel();
		        JLabel passLabel = new JLabel();
		        
		        JComboBox driverDropDownBox = new JComboBox();
		        JComboBox urlDropDownBox = new JComboBox();
		        
		        JTextField userTextField = new JTextField();
		        JPasswordField passTextField = new JPasswordField();
		        JLabel databaseTitle = new JLabel();
		        
		        JButton executeButton = new JButton();
		        JButton clearSQLButton = new JButton();
		        JButton connectButton = new JButton();
		        
		        JTextField connectionTextField = new JTextField();
		        JButton clearResultButton = new JButton();
		        
		        JLabel tableTitle = new JLabel();
		        JScrollPane jScrollPane2 = new JScrollPane();
		        JTable resultTable = new JTable();

		        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        
				setTitle("SQL Client GUI | Spring 2023"); //frame title


		        sqlTextArea.setColumns(20);
		        sqlTextArea.setRows(5);
		        jScrollPane1.setViewportView(sqlTextArea);

		        sqlTitle.setFont(new Font("Consolas", 1, 14));
		        sqlTitle.setForeground(new Color(0, 51, 255));
		        sqlTitle.setText("Enter An SQL Command");

		        driverLabel.setBackground(new Color(204, 204, 204));
		        driverLabel.setText("JDBC Driver");

		        urlLabel.setBackground(new Color(204, 204, 204));
		        urlLabel.setText("Database URL");

		        userLabel.setBackground(new Color(204, 204, 204));
		        userLabel.setText("Username");

		        passLabel.setBackground(new Color(204, 204, 204));
		        passLabel.setText("Password");

		        driverDropDownBox.setModel(new DefaultComboBoxModel(new String[] { "com.mysql.cj.jdbc.Driver" }));

		        urlDropDownBox.setModel(new DefaultComboBoxModel(new String[] { "jdbc:mysql://localhost:3312/project3" }));

		        passTextField.setToolTipText("");

		        databaseTitle.setFont(new Font("Consolas", 1, 14));
		        databaseTitle.setForeground(new Color(0, 0, 255));
		        databaseTitle.setText("Enter Database Information");

		        executeButton.setBackground(new Color(204, 255, 204));
		        executeButton.setForeground(new Color(0, 204, 0));
		        executeButton.setText("Execute SQL Command");
		        
		        //actionListener
		        executeButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent evt) {
		                executeButtonActionPerformed(evt);
		            } //close actionPerformed
		        }); //close actionListener

		        clearSQLButton.setBackground(new Color(255, 204, 204));
		        clearSQLButton.setForeground(new Color(204, 0, 51));
		        clearSQLButton.setText("Clear SQL Command");
		        
		        clearSQLButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		                clearSQLButtonActionPerformed(evt);
		            }
		        });

		        connectButton.setBackground(new Color(51, 0, 255));
		        connectButton.setForeground(new Color(255, 255, 204));
		        connectButton.setText("Connect to Database");
		        
		        connectButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		                connectButtonActionPerformed(evt);
		            }
		        });

		        connectionTextField.setEditable(false);
		        connectionTextField.setBackground(new Color(0, 0, 0));
		        connectionTextField.setForeground(new Color(255, 0, 0));
		        connectionTextField.setText("No Connection");

		        clearResultButton.setBackground(new Color(255, 255, 153));
		        clearResultButton.setForeground(new Color(255, 153, 0));
		        clearResultButton.setText("Clear Result Window");
		        
		        clearResultButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		                clearResultButtonActionPerformed(evt);
		            }
		        });

		        tableTitle.setFont(new Font("Consolas", 1, 14));
		        tableTitle.setForeground(new Color(0, 51, 255));
		        tableTitle.setText("SQL Execution Result Window");

		        resultTable.setModel(new DefaultTableModel(
		            new Object [][] {
		            },
		            new String [] {
		            }
		        ));
		        
		        jScrollPane2.setViewportView(resultTable);

		        GroupLayout layout = new GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addComponent(jScrollPane2)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
		                                    .addComponent(userLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                    .addComponent(driverLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                    .addComponent(urlLabel, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
		                                    .addComponent(passLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
		                                    .addComponent(userTextField, GroupLayout.Alignment.LEADING)
		                                    .addComponent(urlDropDownBox, GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE)
		                                    .addComponent(driverDropDownBox, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                    .addComponent(passTextField)))
		                            .addComponent(databaseTitle))
		                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                            .addComponent(jScrollPane1)
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(sqlTitle)
		                                .addGap(0, 0, Short.MAX_VALUE))))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(connectionTextField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
		                        .addGap(18, 18, 18)
		                        .addComponent(connectButton)
		                        .addGap(18, 18, 18)
		                        .addComponent(clearSQLButton, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addComponent(executeButton))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                            .addComponent(clearResultButton)
		                            .addComponent(tableTitle))
		                        .addGap(0, 0, Short.MAX_VALUE)))
		                .addContainerGap())
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(26, 26, 26)
		                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    .addComponent(sqlTitle)
		                    .addComponent(databaseTitle))
		                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                            .addComponent(driverLabel)
		                            .addComponent(driverDropDownBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                            .addComponent(urlLabel)
		                            .addComponent(urlDropDownBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                            .addComponent(userLabel)
		                            .addComponent(userTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                            .addComponent(passLabel)
		                            .addComponent(passTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
		                .addGap(26, 26, 26)
		                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    .addComponent(executeButton)
		                    .addComponent(clearSQLButton)
		                    .addComponent(connectButton)
		                    .addComponent(connectionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addComponent(tableTitle)
		                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
		                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(clearResultButton)
		                .addContainerGap())
		        );

		        pack();
			}//close interface
			
			private void connectButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
		        
		        
		        dataSource.setUser(userTextField.getText());
		        dataSource.setPassword(passTextField.getText());
		        dataSource.setURL(urlDropDownBox.getSelectedItem().toString());
		        // Set credentials and URL from GUI for database connection
		        
		        try {
		            connection = dataSource.getConnection();
		            connectionTextField.setText(String.format("Connected to %s", urlDropDownBox.getSelectedItem().toString()));
		        }
		        catch(SQLException sqlException)
		        {
		            sqlException.printStackTrace();
		            System.exit(1);
		        }
		        
		    }//GEN-LAST:event_connectButtonActionPerformed
			
			private void clearSQLButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearSQLButtonActionPerformed
		        sqlTextArea.setText(null);
		    }//GEN-LAST:event_clearSQLButtonActionPerformed

			private void executeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
		        String query = sqlTextArea.getText();
		        
		        try {
		            tableModel = new ResultSetTableModel(dataSource, connection, query);
		           if (query.substring(0, query.indexOf(' ')).equals("select"))
		           {
		            tableModel.setQuery(query);
		           } else {
		            tableModel.setUpdate(query);
		           }
		            resultTable.setModel(tableModel);
		            jScrollPane2.setVisible(true);

		        }
		        catch (SQLException sql) {
		            sql.printStackTrace();
		        } catch (ClassNotFoundException ex) {
		            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }//GEN-LAST:event_executeButtonActionPerformed

		    private void clearResultButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearResultButtonActionPerformed
		        jScrollPane2.setVisible(false);
		    }//GEN-LAST:event_clearResultButtonActionPerformed
		    
		    
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
	}//close main
} //close Interface
