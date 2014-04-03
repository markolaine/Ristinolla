package ristinolla.ristinolla;

import java.awt.Color;
import javax.swing.JButton;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author markolai@cs
 */
public class RistinollaTest {

    public RistinollaTest() {
    }

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
        testipeli.uusiPeli();

    }

    @Test
    public void onkoEnsimmainenRuutuValittavissa() {

        Assert.assertEquals(true, testipeli.ruudut[0].isEnabled());
    }

    @Test
    public void onkoMuuRuutuValittavissa() {

        Assert.assertEquals(true, testipeli.ruudut[5].isEnabled());
    }

    @Test
    public void onkoViimeinenRuutuValittavissa() {

        Assert.assertEquals(true, testipeli.ruudut[testipeli.ruudut.length - 1].isEnabled());
    }

    @Test
    public void pelilautaNakyvilla() {

        Assert.assertEquals(true, testipeli.pelilauta.isVisible());
    }

    @Test
    public void onnistuukoPelinNollausEkalle() {

        testipeli.peliLoppui();
        Assert.assertEquals(true, testipeli.ruudut[0].isEnabled());
    }

    @Test
    public void onnistuukoPelinNollausVikalle() {

        testipeli.peliLoppui();
        Assert.assertEquals(true, testipeli.ruudut[testipeli.ruudut.length - 1].isEnabled());

    }

//    @Test
//    public void nollautuukoVarit() {
//
//        Color alussa = testipeli.ruudut[0].getBackground();
//        testipeli.peliLoppui();
//        Assert.assertEquals(alussa, testipeli.ruudut[0].getBackground());
//
//    }

    @Test
    public void nollautuukoRuutujenTekstit() {

        Assert.assertEquals("", testipeli.ruudut[0].getText());
    }

    @Test
    public void laskuriOnAlussaNolla() {

        Assert.assertEquals(0, testipeli.laskuri);
    }

    @Test
    public void merkitOnAlussaNolla() {

        Assert.assertEquals(0, testipeli.merkit);
    }

    @Test
    public void ristiAloittaa() {

        Assert.assertEquals(0, testipeli.merkit%2);
    }

}
