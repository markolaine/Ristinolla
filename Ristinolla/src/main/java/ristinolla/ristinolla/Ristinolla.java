package ristinolla.ristinolla;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author markolai@cs
 */
public class Ristinolla {

    public static void main(String[] args) {

//        String[] valinnat = {"3x3", "5x5"};
//
//        int vastaus = JOptionPane.showOptionDialog(
//                null // Center in window.
//                , "Pelataanko 3x3, 5x5 peli?"
//                , "Uusi peli."
//                , JOptionPane.YES_NO_OPTION
//                , JOptionPane.PLAIN_MESSAGE
//                , null
//                , valinnat
//                , "3x3"
//        );
//        
//        if (vastaus == 0) {
//            
//            Peli peli = new Peli(vastaus);
//            
//        } else{
//
//        Peli peli = new Peli(vastaus);
//        
//    }
        
        Peli peli = new Peli();
    }
}
