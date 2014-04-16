package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import ristinolla.Logiikka;

/**
 *
 * @author marlai
 */
public class UI implements Runnable {

    private final Logiikka peli;
    private final JFrame UI = new JFrame();
    private JButton[] ruudut;
    private JPanel pelilauta;
    private JLabel pelivuorossa;

    /**
     *
     * Graafisen käyttöliittymän asetukset kuntoon.
     *
     * @param peli
     */
    public UI(Logiikka peli) {

        JOptionPane.showMessageDialog(null, "Kahden pelaajan ristinolla, x aloittaa!", "Ristinolla", JOptionPane.INFORMATION_MESSAGE);

        this.peli = peli;
        UI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UI.setVisible(true);
        UI.setResizable(true);
        UI.setSize(500, 500);
        Dimension ruudunKoko = Toolkit.getDefaultToolkit().getScreenSize();
        UI.setLocation(ruudunKoko.width / 2 - UI.getSize().width / 2, ruudunKoko.height / 2 - UI.getSize().height / 2);

    }

    @Override
    public void run() {
        uusiUI(this.UI.getContentPane());
    }

    private void uusiUI(Container container) {

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Menu");
        menuBar.add(menu);

        JMenuItem uusiPeli = new JMenuItem("Aloita peli alusta", KeyEvent.VK_F4);
        uusiPeli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        uusiPeli.setActionCommand("UUSIPELI");
        uusiPeli.addActionListener(new Kuuntelija(this, this.peli));
        menu.add(uusiPeli);
        menu.addSeparator();

        JMenuItem uusiPeliJaVoitot = new JMenuItem("Aloita peli alusta ja nollaa voitot", KeyEvent.VK_F5);
        uusiPeliJaVoitot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        uusiPeliJaVoitot.setActionCommand("UUSIPELIJAVOITOT");
        uusiPeliJaVoitot.addActionListener(new Kuuntelija(this, this.peli));
        menu.add(uusiPeliJaVoitot);
        menu.addSeparator();

        JMenuItem lopeta = new JMenuItem("Lopeta", KeyEvent.VK_F1);
        lopeta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        lopeta.setActionCommand("LOPETA");
        lopeta.addActionListener(new Kuuntelija(this, this.peli));
        menu.add(lopeta);

        pelilauta = new JPanel();
        pelilauta.setLayout(new GridLayout(3, 3));
        UI.add(pelilauta);

        ruudut = new JButton[9];

        for (int i = 0; i < ruudut.length; i++) {

            ruudut[i] = new JButton();
            pelilauta.add(ruudut[i]);
            ruudut[i].addActionListener(new Kuuntelija(this, this.peli, i));
            ruudut[i].setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
            ruudut[i].setFont(new Font("Dialog", Font.BOLD, 30));
        }

        nollaaPelilauta();

        JPanel alareuna = new JPanel();
        GridLayout alareunaL = new GridLayout(1, 3);
        alareuna.setLayout(alareunaL);
        this.pelivuorossa = new JLabel("Pelivuorossa: X");
        this.pelivuorossa.setHorizontalAlignment(JLabel.LEFT);
        alareuna.add(this.pelivuorossa);

        container.add(menuBar, BorderLayout.NORTH);
        container.add(pelilauta, BorderLayout.CENTER);
        container.add(alareuna, BorderLayout.SOUTH);
    }

    /**
     *
     * Alustetaan peliruudut uutta peliä varten.
     *
     */
    public void nollaaPelilauta() {

        for (JButton ruutu : ruudut) {
            ruutu.setText("");
            ruutu.setBackground(Color.DARK_GRAY);
            ruutu.setEnabled(true);
        }
    }

    public void paivita() {

        for (int i = 0; i < ruudut.length; i++) {

            if ("X".equals(peli.getRuudunMerkki(i)) || "0".equals(peli.getRuudunMerkki(i))) {

                maalaaRuutu(ruudut[i], peli.getRuudunMerkki(i));
            }

        }

        paivitaVoitto();
        if ("X".equals(peli.getPelivuorossa())) {
            this.pelivuorossa.setText("Pelivuorossa: 0");
        } else {
            this.pelivuorossa.setText("Pelivuorossa: X");
        }
    }

    public void paivitaVoitto() {

        String pelaaja = peli.getPelivuorossa();
        String uusiRivi = System.lineSeparator();

        if (peli.loppuikoTasapeliin()) {
            JOptionPane.showMessageDialog(null, "Peli loppui tasapeliin." + uusiRivi + "Ristin voitot: " + peli.getRistinVoitot() + ". Nollan voitot: " + peli.getNollanVoitot() + ".", "Tasapeli!", JOptionPane.INFORMATION_MESSAGE);
        }

        if (peli.loppuikoVoittoon()) {

            if ("X".equals(pelaaja)) {
                JOptionPane.showMessageDialog(null, "Ristiä pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: " + peli.getRistinVoitot() + ". Nollan voitot: " + peli.getNollanVoitot() + ".", "Risti voitti!", JOptionPane.INFORMATION_MESSAGE);
            }
            if ("0".equals(pelaaja)) {
                JOptionPane.showMessageDialog(null, "Nollaa pelannut voitti." + uusiRivi + uusiRivi + "Ristin voitot: " + peli.getRistinVoitot() + ". Nollan voitot: " + peli.getNollanVoitot() + ".", "Nolla voitti!", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        if (peli.loppuiko()) {

            if (pelataankoLisaa()) {
                peli.uusiPeli();
                nollaaPelilauta();
            } else {
                JOptionPane.showMessageDialog(null, "Kiitos pelaamisesta.", "Näkemiin", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }

    }

    /**
     *
     * Maalataan valittu ruutu ja estetään klikkaaminen uudestaan.
     *
     * @param ruutu
     * @param merkki
     */
    public void maalaaRuutu(JButton ruutu, String merkki) {

        ruutu.setEnabled(false);
        ruutu.setText(merkki);

        if (merkki.equals("X")) {
            ruutu.setBackground(Color.blue);
        } else {

            ruutu.setBackground(Color.red);
        }
    }

    /**
     *
     * Kysytäänkö käyttäjältä pelataanko lisää.
     *
     * @return
     */
    public boolean pelataankoLisaa() {

        JDialog.setDefaultLookAndFeelDecorated(true);
        int valinta;

        valinta = JOptionPane.showConfirmDialog(null, "Jatketaanko pelaamista?", "Peli loppui",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (valinta == JOptionPane.YES_OPTION) {
            return true;
        } else if (valinta == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;
    }

}
