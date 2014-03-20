package ristinolla.ristinolla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author markolai@cs
 */
public class Peli extends JFrame implements ActionListener {

    JPanel pelilauta;
    JButton[] ruudut;
    public int laskuri = 0;
    public int merkit = 0;
    public int nykyinenPelaaja = 1;

    public Peli() {

        uusiPeli();

    }

    /**
     *
     * Pelilaudan alustus ja pelin luonti.
     */
    public void uusiPeli() {

        pelilauta = new JPanel();
        pelilauta.setLayout(new GridLayout(3, 3));
        this.add(pelilauta);
        ruudut = new JButton[9];

        for (int i = 0; i < ruudut.length; i++) {

            ruudut[i] = new JButton();
            pelilauta.add(ruudut[i]);
            ruudut[i].setEnabled(true);
            ruudut[i].addActionListener(this);
            ruudut[i].setBackground(Color.DARK_GRAY);
            ruudut[i].setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));

        }
        //       this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(500, 500);
        Dimension ruudunKoko = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(ruudunKoko.width / 2 - this.getSize().width / 2, ruudunKoko.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        laskuri++;
        for (JButton ruutu : ruudut) {
            if (ruutu == e.getSource()) {
                if (merkit % 2 == 0) {
                    ruutu.setFont(new Font("Dialog", Font.BOLD, 30));
                    ruutu.setText("X");
                    ruutu.setBackground(Color.blue);

                    ruutu.setEnabled(false);
                } else {
                    ruutu.setFont(new Font("Dialog", Font.BOLD, 30));
                    ruutu.setText("O");
                    ruutu.setBackground(Color.red);
                    ruutu.setEnabled(false);
                }
            }
            tarkistaVoittaja();
        }

        if (laskuri == ruudut.length) {
            laskuri = 0;
            peliLoppui();
        }
        merkit++;
    }

    /**
     *
     * Palautetaan pelin voittaja.
     */
    public int tarkistaVoittaja() {

        return 0;

    }

    /**
     *
     * Ruudun täytyttyä ja toisen pelaajan voittaessa kysytään jatketaan
     * pelaamista.
     */
    public void peliLoppui() {

        JOptionPane.showMessageDialog(null, "Jompi kumpi voitti");

        JDialog.setDefaultLookAndFeelDecorated(true);
        int valinta = JOptionPane.showConfirmDialog(null, "Pelataanko uusi peli?", "Lopeta",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (valinta == JOptionPane.YES_OPTION) {
            for (JButton ruutu : ruudut) {
                ruutu.setText("");
                ruutu.setBackground(Color.GRAY);
                ruutu.setEnabled(true);
            }
            laskuri = 0;
            merkit = 0;
        } else if (valinta == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

    }
}
