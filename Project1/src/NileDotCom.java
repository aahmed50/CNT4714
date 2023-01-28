/*  Name:  Asma Ahmed
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 
import javax.swing.*;
import javax.swing.ImageIcon;
import java.util.*;



public class NileDotCom extends JFrame{
	public NileDotCom() {
		JFrame frame = new JFrame();
		frame.setTitle("NileDotCom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(420, 420);
		frame.setVisible(true);
		
		ImageIcon image = new ImageIcon("logo.png"); //create icon
		frame.setIconImage(image.getImage()); //change icon of frame
	}

	public static void main(String[] args) {
		
		//tester GUI
		JFrame frame = new JFrame();
		frame.setTitle("NileDotCom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(420, 420);
		frame.setVisible(true);
		
		ImageIcon image = new ImageIcon("logo.png"); //create icon
		frame.setIconImage(image.getImage()); //change icon of frame
	}//close tester main
	
}//close NileDotCom
