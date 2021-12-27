/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analiseseries;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raulcoelho
 */
public class AnaliseseriesTest {

    public AnaliseseriesTest() {
    }

    /**
     * Test of ler_ficheiro method, of class Analiseseries.
     */
    @Test
    public void testLer_ficheiro() throws Exception {
        System.out.println("ler_ficheiro");
        int[][] ficheiro = new int[30000][7];
        String args = "DAYTON.csv";
        int expResult = 22680;
        int result = Analiseseries.ler_ficheiro(ficheiro,args);
        assertEquals(expResult, result);
    }

    /**
     * Test of mergesort method, of class Analiseseries.
     */
    @Test
    public void testMergesort() {
        System.out.println("mergesort");
        int[][] ana = {{3, 4, 34, 1, 3, 3, 1}, {3, 41, 2, 4, 5, 2, 1}, {3, 4, 1, 2, 5, 4, 4}};
        int lindex = 0;
        int hindex = 2;
        Analiseseries.mergesort(ana, lindex, hindex);
        assertEquals(1, ana[0][6]);
    }

    /**
     * Test of inverter method, of class Analiseseries.
     */
    @Test
    public void testInverter() {
        System.out.println("inverter");
        int valores = 3;
        int[][] copia = {{2, 1, 3, 2, 41, 2, 3}, {3, 5, 2, 3, 5, 7, 3}, {3, 2, 5, 3, 6, 8, 6}};
        Analiseseries.inverter(valores, copia);
        assertEquals(6, copia[0][6]);
    }

