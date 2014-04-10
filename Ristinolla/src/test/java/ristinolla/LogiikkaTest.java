package ristinolla;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

    private Ristinolla testipeli;

    @Before
    public void setUp() {

        testipeli = new Ristinolla();

    }

    @Test
    public void hello() {
    }
}
