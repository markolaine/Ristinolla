/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Käyttöliittymä;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import ristinolla.ristinolla.Logiikka;

/**
 *
 * @author marlai
 */
public class UI implements Runnable, ActionListener {

    public Logiikka peli;
    public JFrame UI = new JFrame();
    public JButton[] ruudut;
    public JPanel pelilauta;

    /**
     *
     * Graafisen käyttöliittymän asetukset kuntoon.
     *
     * @param peli
     */
    public UI(Logiikka peli) {

        this.peli = peli;
        UI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UI.setVisible(true);
        UI.setResizable(true);
        UI.setSize(500, 500);
        Dimension ruudunKoko = Toolkit.getDefaultToolkit().getScreenSize();
        UI.setLocation(ruudunKoko.width / 2 - peli.getSize().width / 2, ruudunKoko.height / 2 - peli.getSize().height / 2);

    }

    @Override
    public void run() {
        uusiUI();
    }

    private void uusiUI() {
        pelilauta = new JPanel();
        pelilauta.setLayout(new GridLayout(3, 3));
        UI.add(pelilauta);
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
    }

    public static void alustaPeli(JButton[] ruudut) {
        for (JButton ruutu : ruudut) {
            ruutu.setText("");
            ruutu.setBackground(Color.GRAY);
            ruutu.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (JButton ruutu : ruudut) {
            if (ruutu == e.getSource()) {
                ristinolla.ristinolla.Logiikka.asetaMerkkiRuutuun(ruutu, ruudut);
            }
        }
        ristinolla.ristinolla.Logiikka.paivita(ruudut);
    }

    /**
     * Värjätään ruutu, johon risti asetettiin siniseksi.
     *
     * @param ruutu
     */
    public static void ristiAsetettu(JButton ruutu) {
        ruutu.setBackground(Color.blue);
        ruutu.setEnabled(false);
    }

    /**
     * Värjätään ruutu, johon nolla asetettiin punaiseksi.
     *
     * @param ruutu
     */
    public static void nollaAsetettu(JButton ruutu) {
        ruutu.setBackground(Color.red);
        ruutu.setEnabled(false);
    }

    /**
     *
     * Ilmoitetaan käyttäjälle tasapelista.
     *
     */
    public static void ilmoitaTasapeli() {
        JOptionPane.showMessageDialog(null, "Peli loppui tasapeliin." + uusiRivi + "Ristin voitot: " + Logiikka.getRistinVoitot() + ". Nollan voitot: " + Logiikka.getNollanVoitot() + ".", "Tasapeli!", JOptionPane.INFORMATION_MESSAGE);
    }
    static String uusiRivi = System.lineSeparator();

    /**
     *
     * Ilmoitetaan käyttäjälle ristin voitosta.
     *
     */
    public static void ilmoitaRistinVoitto() {
        JOptionPane.showMessageDialog(null, "Ristiä pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: " + Logiikka.getRistinVoitot() + ". Nollan voitot: " + Logiikka.getNollanVoitot() + ".", "Risti voitti!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * Ilmoitetaan käyttäjälle nollan voitosta.
     *
     */
    public static void ilmoitaNollanVoitto() {
        JOptionPane.showMessageDialog(null, "Nollaa pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: " + Logiikka.getRistinVoitot() + ". Nollan voitot: " + Logiikka.getNollanVoitot() + ".", "Nolla voitti!", JOptionPane.INFORMATION_MESSAGE);
    }

     /**
     *
     * Kysytäänkö käyttäjältä pelataanko lisää.
     *
     * @return 
     */
    public static boolean pelataankoLisaa() {

        JDialog.setDefaultLookAndFeelDecorated(true);
        int valinta;

        valinta = JOptionPane.showConfirmDialog(null, "Pelataanko uusi peli?", "Peli loppui",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (valinta == JOptionPane.YES_OPTION) {
            return true;
        } else if (valinta == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;
    }

}
