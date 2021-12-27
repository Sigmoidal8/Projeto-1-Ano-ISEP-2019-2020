/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analiseseries;

/**
 *
 * @author Forno21
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tempo {

    static Scanner sc = new Scanner(System.in);
    static Scanner inte = new Scanner(System.in);
    static final int LINHAS = 30000;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Qual é o nome do ficheiro .csv onde estão os dados?");
        String ficheiro = sc.nextLine();
        int[][] informacaoFicheiro = new int[LINHAS][7];
        int i = 0;
        File file = new File(ficheiro);
        Scanner leituraFicheiro = new Scanner(file);
        leituraFicheiro.nextLine();
        String[] temp;
        while (leituraFicheiro.hasNextLine()) {
            temp = leituraFicheiro.nextLine().split("[: ,-]+");
            for (int k = 0; k < 7; k++) {
                informacaoFicheiro[i][k] = Integer.parseInt(temp[k]);
            }
            i++;
        }
        //-------------------------------------------------------------------------------
        long lStartTime = 0;
        long lEndTime = 0;
        System.out.println("que método quer testar?");
        System.out.println("1-MergeSort");
        System.out.println("2-InsertionSort");
        System.out.println("3-BubbleSort");
        int N = sc.nextInt();
        switch (N) {
            case 1:
                System.out.println("para crescente insira 1 e decrescente insira 2");
                int n = inte.nextInt();
                lStartTime = System.currentTimeMillis();
                Analiseseries.mergesort(informacaoFicheiro, i, n);
                Analiseseries.mergesort(informacaoFicheiro, i, n);
                lEndTime = System.currentTimeMillis();
                break;
            case 2:
                lStartTime = System.currentTimeMillis();
                Analiseseries.insertionSort(informacaoFicheiro, LINHAS);
                lEndTime = System.currentTimeMillis();
                break;
            case 3:
                lStartTime = System.currentTimeMillis();
                Analiseseries.bubblesort(informacaoFicheiro, LINHAS, informacaoFicheiro);
                lEndTime = System.currentTimeMillis();
                break;
            default:
                System.out.println("ERROR");
                break;
        }

        long output = lEndTime - lStartTime;

        System.out.println("o tempo de processamento foi de : " + output + " milisegundos");

    }

}
