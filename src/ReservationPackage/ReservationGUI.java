/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationPackage;
import javax.swing.JFrame;

/**
 *
 * @author syaco
 */
public class ReservationGUI {
    public static void main(String[] args){
        ReservationForm reservationForm = new ReservationForm();
        reservationForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationForm.setSize(900,300);
        reservationForm.setVisible(true);
    }    
}
