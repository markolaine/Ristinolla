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
    
    public RistinollaTest() {}
    
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
// 
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//    
    private Peli testipeli;
    @Before
    public void setUp() {
        
        testipeli = new Peli();
        testipeli.uusiPeli();

    }
    
    @Test
    public void onkoEnsimmainenRuutuValittavissa() {

        Assert.assertEquals(true, testipeli.ruudut[0].isEnabled());
    }
    
    public void onkoMuuRuutuValittavissa() {
            
        Assert.assertEquals(true, testipeli.ruudut[5].isEnabled());
    }
        
    public void onkoViimeinenRuutuValittavissa() {
            
        Assert.assertEquals(true, testipeli.ruudut[testipeli.ruudut.length].isEnabled());
    }
        
    public void pelilautaNakyvilla() {
        
        Assert.assertEquals(true, testipeli.pelilauta.isVisible());
    }
    
    public void onnistuukoPelinNollausEkalle() {
        
        testipeli.peliLoppui();
        Assert.assertEquals(true, testipeli.ruudut[0].isEnabled());
    }
    
    public void onnistuukoPelinNollausVikalle() {
        
        testipeli.peliLoppui();
        Assert.assertEquals(true, testipeli.ruudut[testipeli.ruudut.length].isEnabled());
      
    }
    
    public void nollautuukoVarit() {
        
        Color alussa = testipeli.ruudut[0].getBackground();
        testipeli.peliLoppui();
        Assert.assertEquals(alussa, testipeli.ruudut[0].getBackground());
        
    }

    
}
