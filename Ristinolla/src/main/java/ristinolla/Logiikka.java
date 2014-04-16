package ristinolla;

/**
 *
 * @author markolai@cs
 */
public final class Logiikka {

    public final String[] pelialusta;

    private int pelivuoro;
    private int ristinVoitot;
    private int nollanVoitot;
    public boolean tasapeli;
    private boolean pelaajaVoitti;
    private String voittaja;

    public Logiikka() {

        this.pelialusta = new String[9];

    }

    /**
     *
     * Alustetaan pelilauta uudelleen ja apumuuttujat uutta peliä varten.
     *
     */
    public void uusiPeli() {

        for (int i = 0; i < pelialusta.length; i++) {
            this.pelialusta[i] = null;
        }

        this.pelivuoro = 0;
        this.tasapeli = false;
        this.pelaajaVoitti = false;
        this.voittaja = null;

    }

    public void resetoiVoitot() {

        this.ristinVoitot = 0;
        this.nollanVoitot = 0;
    }

    /**
     *
     * Kasvatetaan laskuria ja asetetaan ruutuun vuorossa olevan pelaajan risti
     * tai nolla pelimerkki.
     *
     * @param i
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

    public void tarkistaLoppuiko() {

        voittikoPelaaja();

        if (this.voittaja != null) {

            this.pelaajaVoitti = true;

            kasvataVoittoja(this.getPelivuorossa());

        }

        if (loppuikoTasapeliin()) {
            this.tasapeli = true;
        }

    }

    /**
     *
     * Tarkistetaan onko peli loppunut tasapeliin pelialustan täytyttyä ennen
     * kummankaan voittoa.
     *
     * @return
     */
    public boolean loppuikoTasapeliin() {

        return (this.voittaja == null) && this.pelivuoro == this.pelialusta.length;

    }

    public boolean loppuikoVoittoon() {

        return this.voittaja != null;
    }
    
    public boolean loppuiko() {
        
        return loppuikoTasapeliin() || loppuikoVoittoon();
    }

    /**
     *
     * Tarkistetaan onko kumpikaan pelaajista voittanut peliä viimeisimmällä
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

    private void kasvataVoittoja(String pelivuorossa) {
        if ("X".equals(pelivuorossa)) {
            this.ristinVoitot++;
        } else if ("0".equals(pelivuorossa)) {
            this.nollanVoitot++;
        }
    }

    /**
     * Palautetaan ristiä pelaavan pelaajan voittojen määrä.
     *
     * @return
     */
    public int getRistinVoitot() {

        return this.ristinVoitot;
    }

    /**
     * Palautetaan nollaa pelaavan pelaajan voittojen määrä.
     *
     * @return
     */
    public int getNollanVoitot() {

        return this.nollanVoitot;
    }

    public String getPelivuorossa() {

        if (this.pelivuoro % 2 != 0) {

            return "X";
        }
        return "0";

    }

    public String getRuudunMerkki(int ruutu) {

        return this.pelialusta[ruutu];
    }

    public int getPelivuoro() {

        return this.pelivuoro;
    }

}