    /**
     * Test of erro_medio_absoluto method, of class Analiseseries.
     */
    @Test
    public void testErro_medio_absoluto() {
        System.out.println("erro_medio_absoluto");
        int[] mmep2 = {24, 32};
        int numeroDatas = 6;
        int[][] ficheiro = {{2016, 2, 1, 22, 0, 0, 24}, {2016, 2, 3, 22, 0, 0, 32}};
        Analiseseries.erro_medio_absoluto(mmep2, numeroDatas, ficheiro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of passarZero method, of class Analiseseries.
     */
    @Test
    public void testPassarZero() {
        System.out.println("passarZero");
        int[][] fich = {{2016, 2, 2, 22, 0, 0, 2000}, {2016, 2, 3, 22, 0, 0, 2300}};
        Analiseseries.passarZero(fich);
        assertEquals(0, fich[0][0]);
    }

    /**
     * Test of separar method, of class Analiseseries.
     */
    @Test
    public void testSeparar() {
        System.out.println("separar");
        int[][] ficheiro = {{2016, 2, 3, 23, 0, 0, 1000}, {2016, 2, 4, 23, 0, 0, 1000}, {2016, 2, 5, 23, 0, 0, 1000}};
        int numeroDatas = 3;
        int extremo1 = 0;
        int extremo2 = 24;
        int j = 2;
        int[][] ana = {};
        int expResult = 2;
        int result = Analiseseries.separar2(ficheiro, numeroDatas, extremo1, extremo2, j, ana);
        assertEquals(expResult, result);
    }

    /**
     * Test of analisadata method, of class Analiseseries.
     */
    @Test
    public void testAnalisadata() {
        System.out.println("analisadata");
        int[][] ana = {{2016, 2, 2, 22, 0, 0, 2000}, {2016, 2, 3, 22, 0, 0, 1500}, {2016, 2, 4, 22, 0, 0, 1750}};
        int numeroDatas = 3;
        String prever = "4/2/2016";
        int d = 2;
        int m = 1;
        int a = 0;
        int expResult = 2;
        int result = Analiseseries.analisadata(ana, numeroDatas, prever, d, m, a);
        assertEquals(expResult, result);
    }

    /**
     * Test of analisadata2 method, of class Analiseseries.
     */
    @Test
    public void testAnalisadata2sem() {
        String dat = "04/02/2016";
        int[][] ficheiro = {{2016, 2, 2, 22, 0, 0, 2000}, {2016, 2, 3, 22, 0, 0, 1500}, {2016, 2, 4, 22, 0, 0, 1750}};
        int numeroDatas = 3;
        String tipo = "2";
        int expResult = 2;
        int result = Analiseseries.analisadata2sem(ficheiro, numeroDatas, tipo, dat);
        assertEquals(expResult, result);
    }

    /**
     * Test of numero_de_observaçoes method, of class Analiseseries.
     */
    @Test
    public void testNumero_de_observaçoes() throws Exception {
        System.out.println("numero_de_observa\u00e7oes");
        int[][] ficheiro = null;
        int numeroDatas = 0;
        Analiseseries.numero_de_observaçoes(ficheiro, numeroDatas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mediamovelsimplessem method, of class Analiseseries.
     */
    @Test
    public void testMediamovelsimplessem() throws Exception {
        int[][] ficheiro = {{2016, 2, 3, 23, 0, 0, 1000}, {2016, 2, 4, 23, 0, 0, 1000}, {2016, 2, 5, 23, 0, 0, 500}, {2016, 2, 6, 23, 0, 0, 800}};
        int numeroDatas = 4;
        int[] y = {};
        String[] args = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "2"};
        Analiseseries.mediamovelsimplessem(ficheiro, numeroDatas, y, args);
        assertEquals(650, y[3]);
    }

    /**
     * Test of media_movel_exponencialmente_pesadasem method, of class
     * Analiseseries.
     */
    @Test
    public void testMedia_movel_exponencialmente_pesadasem() throws Exception {
        int[][] ficheiro = {{2016, 2, 3, 23, 0, 0, 1000}, {2016, 2, 4, 23, 0, 0, 1000}, {2016, 2, 5, 23, 0, 0, 500}};
        int numeroDatas = 3;
        int[] mmep2 = {};
        String[] args = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0.2"};
        Analiseseries.media_movel_exponencialmente_pesadasem(ficheiro, numeroDatas, mmep2, args);
        assertEquals(900, mmep2[2]);
    }

    /**
     * Test of mediamovelsimplesprevisaosem method, of class Analiseseries.
     */
    @Test
    public void testMediamovelsimplesprevisaosem() throws Exception {
        System.out.println("mediamovelsimplesprevisaosem");
        int[][] ficheiro = {{2016, 2, 3, 23, 0, 0, 1000}, {2016, 2, 4, 23, 0, 0, 1000}, {2016, 2, 5, 23, 0, 0, 500}};
        int numeroDatas = 3;
        int[] y = {};
        String tipo = "2";
        String args = "2";
        String dat = "5/2/2016";
        Analiseseries.mediamovelsimplesprevisaosem(ficheiro, numeroDatas, y, tipo, args, dat);
        assertEquals(1000, y[2]);
    }

    /**
     * Test of media_exponencialmente_pesada_previsaosem method, of class
     * Analiseseries.
     */
    @Test
    public void testMedia_exponencialmente_pesada_previsaosem() throws Exception {
        System.out.println("media_exponencialmente_pesada_previsaosem");
        int[][] ficheiro = {{2016, 2, 3, 23, 0, 0, 1000}, {2016, 2, 4, 23, 0, 0, 1000}, {2016, 2, 5, 23, 0, 0, 500}};
        int numeroDatas = 3;
        int[] mmep2 = {};
        String tipo = "2";
        String args = "0.2";
        String dat = "5/2/2016";
        Analiseseries.media_exponencialmente_pesada_previsaosem(ficheiro, numeroDatas, mmep2, tipo, args, dat);
        assertEquals(900, mmep2[2]);
    }

    /**
     * Test of cortarmes method, of class Analiseseries.
     */
    @Test
    public void testCortarmes() {
        System.out.println("cortarmes");
        String args = "022017";
        String expResult = "00/02/2017";
        String result = Analiseseries.cortarmes(args);
        assertEquals(expResult, result);
    }

    /**
     * Test of cortardia method, of class Analiseseries.
     */
    @Test
    public void testCortardia() {
        System.out.println("cortardia");
        String args = "20022017";
        String expResult = "20/02/2017";
        String result = Analiseseries.cortardia(args);
        assertEquals(expResult, result);
    }

}
