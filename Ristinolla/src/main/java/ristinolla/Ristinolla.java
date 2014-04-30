package ristinolla;

import ui.UI;
import javax.swing.*;

/**
 *
 * @author markolai@cs
 * 
 * Luo ristinollapelille logiikan ja käyttää sitä UI-luokassa.
 * 
 */
public class Ristinolla {

    public static void main(String[] args) {

        Logiikka peli = new Logiikka();
        SwingUtilities.invokeLater(new UI(peli));
    }
}
