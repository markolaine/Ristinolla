package ristinolla;

/**
 *
 * @author markolai@cs
 */
public final class Logiikka {

    public final String[] pelialusta;

    public int pelivuoro;
    private int ristinVoitot;
    private int nollanVoitot;
    public boolean tasapeli;
    public boolean pelaajaVoitti;
    public String voittaja;

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
            pelialusta[i] = null;
        }

        pelivuoro = 0;
        tasapeli = false;
        pelaajaVoitti = false;
        voittaja = null;

    }
    
    public void resetoiVoitot() {
        
        ristinVoitot = 0;
        nollanVoitot = 0;
    }

    /**
     *
     * Kasvatetaan laskuria ja asetetaan ruutuun vuorossa olevan pelaajan risti
     * tai nolla pelimerkki.
     *
     * @param i
     */
    public void asetaMerkkiRuutuun(int i) {
        
        pelivuoro++;

        if (pelialusta[i] != null) {
            throw new IllegalArgumentException("Ruutu on jo pelattu.");
        }
        
        if ("X".equals(getPelivuorossa())) {
            
            pelialusta[i] = "X";
        } else if ("0".equals(getPelivuorossa())) {
            pelialusta[i] = "0";
        }
        

    }

    public void tarkistaLoppuiko() {

        voittikoPelaaja();

        if (voittaja != null) {

            pelaajaVoitti = true;

            kasvataVoittoja(getPelivuorossa());

        }
        
            if (tulikoTasapeli()) {
            tasapeli = true;
        }

    }

    /**
     *
     * Tarkistetaan onko peli loppunut tasapeliin pelialustan täytyttyä ennen
     * kummankaan voittoa.
     *
     * @return
     */
    public boolean tulikoTasapeli() {

        return (voittaja == null) && pelivuoro == pelialusta.length;

    }

    /**
     *
     * Tarkistetaan onko kumpikaan pelaajista voittanut peliä viimeisimmällä
     * siirrollaan.
     *
     */
    public void voittikoPelaaja() {

        if (pelivuoro >= 5) {

            for (int i = 0; i < 3; i++) {

                if (pelialusta[i] != null) {
                    String verrattava = pelialusta[i];
                    if (verrattava.equals(pelialusta[i + 3]) && verrattava.equals(pelialusta[i + 6])) {

                        voittaja = verrattava;
                    }
                }
            }

            for (int i = 0; i < 9; i += 3) {

                if (pelialusta[i] != null) {
                    String verrattava = pelialusta[i];
                    if (verrattava.equals(pelialusta[i + 1]) && verrattava.equals(pelialusta[i + 2])) {

                        voittaja = verrattava;
                    }
                }
            }

            if (pelialusta[0] != null) {
                String verrattava = pelialusta[0];
                if (verrattava.equals(pelialusta[4]) && verrattava.equals(pelialusta[8])) {

                    voittaja = verrattava;
                }
            }

            if (pelialusta[2] != null) {
                String verrattava = pelialusta[2];
                if (verrattava.equals(pelialusta[4]) && verrattava.equals(pelialusta[6])) {

                    voittaja = verrattava;
                }
            }

        }

    }

    /**
     * Palautetaan ristiä pelaavan pelaajan voittojen määrä.
     *
     * @return
     */
    public int getRistinVoitot() {

        return ristinVoitot;
    }

    /**
     * Palautetaan nollaa pelaavan pelaajan voittojen määrä.
     *
     * @return
     */
    public int getNollanVoitot() {

        return nollanVoitot;
    }

    public String getPelivuorossa() {

        if (pelivuoro % 2 != 0) {

            return "X";
        }
        return "0";

    }

    public String getRuudunMerkki(int ruutu) {

        return pelialusta[ruutu];
    }

    public void kasvataVoittoja(String pelivuorossa) {
        if ("X".equals(pelivuorossa)) {
            ristinVoitot++;
        } else if ("0".equals(pelivuorossa)) {
            nollanVoitot++;
        }
    }
}