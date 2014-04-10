/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Käyttöliittymä;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import Ristinolla.Logiikka;

/**
 *
 * @author marlai
 */
public class UI implements Runnable, ActionListener {

    public Logiikka peli;
    public JFrame UI = new JFrame();
    public JButton[] ruudut;
    public JPanel pelilauta;


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

    @Override
    public void actionPerformed(ActionEvent e) {

        for (JButton ruutu : ruudut) {
            if (ruutu == e.getSource()) {
                Ristinolla.Logiikka.asetaMerkkiRuutuun(ruutu, ruudut);
            }
        }
        Ristinolla.Logiikka.paivita(ruudut);
    }

}