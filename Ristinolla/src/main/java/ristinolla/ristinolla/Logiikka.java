package ristinolla.ristinolla;

import javax.swing.*;

/**
*
* @author markolai@cs
*/
public final class Logiikka extends JFrame {
    
    private int[] pelilauta = new int[9];

public Logiikka() {
    
    uusiPeli();
}

public void uusiPeli() {
    
    for (int i = 0; i < pelilauta.length ; i++) {
        this.pelilauta[i] = 0;
    }
    
}


}
