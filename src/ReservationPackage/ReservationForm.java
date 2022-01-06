/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
//Christopher Hill 01088533 

//I pledge to support the honor system of Old Dominion University. 
I will refrain from any form of academic dishonesty or deception, such as cheating or plagiarism. 
I am aware that as a member of the academic community, 
it is my responsibility to turn in all suspected violators of the honor system.
I will report to Honor Council hearings if I am summoned.” 
By attending Old Dominion University you have accepted the responsibility to abide by this code. 
This is an institutional policy, approved by the Board of Visitors.


//
//
 */
package ReservationPackage;
import java.awt.FlowLayout;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;






/**
 *
 * @author syaco
 */
public class ReservationForm extends JFrame{
    
    public JTextField txtfFN;
    public JTextField txtfLN;
    public JTextField txtfSA;
    public JTextField txtfCITY;
    public JTextField txtfSTATE;
    public JTextField txtfPHONE;
    public JTextField txtfCN;
    public JTextField txtfLLN;
    public JComboBox cmbFlight;
    public JFormattedTextField frmtDate;
    public String seatnumber;
    public int counter;
    public static JRadioButton[] coach = new JRadioButton[41];
    public static JRadioButton[] firstclass = new JRadioButton[7];
    public static String[][] reservationArray = new String[47][11];
    public JPanel coachJPanel;
    public JPanel FCJPanel;
    public String fileName;
    public Boolean userExists;
    
    
    public ReservationForm() {
        
        super("Reservation System");
        setLayout(new FlowLayout());
        
        txtfFN = new JTextField(10);
        txtfFN.setVisible(true);
        add(txtfFN);
        
        txtfLN = new JTextField(10);
        txtfLN.setVisible(true);
        add(txtfLN);

        txtfSA = new JTextField(10);
        txtfSA.setVisible(true);
        add(txtfSA);
        
        txtfCITY = new JTextField(6);
        txtfCITY.setVisible(true);
        add(txtfCITY);
        
        txtfSTATE = new JTextField(2);
        txtfSTATE.setVisible(true);
        add(txtfSTATE);
        
        txtfPHONE = new JTextField(8);
        txtfPHONE.setVisible(true);
        add(txtfPHONE);
        
        frmtDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
        frmtDate.setValue(new Date());
        frmtDate.setVisible(true);
        add(frmtDate);
        
        cmbFlight = new JComboBox();
        cmbFlight.addItem("Select Flight");
        cmbFlight.addItem("Flight SA1212: Norfolk, VA to Washington, DC");
        cmbFlight.addItem("Flight SA1216: Washington, DC to Laguardia, NY");
        cmbFlight.addItem("Flight SA1299: Laguardia, NY to Norfolk, VA");
        cmbFlight.setVisible(true);
        ComboButtonHandler combohandler = new ComboButtonHandler();
        cmbFlight.addItemListener(combohandler);
        add(cmbFlight);
        
        RadioButtonHandler radioHandler = new RadioButtonHandler();
        coachJPanel = new JPanel();
        coachJPanel.setLayout(new GridLayout(4, coach.length));
        for (counter = 1; counter < coach.length; counter++) {
            coach[counter] = new JRadioButton("C" + (counter));
            coach[counter].addItemListener(radioHandler);
            coach[counter].setName("C" + (counter));
            coach[counter].setBackground(Color.green);
            coachJPanel.add(coach[counter]);
        }
        add(coachJPanel, BorderLayout.EAST);
        
        FCJPanel = new JPanel();
        FCJPanel.setLayout(new GridLayout(2, firstclass.length));
        for (counter = 1; counter < firstclass.length; counter++) {
            firstclass[counter] = new JRadioButton("FC" + (counter));
            firstclass[counter].addItemListener(radioHandler);
            firstclass[counter].setName("FC" + (counter));
            firstclass[counter].setBackground(Color.green);
            FCJPanel.add(firstclass[counter]);
        }
        add(FCJPanel, BorderLayout.WEST);
        
        JButton btnSave = new JButton("Save");
        btnSave.setSize(270,190);
        btnSave.setVisible(true);
        SaveButtonHandler savehandler = new SaveButtonHandler();
        btnSave.addActionListener(savehandler);
        btnSave.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(btnSave);
        
        JButton btnPrint = new JButton("Print");
        btnPrint.setSize(270,190);
        btnPrint.setVisible(true);
        PrintButtonHandler printHandler = new PrintButtonHandler();
        btnPrint.addActionListener(printHandler);
        btnPrint.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnPrint.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(btnPrint);
              
    }
    
