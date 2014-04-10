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
        
        Assert.assertEquals(0, testipeli.getMerkit()%2);
    }
    
   @Test
    public void laskuriAluksiNolla() {
        
        Assert.assertEquals(0, testipeli.getLaskuri());
    }
    
     @Test
    public void merkitAluksiNolla() {
        
        Assert.assertEquals(0, testipeli.getMerkit());
    }
    
}
