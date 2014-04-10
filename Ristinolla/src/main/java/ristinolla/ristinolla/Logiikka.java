package ristinolla.ristinolla;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author markolai@cs
 */
public final class Logiikka extends JFrame {
//       public JButton[] ruudut;

    static int laskuri;
    static int merkit;
    static String uusiRivi = System.lineSeparator();
    static int ristinVoitot;
    static int nollanVoitot;
    
    public Logiikka() {
        
        merkit = 0;
    }

    public static void paivita(JButton[] ruudut, int laskuri) {

        tulikoTasapeli(ruudut, laskuri);

        if (voittikoPelaaja(ruudut)) {

            if (merkit % 2 == 0) {
                ristinVoitot++;
                JOptionPane.showMessageDialog(null, "Ristiä pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: " + ristinVoitot + ". Nollan voitot: " + nollanVoitot + ".", "Risti voitti!", JOptionPane.INFORMATION_MESSAGE);
                peliLoppui(ruudut);

            } else {
                nollanVoitot++;
                JOptionPane.showMessageDialog(null, "Nollaa pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: " + ristinVoitot + ". Nollan voitot: " + nollanVoitot + ".", "Nolla voitti!", JOptionPane.INFORMATION_MESSAGE);
                peliLoppui(ruudut);
            }
        }

        merkit++;
    }

    private static void tulikoTasapeli(JButton[] ruudut, int merkit) {

        if (voittikoPelaaja(ruudut) == false && laskuri == ruudut.length) {
            JOptionPane.showMessageDialog(null, "Peli loppui tasapeliin." + uusiRivi + "Ristin voitot: " + ristinVoitot + ". Nollan voitot: " + nollanVoitot + ".", "Tasapeli!", JOptionPane.INFORMATION_MESSAGE);
            peliLoppui(ruudut);
        }

    }

//    public int getRistinVoitot() {
//
//        return ristinVoitot;
//
//    }
//
//    public int getNollanVoitot() {
//
//        return nollanVoitot;
//    }
    /**
     *
     * Pelin loppuessa kysytään jatketaanko pelaamista.
     *
     * @param ruudut
     * @return
     */
    public static boolean voittikoPelaaja(JButton[] ruudut) {

        for (int i = 0; i < 3; i++) {

            if (ruudut[i].isEnabled() == false) {
                String verrattava = ruudut[i].getText();
                if (verrattava.equals(ruudut[i + 3].getText()) && verrattava.equals(ruudut[i + 6].getText())) {

                    return true;
                }
            }
        }

        for (int i = 0; i < 9; i += 3) {

            if (ruudut[i].isEnabled() == false) {
                String verrattava = ruudut[i].getText();
                if (verrattava.equals(ruudut[i + 1].getText()) && verrattava.equals(ruudut[i + 2].getText())) {

                    return true;
                }
            }
        }

        if (ruudut[0].isEnabled() == false) {
            String verrattava = ruudut[0].getText();
            if (verrattava.equals(ruudut[4].getText()) && verrattava.equals(ruudut[8].getText())) {

                return true;
            }
        }

        if (ruudut[2].isEnabled() == false) {
            String verrattava = ruudut[2].getText();
            if (verrattava.equals(ruudut[4].getText()) && verrattava.equals(ruudut[6].getText())) {

                return true;
            }
        }
        return false;

    }

    /**
     *
     * Pelin loppuessa kysytään jatketaanko pelaamista.
     *
     * @param ruudut
     */
    public static void peliLoppui(JButton[] ruudut) {

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

    public static void asetaMerkkiRuutuun(JButton ruutu, JButton[] ruudut) {

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
