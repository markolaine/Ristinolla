package ristinolla;

import ui.UI;
import javax.swing.*;

/**
*
* @author markolai@cs
*/
public class Ristinolla {

    public static void main(String[] args) {

        Logiikka peli = new Logiikka();
        SwingUtilities.invokeLater(new UI(peli));
    }
}
