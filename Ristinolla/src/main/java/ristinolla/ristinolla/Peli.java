package ristinolla.ristinolla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author markolai@cs
 */
public class Peli extends JFrame implements ActionListener {

    JPanel pelilauta;
    JButton[] ruudut;
    public int laskuri = 0;
    public int merkit = 0;
    public int ristinVoitot = 0;
    public int nollanVoitot = 0;

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
            ruudut[i].setFont(new Font("Dialog", Font.BOLD, 30));

        }
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(500, 500);
        Dimension ruudunKoko = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(ruudunKoko.width / 2 - this.getSize().width / 2, ruudunKoko.height / 2 - this.getSize().height / 2);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String uusiRivi = System.lineSeparator();

        laskuri++;
        for (JButton ruutu : ruudut) {
            if (ruutu == e.getSource()) {
                if (merkit % 2 == 0) {
                    ruutu.setText("X");
                    ruutu.setBackground(Color.blue);
                    ruutu.setEnabled(false);
                } else {
                    ruutu.setText("O");
                    ruutu.setBackground(Color.red);
                    ruutu.setEnabled(false);
                }
            }
        }

        if (voittikoPelaaja() == true) {

            if (merkit % 2 == 0) {
                ristinVoitot++;
                JOptionPane.showMessageDialog(null,"Ristiä pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: "+ ristinVoitot+". Nollan voitot: "+nollanVoitot+".", "Risti voitti!", JOptionPane.INFORMATION_MESSAGE);
                peliLoppui();

            } else {
                nollanVoitot++;
                JOptionPane.showMessageDialog(null,"Nollaa pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: "+ ristinVoitot+". Nollan voitot: "+nollanVoitot+".", "Nolla voitti!", JOptionPane.INFORMATION_MESSAGE);
                peliLoppui();
            }
        }

        if (voittikoPelaaja() == false && laskuri == ruudut.length) {
            JOptionPane.showMessageDialog(null, "Peli loppui tasapeliin." + uusiRivi + "Ristin voitot: "+ ristinVoitot+". Nollan voitot: "+nollanVoitot+".", "Tasapeli!", JOptionPane.INFORMATION_MESSAGE);
            peliLoppui();
        }
        merkit++;
    }

    /**
     *
     * Tarkistetaan voittiko pelaaja siirrollaan pelin ja ilmoitetaan pelin
     * voittaja.
     *
     * @return
     */
    public boolean voittikoPelaaja() {

        for (int i = 0; i < 3; i++) {

            if (ruudut[i].isEnabled() == false) {
                String verrattava = ruudut[i].getText();
                //String pelaaja = ruudut[i].getText();
                if (verrattava.equals(ruudut[i + 3].getText()) && verrattava.equals(ruudut[i + 6].getText())) {

                    // JOptionPane.showMessageDialog(null, "Peli loppui "+pelaaja+":n voittoon.");
                    return true;
                }
            }
        }

        for (int i = 0; i < 9; i += 3) {

            if (ruudut[i].isEnabled() == false) {
                String verrattava = ruudut[i].getText();
                //String pelaaja = ruudut[i].getText();
                if (verrattava.equals(ruudut[i + 1].getText()) && verrattava.equals(ruudut[i + 2].getText())) {

                    // JOptionPane.showMessageDialog(null, "Peli loppui "+pelaaja+":n voittoon.");
                    return true;
                }
            }
        }

        if (ruudut[0].isEnabled() == false) {
            String verrattava = ruudut[0].getText();
            //String pelaaja = ruudut[0].getText();
            if (verrattava.equals(ruudut[4].getText()) && verrattava.equals(ruudut[8].getText())) {

                // JOptionPane.showMessageDialog(null, "Peli loppui "+pelaaja+":n voittoon.");
                return true;
            }
        }

        if (ruudut[2].isEnabled() == false) {
            String verrattava = ruudut[2].getText();
            //String pelaaja = ruudut[2].getText();
            if (verrattava.equals(ruudut[4].getText()) && verrattava.equals(ruudut[6].getText())) {

                // JOptionPane.showMessageDialog(null, "Peli loppui "+pelaaja+":n voittoon.");
                return true;
            }
        }
        return false;

    }

    /**
     *
     * Pelin loppuessa kysytään jatketaanko pelaamista.
     */
    public void peliLoppui() {

        JDialog.setDefaultLookAndFeelDecorated(true);
        int valinta;
        valinta = JOptionPane.showConfirmDialog(null, "Pelataanko uusi peli?", "Peli loppui",
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


