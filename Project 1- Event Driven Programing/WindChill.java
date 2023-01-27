//windChill GUI
//Developed: June 2007
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WindChill implements ActionListener{
   //class constants
   private static final int WINDOW_WIDTH = 410; //pixels
   private static final int WINDOW_HEIGHT = 235; //pixels
   private static final int FIELD_WIDTH = 20;   //characters
   private static final int AREA_WIDTH = 40;   //characters

   private static final FlowLayout LAYOUT_STYLE = new FlowLayout();

   private static final String LEGEND = "  This windchill " + "calculator is intended for wind speeds greater than 4 mph.";
   //instance variables
   //window for GUI
   private JFrame window = new JFrame("Windchill Calculator");

   //legend
   private JTextArea legendArea = new JTextArea(LEGEND, 2, AREA_WIDTH);

   //user entry area for temperature
   private JLabel fahrTag = new JLabel("Fahrenheit Temperature");
   private JTextField fahrText = new JTextField(FIELD_WIDTH);

   //user entry area for windspeed
   private JLabel windTag = new JLabel("           Windspeed (mph)");
   private JTextField windText = new JTextField(FIELD_WIDTH);

   //entry area for windchill result
   private JLabel chillTag = new JLabel(" Windchill Temperature");
   private JTextField  chillText = new JTextField(FIELD_WIDTH);
   //run button
   private JButton runButton = new JButton("Run");

    //Windchill():  constructor
    public WindChill() {
        //configure GUI
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       legendArea.setEditable(false);
       legendArea.setLineWrap(true);
       legendArea.setWrapStyleWord(true);
       legendArea.setBackground(window.getBackground());

       chillText.setEditable(false);
       //chillText.setBackground(Color.WHITE);

       //register event listener
       runButton.setBackground(Color.RED);
       runButton.setOpaque(true);
       runButton.addActionListener(this);
       //add components to the container
       Container c = window.getContentPane();
       c.setLayout(LAYOUT_STYLE);

       c.add(legendArea);
       c.add(fahrTag);
       c.add(fahrText);
       c.add(windTag);
       c.add(windText);
       c.add(chillTag);
       c.add(chillText);
       c.add(runButton);

       //display GUI
       window.show();
    }
  //actionPerformed(): run button action event handler
    public void actionPerformed(ActionEvent e) {
       //get user's responses
       String response1 = fahrText.getText();
       double t = Double.parseDouble(response1);
       String response2 = windText.getText();
       double v = Double.parseDouble(response2);
       //compute windchill
       double windchillTemperature = 0.081 * (t - 91.4) * (3.71 * Math.sqrt(v) + 5.81 - 0.25*v) + 91.4;
         int perceivedTemperature = (int)Math.round(windchillTemperature);

       //display windchill
       String output = String.valueOf(perceivedTemperature);
       chillText.setText(output);
    }

 //main():  application entry point
    public static void main(String[] args) {
       WindChill gui = new WindChill();
    }
 }

       
