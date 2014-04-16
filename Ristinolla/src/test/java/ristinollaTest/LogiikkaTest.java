package ristinollaTest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ristinolla.Logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author markolai@cs
 */
public class LogiikkaTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    private Logiikka testipeli;

    @Before
    public void setUp() {

        testipeli = new Logiikka();

    }

    @Test
    public void ovatkoRistinVoitotAluksiNolla() {

        Assert.assertEquals(0, testipeli.getRistinVoitot());
    }

    @Test
    public void ovatkoNollanVoitotAluksiNolla() {

        Assert.assertEquals(0, testipeli.getNollanVoitot());
    }

    @Test
    public void ristiAloittaa() {
        testipeli.asetaMerkkiRuutuun(0);
        Assert.assertEquals("X", testipeli.getPelivuorossa());
    }

    @Test
    public void merkitAluksiNolla() {

        Assert.assertEquals(0, testipeli.getPelivuoro());
    }

    @Test
    public void pelivuoroAluksiNolla() {

        Assert.assertEquals(0, testipeli.getPelivuoro());
    }

    @Test
    public void ruudutAluksiNull() {

        Assert.assertEquals(null, testipeli.getRuudunMerkki(0));
        Assert.assertEquals(null, testipeli.getRuudunMerkki(6));
        Assert.assertEquals(null, testipeli.getRuudunMerkki(8));
    }

    @Test
    public void ruudunMerkkiPalautetaanOikein() {

        int ruutu1 = 0;
        int ruutu2 = 1;

        testipeli.asetaMerkkiRuutuun(ruutu1);
        testipeli.asetaMerkkiRuutuun(ruutu2);

        Assert.assertEquals("X", testipeli.getRuudunMerkki(ruutu1));
        Assert.assertEquals("0", testipeli.getRuudunMerkki(ruutu2));
    }

    @Test
    public void toimiikoTasapeli() {

        testipeli.asetaMerkkiRuutuun(0);
        testipeli.asetaMerkkiRuutuun(1);
        testipeli.asetaMerkkiRuutuun(3);
        testipeli.asetaMerkkiRuutuun(4);
        testipeli.asetaMerkkiRuutuun(7);
        testipeli.asetaMerkkiRuutuun(6);
        testipeli.asetaMerkkiRuutuun(2);
        testipeli.asetaMerkkiRuutuun(5);
        testipeli.asetaMerkkiRuutuun(8);
        testipeli.tarkistaLoppuiko();

        Assert.assertEquals(true, testipeli.tasapeli);

    }

    @Test
    public void toimiikoRistinVoitto() {

        testipeli.asetaMerkkiRuutuun(0);
        testipeli.asetaMerkkiRuutuun(1);
        testipeli.asetaMerkkiRuutuun(3);
        testipeli.asetaMerkkiRuutuun(4);
        testipeli.asetaMerkkiRuutuun(6);
        testipeli.tarkistaLoppuiko();

        Assert.assertEquals(0, testipeli.getNollanVoitot());
        Assert.assertEquals(1, testipeli.getRistinVoitot());

    }

    @Test
    public void toimiikoNollanVoitto() {

        testipeli.asetaMerkkiRuutuun(0);
        testipeli.asetaMerkkiRuutuun(1);
        testipeli.asetaMerkkiRuutuun(3);
        testipeli.asetaMerkkiRuutuun(4);
        testipeli.asetaMerkkiRuutuun(2);
        testipeli.asetaMerkkiRuutuun(7);
        testipeli.tarkistaLoppuiko();

        Assert.assertEquals(0, testipeli.getRistinVoitot());
        Assert.assertEquals(1, testipeli.getNollanVoitot());

    }

    @Test
    public void uusiPeliToimii() {

        int ruutu1 = 0;
        int ruutu2 = 1;

        testipeli.asetaMerkkiRuutuun(ruutu1);
        testipeli.asetaMerkkiRuutuun(ruutu2);
        testipeli.uusiPeli();

        Assert.assertEquals(null, testipeli.getRuudunMerkki(ruutu1));
        Assert.assertEquals(null, testipeli.getRuudunMerkki(ruutu2));

    }
    

    @Test
    public void voittojenResetointiToimii() {
        
        testipeli.kasvataVoittoja("X");
        testipeli.kasvataVoittoja("0");
        
        testipeli.resetoiVoitot();
        
        Assert.assertEquals(0, testipeli.getRistinVoitot());
        Assert.assertEquals(0, testipeli.getNollanVoitot());
    }
}
