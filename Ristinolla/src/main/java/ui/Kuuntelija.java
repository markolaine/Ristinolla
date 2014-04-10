package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import ristinolla.Logiikka;
import ui.UI;

/**
 *
 * @author markolai@cs
 */
public class Kuuntelija implements ActionListener {

    private UI ui;
    private final Logiikka peli;
    private JButton ruutu;
    private final JButton[] ruudut;

    public Kuuntelija(UI ui, Logiikka peli, JButton ruutu, JButton[] ruudut) {
        this.ui = ui;
        this.peli = peli;
        this.ruutu = ruutu;
        this.ruudut = ruudut;
    }

    public Kuuntelija(JFrame UI, Logiikka peli, JButton jButton, JButton[] ruudut) {
        this.peli = peli;
        this.ruudut = ruudut;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (JButton a : ruudut) {
            if (a == e.getSource()) {
                peli.asetaMerkkiRuutuun(a, ruudut);
            }
        }
        peli.paivita(ruudut);
    }

}
