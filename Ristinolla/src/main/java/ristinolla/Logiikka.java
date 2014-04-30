package ristinolla;

/**
 *
 * @author markolai@cs
 *
 * Luo pelialustan, asettaa sille valitun merkin, tarkistaa loppuiko peli ja
 * kumpi pelaaja voitti vai tuliko tasapeli. Pitää kirjaa pelivuorosta ja
 * voittojen määrästä sekä kasvattaa voittoja tarvittaessa.
 *
 */
public final class Logiikka {

    private final String[] pelialusta;
    /**
     * pelivuoro-laskuri, joka pitää kirjaa pelatuista vuoroista yhtä peliä
     * kohden.
     */
    private int pelivuoro;
    private int ristinVoitot;
    private int nollanVoitot;
    public boolean tasapeli;
    private String voittaja;

    public Logiikka() {

        this.pelialusta = new String[9];

    }

    /**
     *
     * Alustaa pelilaudan ja apumuuttujat uutta peliä varten.
     *
     */
    public void uusiPeli() {

        for (int i = 0; i < pelialusta.length; i++) {
            this.pelialusta[i] = null;
        }

        this.pelivuoro = 0;
        this.tasapeli = false;
        this.voittaja = null;

    }

    /**
     *
     * Alustaa voitot nollaksi.
     *
     */
    public void resetoiVoitot() {

        this.ristinVoitot = 0;
        this.nollanVoitot = 0;
    }

    /**
     *
     * Kasvatetaan pelivuorolaskuria ja asetetaan ruutuun vuorossa olevan
     * pelaajan pelimerkki, jos ruutu on tyhjä.
     *
     * @param i kertoo mihin ruutuun merkki asetetaan
     */
    public void asetaMerkkiRuutuun(int i) {

        this.pelivuoro++;

        if (this.pelialusta[i] != null) {
            throw new IllegalArgumentException("Ruutu on jo pelattu.");
        }

        if ("X".equals(getPelivuorossa())) {

            this.pelialusta[i] = "X";
        } else if ("0".equals(getPelivuorossa())) {
            this.pelialusta[i] = "0";
        }

    }

    /**
     *
     * Tarkistaa onko peli loppunut, ja kasvattaa voittoja tai asettaa pelin
     * päättyneeksi tasapeliin.
     *
     */
    public void tarkistaLoppuiko() {

        voittikoPelaaja();

        if (this.voittaja != null) {

            kasvataVoittoja(this.getPelivuorossa());

        }

        if (loppuikoTasapeliin()) {
            this.tasapeli = true;
        }

    }

    /**
     *
     * Tarkistaa onko peli loppunut tasapeliin pelialustan täytyttyä ennen
     * kummankaan voittoa.
     *
     * @return palauttaa tasapelin totuusarvon
     */
    public boolean loppuikoTasapeliin() {

        return (this.voittaja == null) && this.pelivuoro == this.pelialusta.length;

    }

    /**
     *
     * Tarkistaa onko peli loppunut voittoon.
     *
     * @return palauttaa voiton totuusarvon
     */
    public boolean loppuikoVoittoon() {

        return this.voittaja != null;
    }

    /**
     *
     * Tarkistaa onko peli loppunut tasapeliin tai voittoon.
     *
     * @return palauttaa totuusarvon, joka kertoo onko peli loppunut
     */
    public boolean loppuiko() {

        return loppuikoTasapeliin() || loppuikoVoittoon();
    }

    /**
     *
     * Tarkistaa onko kumpikaan pelaajista voittanut peliä viimeisimmällä
     * siirrollaan.
     *
     */
    public void voittikoPelaaja() {

        if (this.pelivuoro >= 5) {

            for (int i = 0; i < 3; i++) {

                if (this.pelialusta[i] != null) {
                    String verrattava = this.pelialusta[i];
                    if (verrattava.equals(this.pelialusta[i + 3]) && verrattava.equals(this.pelialusta[i + 6])) {

                        this.voittaja = verrattava;
                    }
                }
            }

            for (int i = 0; i < 9; i += 3) {

                if (this.pelialusta[i] != null) {
                    String verrattava = this.pelialusta[i];
                    if (verrattava.equals(this.pelialusta[i + 1]) && verrattava.equals(this.pelialusta[i + 2])) {

                        this.voittaja = verrattava;
                    }
                }
            }

            if (this.pelialusta[0] != null) {
                String verrattava = this.pelialusta[0];
                if (verrattava.equals(this.pelialusta[4]) && verrattava.equals(this.pelialusta[8])) {

                    this.voittaja = verrattava;
                }
            }

            if (this.pelialusta[2] != null) {
                String verrattava = this.pelialusta[2];
                if (verrattava.equals(this.pelialusta[4]) && verrattava.equals(this.pelialusta[6])) {

                    this.voittaja = verrattava;
                }
            }

        }

    }

    /**
     *
     * Kasvattaa voittaneen pelaajan voittojen määrää
     *
     * @param pelivuorossa kertoo pelivuorossa olevan pelaajan
     */
    private void kasvataVoittoja(String pelivuorossa) {
        if ("X".equals(pelivuorossa)) {
            this.ristinVoitot++;
        } else {
            this.nollanVoitot++;
        }
    }

    /**
     *
     * Palauttaa ristiä pelaavan pelaajan voittojen määrä.
     *
     * @return palauttaa ristin voitot
     */
    public int getRistinVoitot() {

        return this.ristinVoitot;
    }

    /**
     *
     * Palauttaa nollaa pelaavan pelaajan voittojen määrä.
     *
     * @return palauttaa nollan voitot
     */
    public int getNollanVoitot() {

        return this.nollanVoitot;
    }

    /**
     *
     * Palautetaan pelivuorossa olevan pelaajan merkki.
     *
     * @return palauttaa pelivuorossa olevan pelaajan merkin
     */
    public String getPelivuorossa() {

        if (this.pelivuoro % 2 != 0) {

            return "X";
        }
        return "0";

    }

    /**
     *
     * Palautetaan halutun ruudun merkki.
     *
     * @param ruutu kertoo halutun ruudun
     * @return palauttaa halutun ruudun merkin
     */
    public String getRuudunMerkki(int ruutu) {

        return this.pelialusta[ruutu];
    }

    /**
     *
     * Palautetaan pelivuoro-laskurin arvo.
     *
     * @return palauttaa pelivuoro-laskurin arvon
     */
    public int getPelivuoro() {

        return this.pelivuoro;
    }

}
