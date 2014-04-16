package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ristinolla.Logiikka;

/**
 *
 * @author markolai@cs
 */
public class Kuuntelija implements ActionListener {

    private final UI ui;
    private final Logiikka peli;
    private int ruutu;

    public Kuuntelija(UI ui, Logiikka peli) {
        this.ui = ui;
        this.peli = peli;
    }

    public Kuuntelija(UI ui, Logiikka peli, int ruutu) {
        this.ui = ui;
        this.peli = peli;
        this.ruutu = ruutu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String komento = e.getActionCommand();

        if (komento.equals("UUSIPELI")) {
            this.peli.uusiPeli();
            this.ui.nollaaPelilauta();
        } else if (komento.equals("UUSIPELIJAVOITOT")) {
            this.peli.resetoiVoitot();
        } else if (komento.equals("LOPETA")) {
            System.exit(0);
        } else {

            peli.asetaMerkkiRuutuun(ruutu);
            peli.tarkistaLoppuiko();
            this.ui.paivita();
        }
    }
}