    private Scanner input;
    private Formatter output;

    private void openFile(String type){

        Path path = null;

        try{
            try{

                if(cmbFlight.getSelectedItem() == "Select Flight"){
                    fileName = "";
                }     
                if(cmbFlight.getSelectedItem() == "Flight SA1212: Norfolk, VA to Washington, DC"){
                    fileName = "Flight SA1212.txt";
                }
                if(cmbFlight.getSelectedItem() == "Flight SA1216: Washington, DC to Laguardia, NY"){
                    fileName = "Flight SA1216.txt";
                }
                if(cmbFlight.getSelectedItem() == "Flight SA1299: Laguardia, NY to Norfolk, VA"){
                    fileName = "Flight SA1299.txt";
                }
                
                if (type == "reading") {
                    // JOptionPane.showMessageDialog(null, "File Exists.");
                    input = new Scanner(Paths.get(fileName));
                    readRecords();
                }
                
                if (type == "saving") {
                    output = new Formatter(fileName);
                }
                
            }
            catch(IOException ioException){
                System.err.println("Error Opening File. Terminating.");
                System.exit(1);
            }

        }
        catch(SecurityException securityException){
            System.err.println("Write Permission Denied. Terminating.");
            System.exit(1);
        }
    }

    public void addRecords(){
       
        boolean userExists = false;
        try{
            if (reservationArray != null) {
                for(counter = 0; counter < reservationArray.length; counter++){
                    if (reservationArray[counter][0] == txtfFN.getText() && reservationArray[counter][1] == txtfLN.getText() && reservationArray[counter][2] == txtfSA.getText() && reservationArray[counter][3] == txtfCITY.getText() && reservationArray[counter][4] == txtfSTATE.getText() && reservationArray[counter][5] == txtfPHONE.getText() && reservationArray[counter][6] == frmtDate.getText()) {
                        userExists = true;
                        JOptionPane.showMessageDialog(null, "Reservation Already Exists.");
                    }  
                }
            }
            
            if (userExists == false){

                SecureRandom randomNumbers = new SecureRandom();
                int confirmationNumber = randomNumbers.nextInt(10000);
                
                
                output = new Formatter(fileName);
                output.format("%s %s %s %s %s %s %s %s %s %s%n", 
                        txtfFN.getText(), 
                        txtfLN.getText(), 
                        txtfSA.getText(), 
                        txtfCITY.getText(), 
                        txtfSTATE.getText(), 
                        txtfPHONE.getText(), 
                        frmtDate.getText(), 
                        cmbFlight.getSelectedIndex(),
                        seatnumber,
                        confirmationNumber);

                if (reservationArray != null) {
                    for(counter = 0; counter < reservationArray.length; counter++){
                        if (reservationArray[counter][0] != null){
                            output.format("%s %s %s %s %s %s %s %s %s %s%n", 
                                    reservationArray[counter][0], 
                                    reservationArray[counter][1], 
                                    reservationArray[counter][2], 
                                    reservationArray[counter][3], 
                                    reservationArray[counter][4], 
                                    reservationArray[counter][5], 
                                    reservationArray[counter][6], 
                                    reservationArray[counter][7], 
                                    reservationArray[counter][8], 
                                    reservationArray[counter][9]);
                        }
                    }
                }
            }

        }
        catch(FormatterClosedException formatterClosedException){
            System.err.println("Error Writing To File. Terminating");
            System.exit(1);
        }
        catch(FileNotFoundException fileNotFoundException){
            System.err.println("Error Opening File. Terminating.");
            System.exit(1);
        }            
    } 
    private void readRecords(){
        
        try{


            int counter =0;
            
            while(input.hasNext()){
                reservationArray[counter][0] = input.next(); //first name
                reservationArray[counter][1] = input.next(); //last name
                reservationArray[counter][2] = input.next(); //street address
                reservationArray[counter][3] = input.next(); //city
                reservationArray[counter][4] = input.next(); //state
                reservationArray[counter][5] = input.next(); //phone
                reservationArray[counter][6] = input.next(); //date
                reservationArray[counter][7] = input.next(); //flight index
                reservationArray[counter][8] = input.next(); //seat number
                reservationArray[counter][9] = input.next(); //confirmation number
          
                for (int x = 0; x < coachJPanel.getComponents().length; x++) {
                    JRadioButton selectedRadioButton = (JRadioButton) coachJPanel.getComponents()[x];
                    if (selectedRadioButton.getName().equals(reservationArray[counter][8])) {
                        selectedRadioButton.setSelected(true);
                        selectedRadioButton.setEnabled(false);
                        selectedRadioButton.setBackground(Color.red);
                    }                   
                }
                
                for (int x = 0; x < FCJPanel.getComponents().length; x++) {
                    JRadioButton selectedRadioButton = (JRadioButton) FCJPanel.getComponents()[x];
                    if (selectedRadioButton.getName().equals(reservationArray[counter][8])) {
                        selectedRadioButton.setSelected(true);
                        selectedRadioButton.setEnabled(false); 
                        selectedRadioButton.setBackground(Color.red);
                    }                    
                }              

                counter++;
            }        

        }
        catch(FormatterClosedException formatterClosedException){
            //System.err.println("Error Writing To File. Terminating");
            //System.exit(1);
        }
    }   

