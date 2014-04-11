package ristinolla;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author markolai@cs
 */
public final class Logiikka extends JFrame {

    private static String[] pelialusta = new String[9];

    static int laskuri = 0;
    static int merkit = 0;
    static int ristinVoitot;
    static int nollanVoitot;

    public Logiikka() {

        uusiPeli();

    }

    public void uusiPeli() {

        for (int i = 0; i < pelialusta.length; i++) {
            this.pelialusta[i] = null;
        }

    }

    public void asetaMerkkiRuutuunUusi(int i) {

        laskuri++;
        if (merkit % 2 == 0) {
            pelialusta[i] = "X";
        } else {
            pelialusta[i] = "0";
        }

//        ui.UI.maalaaRuutu(ruutu, ruutu.getText());
    }

    /**
     *
     * Kasvatetaan laskuria ja asetetaan ruutuun vuorossa olevan pelaajan risti
     * tai nolla pelimerkki.
     *
     * @param ruutu
     * @param ruudut
     */
    public void asetaMerkkiRuutuun(JButton ruutu, JButton[] ruudut) {

        laskuri++;
        if (merkit % 2 == 0) {
            ruutu.setText("X");
        } else {
            ruutu.setText("O");
        }
        ui.UI.maalaaRuutu(ruutu, ruutu.getText());
    }

    /**
     *
     * Päivitetään pelitilanne tarkistamalla, jatkuuko peli, ja jos toinen
     * pelaaja on voittanut tai peli on päättynyt tasapeliin ilmoitetaan
     * tilanteesta, muutoin jatketaan.
     *
     * @param ruudut
     */
    

    public void paivita(JButton[] ruudut) {

        int kumpiVoitti = merkit % 2;
        merkit++;

        if (tulikoTasapeli(ruudut, laskuri)) {
            ui.UI.ilmoitaTasapeli();
            peliLoppui(ruudut);
        }

        if (voittikoPelaaja(ruudut)) {

            if (kumpiVoitti == 0) {

                ristiVoitti(ruudut);

            } else {

                nollaVoitti(ruudut);
            }
        }
    }

    /**
     *
     * Tarkistetaan onko peli loppunut tasapeliin pelialustan täytyttyä ennen
     * kummankaan voittoa.
     *
     */
    private static boolean tulikoTasapeli(JButton[] ruudut, int laskuri) {
        return voittikoPelaaja(ruudut) == false && laskuri == ruudut.length;

    }

    public static void ristiVoitti(JButton[] ruudut) {
        ristinVoitot++;
        ui.UI.ilmoitaRistinVoitto();
        peliLoppui(ruudut);
    }

    public static void nollaVoitti(JButton[] ruudut) {
        nollanVoitot++;
        ui.UI.ilmoitaNollanVoitto();
        peliLoppui(ruudut);
    }

    /**
     *
     * Tarkistetaan onko kumpikaan pelaajista voittanut peliä viimeisimmällä
     * siirrollaan.
     *
     * @param ruudut
     * @return
     */
    public static boolean voittikoPelaaja(JButton[] ruudut) {

        if (merkit >= 3) {

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

        }
        return false;

    }

    /**
     * Palautetaan ristiä pelaavan pelaajan voittojen määrä.
     *
     * @return
     */
    public static int getRistinVoitot() {

        return ristinVoitot;
    }

    /**
     * Palautetaan nollaa pelaavan pelaajan voittojen määrä.
     *
     * @return
     */
    public static int getNollanVoitot() {

        return nollanVoitot;
    }

    public static int getMerkit() {

        return merkit;
    }

    public static int getLaskuri() {

        return laskuri;
    }

    public static String getPelivuorossa() {

        if (merkit % 2 == 0) {

            return "X";
        }
        return "0";

    }
    
    public String getRuudunMerkki(int ruutu) {
        
        return pelialusta[ruutu];
    }

    /**
     *
     * Pelin loppuessa (voittoon tai tasapeliin) kysytään käyttäjältä
     * jatketaanko pelaamista ja joko alustetaan pelilauta tai lopetetaan.
     *
     * @param ruudut
     */
    public static void peliLoppui(JButton[] ruudut) {

        if (ui.UI.pelataankoLisaa()) {
            alustaPelilauta(ruudut);
        } else {
            System.exit(0);
        }
    }

    /**
     *
     * Alustetaan pelilauta uudelleen ja apumuuttujat uutta peliä varten.
     *
     * @param ruudut
     */
    public static void alustaPelilauta(JButton[] ruudut) {

        laskuri = 0;
        merkit = 0;
        ui.UI.alustaPeli(ruudut);

    }

}