    private void closeFile(String type){

        if (type == "saving") {
        if(output != null)
            output.close();
        }
        
        if (type == "reading") {
            if(input != null)
             input.close(); 
        }
    } 
   
    private class SaveButtonHandler implements ActionListener{
        
        
        @Override
        public void actionPerformed(ActionEvent event){
            
            Date currentDate = new Date();
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 14);
            Date currentDatePlus14 = calendar.getTime();
            String d = frmtDate.getText();

            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
                if(date1.compareTo(currentDatePlus14) > 0){
                    JOptionPane.showMessageDialog(null, "Reservation too far in advance.");
                } else {
                    openFile("saving");
                    addRecords();
                    closeFile("saving");
                    cmbFlight.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "Reservation Confirmed.");
                }
            }
            catch(ParseException e){
                        e.printStackTrace();
            }
           
        }
    }
    
    private class PrintButtonHandler implements ActionListener{
        
        
        @Override
        public void actionPerformed(ActionEvent event){
            
              String confirmationnumber = JOptionPane.showInputDialog(null, "What is your conifmation number?");
            System.out.printf("The user's confirmation number '%s'.\n", confirmationnumber);
            
            String lastname = JOptionPane.showInputDialog(null, "What is your last name?");
            System.out.printf("The user's last name is '%s'.\n", lastname);
                        
        }
    }
    
    private class ComboButtonHandler implements ItemListener{
            
        @Override
        public void itemStateChanged(ItemEvent event){
            if (event.getStateChange() == ItemEvent.SELECTED) {
                // Item was just selected
                if (event.getItem() != "Select Flight") {
                openFile("reading");
                readRecords();
                closeFile("reading");  
                }
            }

        }
    }
    
    private class RadioButtonHandler implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent event){
            
            JRadioButton selectedRadioButton = (JRadioButton) event.getSource();
            
            if(event.getStateChange() == ItemEvent.SELECTED){
                
                seatnumber = selectedRadioButton.getName();
                
                
            }
        }
    }
    
}


    

