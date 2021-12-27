package analiseseries;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.FileTerminal;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Forno21
 */
public class Analiseseries {

    static Scanner sc = new Scanner(System.in);
    static final int MAX = 30000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        int naousem = 0, k = 0;
        String numero, num, comando;
        int[][] ficheiro = new int[MAX][7];
        int[][] copia = new int[MAX][7];
        int[][] ana = new int[MAX][7];
        int[] consumos = new int[MAX];
        if (args.length == 12) {
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
            int numeroDatas = ler_ficheiro(ficheiro, args[1]);
            int mmep2[] = new int[numeroDatas];
            int y[] = new int[numeroDatas];
            switch (args[3]) {
                case "11":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 6, 11, 2, ana);
                    naousem = separar2(ficheiro, numeroDatas, 6, 11, 2, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
                case "12":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 12, 17, 2, ana);
                    naousem = separar2(ficheiro, numeroDatas, 12, 17, 2, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
                case "13":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 18, 23, 2, ana);
                    naousem = separar2(ficheiro, numeroDatas, 18, 23, 2, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
                case "14":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 0, 5, 2, ana);
                    naousem = separar2(ficheiro, numeroDatas, 0, 5, 2, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
                case "2":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 0, 24, 2, ana);
                    naousem = separar2(ficheiro, numeroDatas, 0, 24, 2, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
                case "3":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 0, 24, 1, ana);
                    naousem = separar2(ficheiro, numeroDatas, 0, 24, 1, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
                case "4":
                    passarZero(ana);
                    passarZero(copia);
                    k = separar2(ficheiro, numeroDatas, 0, 24, 0, ana);
                    naousem = separar2(ficheiro, numeroDatas, 0, 24, 0, copia);
                    for (int i = 0; i < k; i++) {
                        consumos[i] = ana[i][6];
                    }
                    fazgrafico(consumos, k, k, "Consumo geral", "nao");
                    break;
            }
            if (!args[5].equals("1") && !args[5].equals("2")) {
                System.out.println("modelo inválido");
                System.exit(1);
            }
            switch (args[7]) {
                case "1":
                    String nome = "Ordenação";
                    mergesort(ana, 0, k - 1);
                    fazgrafico2arr(ana, k, copia, nome, "nao");
                    printwriter3(copia, ana, k, nome, "nao");
                    k = separar2(ficheiro, numeroDatas, 6, 11, 2, ana);
                    break;
                case "2":
                    nome = "Ordenação";
                    mergesort(ana, 0, k - 1);
                    inverter(k, ana);
                    fazgrafico2arr(ana, k, copia, nome, "nao");
                    printwriter3(copia, ana, k, nome, "nao");
                    k = separar2(ficheiro, numeroDatas, 6, 11, 2, ana);
                    break;
            }

            switch (args[5]) {
                case "1":
                    mediamovelsimplessem(ana, k, y, args);
                    break;
                case "2":
                    media_movel_exponencialmente_pesadasem(ana, k, mmep2, args);
                    break;
            }
            String dat = " ";
            if ("3".equals(args[3])) {
                dat = cortarmes(args[11]);
            }
            if ("11".equals(args[3]) || "12".equals(args[3]) || "13".equals(args[3]) || "14".equals(args[3]) || "2".equals(args[3])) {
                dat = cortardia(args[11]);
            }
            if ("4".equals(args[3])) {
                dat = "00/00/" + args[11];
            }

            switch (args[5]) {
                case "1":
                    mediamovelsimplesprevisaosem(ana, k, y, args[3], args[9], dat);
                    break;
                case "2":
                    media_exponencialmente_pesada_previsaosem(ana, k, mmep2, args[3], args[9], dat);
                    break;
            }
        } else {
            if (args.length == 2) {
                int numeroDatas = ler_ficheiro(ficheiro, args[1]);
                int mmep2[] = new int[numeroDatas];
                int y[] = new int[numeroDatas];
                do {
                    System.out.println("===============================================================================");
                    System.out.println("Introduza qual parte pretende analisar");
                    System.out.println("11-Manhã");
                    System.out.println("12-Tarde");
                    System.out.println("13-Noite");
                    System.out.println("14-Madrugada");
                    System.out.println("2-Diário");
                    System.out.println("3-Mensal");
                    System.out.println("4-Anual");
                    System.out.println("5-Escolher outro ficheiro");
                    System.out.println("0-Fechar o programa");
                    System.out.println("===============================================================================");
                    String tipo = sc.nextLine();
                    switch (tipo) {
                        case "11":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 6, 11, 2, ana);
                            naousem = separar2(ficheiro, numeroDatas, 6, 11, 2, copia);
                            break;
                        case "12":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 12, 17, 2, ana);
                            naousem = separar2(ficheiro, numeroDatas, 12, 17, 2, copia);
                            break;
                        case "13":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 18, 23, 2, ana);
                            naousem = separar2(ficheiro, numeroDatas, 18, 23, 2, copia);
                            break;
                        case "14":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 0, 5, 2, ana);
                            naousem = separar2(ficheiro, numeroDatas, 0, 5, 2, copia);
                            break;
                        case "2":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 0, 24, 2, ana);
                            naousem = separar2(ficheiro, numeroDatas, 0, 24, 2, copia);
                            break;
                        case "3":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 0, 24, 1, ana);
                            naousem = separar2(ficheiro, numeroDatas, 0, 24, 1, copia);
                            break;
                        case "4":
                            passarZero(ana);
                            passarZero(copia);
                            k = separar2(ficheiro, numeroDatas, 0, 24, 0, ana);
                            naousem = separar2(ficheiro, numeroDatas, 0, 24, 0, copia);
                            break;
                        case "5":
                            System.out.println("Indique o novo ficheiro");
                            passarZero(ficheiro);
                            passarZero(copia);
                            String fi = sc.nextLine();
                            numeroDatas = ler_ficheiro(ficheiro, fi);
                            naousem = ler_ficheiro(copia, fi);
                            break;
                        case "0":
                            System.exit(0);
                        default:
                            System.out.println("Inseriu um valor inválido");
                            System.exit(0);
                    }
                    System.out.println("===============================================================================");
                    System.out.println("O que pretende fazer?");
                    System.out.println("");
                    System.out.println("1-Visualizar gráfico de consumos");
                    System.out.println("2-Visualizar as distribuições de observações");
                    System.out.println("3-Ordenar valores");
                    System.out.println("4-Efetuar uma Filtragem");
                    System.out.println("5-Efetuar uma Previsão");
                    System.out.println("6-Escolher outro ficheiro");
                    System.out.println("0-Fechar o programa");
                    System.out.println("===============================================================================");
                    comando = sc.nextLine();
                    switch (comando) {
                        case "1":
                            for (int i = 0; i < k; i++) {
                                consumos[i] = ana[i][6];
                            }
                            fazgrafico(consumos, k, k, "Consumo espaço geral", "sim");
                            break;
                        case "2":
                            numero_de_observaçoes(ana, k);
                            break;
                        case "3":
                            int op;
                            String nome = "Ordenação";
                            System.out.println("Para ordem crescente insira 1 e para ordem decrescente introduza 0");
                            do {
                                op = Integer.parseInt(sc.nextLine());
                            } while (op > 1 || op < 0);
                            if (op == 1) {
                                mergesort(ana, 0, k - 1);
                                fazgrafico2arr(ana, k, copia, nome, "sim");
                                printwriter3(copia, ana, k, nome, "sim");
                            }
                            if (op == 0) {
                                mergesort(ana, 0, k - 1);
                                inverter(k, ana);
                                fazgrafico2arr(ana, k, copia, nome, "sim");
                                printwriter3(copia, ana, k, nome, "sim");
                            }
                            break;
                        case "4":
                            submenu();
                            num = sc.nextLine();
                            switch (num) {
                                case "1":
                                    mediamovelsimples(ana, k, y);
                                    break;
                                case "2":
                                    media_movel_exponencialmente_pesada(ana, k, mmep2);
                                    break;
                            }
                            break;
                        case "5":
                            submenu();
                            num = sc.nextLine();
                            switch (num) {
                                case "1":
                                    mediamovelsimplesprevisao(ana, k, y, tipo);
                                    break;
                                case "2":
                                    media_exponencialmente_pesada_previsao(ana, k, mmep2, tipo);
                                    break;
                            }
                            break;
                        case "0":
                            System.exit(0);
                        default:
                            System.out.println("Inseriu um valor inválido");
                            System.exit(0);
                    }
                } while (!"sair".equalsIgnoreCase(comando));
            } else {
                System.out.println("Argumentos introduzidos de maneira errada");
            }
        }
    }

    public static void submenu() {
        System.out.println("Qual modo pretende usar?");
        System.out.println("Média Móvel Simples-1");
        System.out.println("Média Móvel Exponencialmente Pesada-2");
    }

    public static int ler_ficheiro(int[][] ficheiro, String args) throws FileNotFoundException {
        int numeroDatas = 0;
        File dados = new File(args);
        Scanner in = new Scanner(dados);
        in.nextLine();
        while (in.hasNextLine()) {
            String[] separar = in.nextLine().split(" ");
            String[] calendario = separar[0].split("-");
            String[] horario = separar[1].split(":");
            String[] watts = horario[2].split(",");
            ficheiro[numeroDatas][0] = Integer.parseInt(calendario[0]);
            ficheiro[numeroDatas][1] = Integer.parseInt(calendario[1]);
            ficheiro[numeroDatas][2] = Integer.parseInt(calendario[2]);
            ficheiro[numeroDatas][3] = Integer.parseInt(horario[0]);
            ficheiro[numeroDatas][4] = Integer.parseInt(horario[1]);
            ficheiro[numeroDatas][5] = Integer.parseInt(watts[0]);
            ficheiro[numeroDatas][6] = Integer.parseInt(watts[1]);
            numeroDatas++;
        }
        in.close();
        return numeroDatas;
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void numero_de_observaçoes(int[][] ficheiro, int numeroDatas) throws FileNotFoundException {
        int soma = 0, mediaglobal, contador1 = 0, contador2 = 0, contador3 = 0;
        String a = "Observações";
        for (int i = 0; i < numeroDatas; i++) {
            soma = soma + ficheiro[i][6];
        }
        mediaglobal = soma / numeroDatas;
        for (int p = 0; p < numeroDatas; p++) {
            if (ficheiro[p][6] > 0 && (ficheiro[p][6] < (mediaglobal - (0.2 * mediaglobal)))) {
                contador1++;
            } else if (ficheiro[p][6] >= (mediaglobal - (0.2 * mediaglobal)) && (ficheiro[p][6] < (mediaglobal + (0.2 * mediaglobal)))) {
                contador2++;
            } else if (ficheiro[p][6] >= mediaglobal + (0.2 * mediaglobal)) {
                contador3++;
            }
        }
        fazhistograma(contador1, contador2, contador3, "Histograma");
        System.out.printf("No intervalo [-∞ , " + "%.2f", (mediaglobal - (0.2 * mediaglobal)));
        System.out.println("[, existem " + contador1 + " ocorrências");
        System.out.printf("No intervalo [" + "%.2f", (mediaglobal - (0.2 * mediaglobal)));
        System.out.print(" , ");
        System.out.printf("%.2f", (mediaglobal + (0.2 * mediaglobal)));
        System.out.println("[, existem " + contador2 + " ocorrências");
        System.out.printf("No intervalo [" + "%.2f", mediaglobal + (0.2 * mediaglobal));
        System.out.print(", +∞[ , existem " + contador3 + " ocorrências");
        System.out.println("");
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void mergesort(int[][] ana, int lindex, int hindex) {
        if (lindex < hindex) {
            int m = (lindex + hindex) / 2;
            mergesort(ana, lindex, m);
            mergesort(ana, m + 1, hindex);
            merge(ana, lindex, m, hindex);
        }
    }

    public static void merge(int[][] ana, int lindex, int mindex, int hindex) {
        int n1 = mindex - lindex + 1;
        int n2 = hindex - mindex;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = ana[lindex + i][6];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = ana[mindex + 1 + j][6];
        }
        int i = 0, j = 0;

        int k = lindex;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                ana[k][6] = L[i];
                i++;
            } else {
                ana[k][6] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            ana[k][6] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            ana[k][6] = R[j];
            j++;
            k++;
        }
    }

    public static int[][] insertionSort(int[][] list, int numeroDeLinhas) {
        int i, j, key;
        int[] aux = new int[6];
        for (i = 1; i < numeroDeLinhas; i++) {
            key = list[i][6];
            j = i - 1;
            while (j >= 0 && key < list[j][6]) {
                aux = list[j];
                list[j] = list[j + 1];
                list[j + 1] = aux;
                j--;
            }
        }
        return list;
    }

    public static void bubblesort(int[][] ficheiro, int numlinhas, int[][] copia) {
        for (int idxl = 0; idxl < numlinhas; idxl++) {
            for (int idx2 = 0; idx2 < numlinhas; idx2++) {
                if (copia[idxl][6] < copia[idx2][6]) {
                    int[] aux = new int[7];
                    for (int j = 0; j < 7; j++) {
                        aux[j] = copia[idx2][j];
                        copia[idx2][j] = copia[idxl][j];
                        copia[idxl][j] = aux[j];
                    }
                }
            }
        }
    }

    public static void inverter(int valores, int[][] copia) {
        for (int i = 0; i < valores / 2; i++) {
            int j;
            int[] temp = new int[7];
            for (j = 0; j < 7; j++) {
                temp[j] = copia[i][j];
                copia[i][j] = copia[valores - i - 1][j];
                copia[valores - i - 1][j] = temp[j];
            }

        }
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void mediamovelsimples(int[][] ficheiro, int numeroDatas, int[] y) throws FileNotFoundException {
        int ordem, k = 0;
        String a = "filtragem_simples";
        System.out.println("Qual a ordem que deseja fazer?");
        ordem = Integer.parseInt(sc.nextLine());
        for (int i = ordem - 1; i < numeroDatas; i++) {
            for (int j = i; j < i + ordem; j++) {
                y[i] = (ficheiro[j][6] + y[i]);
            }
            y[i] = y[i] / ordem;
            k++;
        }
        fazgrafico2simples(y, k, ficheiro, ordem, a, "sim");
        printwriter2(ficheiro, y, numeroDatas, a, "sim");
        erro_medio_absoluto(y, k, ficheiro);
    }

    public static void mediamovelsimplessem(int[][] ficheiro, int numeroDatas, int[] y, String[] args) throws FileNotFoundException {
        int ordem, k = 0;
        String a = "filtragem_simples";
        ordem = Integer.parseInt(args[9]);
        for (int i = ordem - 1; i < numeroDatas; i++) {
            for (int j = i; j < i + ordem; j++) {
                y[i] = (ficheiro[j][6] + y[i]);
            }
            y[i] = y[i] / ordem;
            k++;
        }
        fazgrafico2simples(y, k, ficheiro, ordem, a, "nao");
        printwriter2(ficheiro, y, numeroDatas, a, "nao");
        erro_medio_absoluto(y, k, ficheiro);
    }

    public static void media_movel_exponencialmente_pesada(int[][] ficheiro, int numeroDatas, int[] mmep2) throws FileNotFoundException {
        double alpha, mmep = 0;
        String a = "filtragem_pesada";
        System.out.println("Introduza o alpha desejado");
        do {
            alpha = Double.parseDouble(sc.nextLine());
            if (alpha <= 0 || alpha > 1) {
                System.out.println("Valor inválido, introduza outro");
            }
        } while (alpha <= 0 || alpha > 1);
        for (int i = 0; i < numeroDatas; i++) {
            if (i == 0) {
                mmep = ficheiro[0][6];
                mmep2[0] = (int) mmep;
            } else {
                mmep = alpha * ficheiro[i][6] + (1 - alpha) * (mmep);
                mmep2[i] = (int) mmep;
            }
        }
        fazgrafico2(mmep2, numeroDatas, ficheiro, a, "sim");
        printwriter2(ficheiro, mmep2, numeroDatas, a, "sim");
        erro_medio_absoluto(mmep2, numeroDatas, ficheiro);
    }

    public static void media_movel_exponencialmente_pesadasem(int[][] ficheiro, int numeroDatas, int[] mmep2, String[] args) throws FileNotFoundException {
        double alpha, mmep = 0;
        String a = "filtragem_pesada";
        do {
            alpha = Double.parseDouble(args[9]);
            if (alpha <= 0 || alpha > 1) {
                System.out.println("Valor inválido, introduza outro");
            }
        } while (alpha <= 0 || alpha > 1);
        for (int i = 0; i < numeroDatas; i++) {
            if (i == 0) {
                mmep = ficheiro[0][6];
                mmep2[0] = (int) mmep;
            } else {
                mmep = alpha * ficheiro[i][6] + (1 - alpha) * (mmep);
                mmep2[i] = (int) mmep;
            }
        }
        fazgrafico2(mmep2, numeroDatas, ficheiro, a, "nao");
        printwriter2(ficheiro, mmep2, numeroDatas, a, "nao");
        erro_medio_absoluto(mmep2, numeroDatas, ficheiro);
    }

    public static void mediamovelsimplesprevisao(int[][] ficheiro, int numeroDatas, int[] y, String tipo) throws FileNotFoundException {
        int ordem, prever = 0;
        String a = "previsão_simples";
        do {
            System.out.println("Qual a ordem que deseja usar?");
            ordem = Integer.parseInt(sc.nextLine());
            if (ordem <= 0) {
                System.out.println("Valor inválido introduza outro");
            }
        } while (ordem <= 0);
        prever = analisadata2(ficheiro, numeroDatas, tipo);
        for (int i = 0; i < ordem; i++) {
            y[i] = ficheiro[i][6];
        }
        for (int j = ordem; j <= numeroDatas; j++) {
            for (int i = j - ordem; i < j; i++) {
                y[j] = (y[j] + ficheiro[i][6]);
            }
            y[j] = y[j] / ordem;
        }
        System.out.println("O valor previsto para esse valor é " + y[prever]);
        printwriter2(ficheiro, y, numeroDatas, a, "sim");
    }

    public static void mediamovelsimplesprevisaosem(int[][] ficheiro, int numeroDatas, int[] y, String tipo, String args, String dat) throws FileNotFoundException {
        int ordem, prever = 0;
        String a = "previsão_simples";
        ordem = Integer.parseInt(args);
        prever = analisadata2sem(ficheiro, numeroDatas, tipo, dat);
        for (int i = 0; i < ordem; i++) {
            y[i] = ficheiro[i][6];
        }
        for (int j = ordem; j <= numeroDatas; j++) {
            for (int i = j - ordem; i < j; i++) {
                y[j] = (y[j] + ficheiro[i][6]);
            }
            y[j] = y[j] / ordem;
        }
        System.out.println("O valor previsto para esse valor é " + y[prever]);
        printwriter2(ficheiro, y, numeroDatas, a, "nao");
    }

    public static void media_exponencialmente_pesada_previsao(int[][] ficheiro, int numeroDatas, int[] mmep2, String tipo) throws FileNotFoundException {
        double alpha, mmep = 0;
        String a = "previsão_exponencialmente_pesada";
        int prever = 0;
        System.out.println("Introduza o alpha desejado");
        do {
            alpha = Double.parseDouble(sc.nextLine());
            if (alpha <= 0 || alpha > 1) {
                System.out.println("Valor inválido, introduza outro");
            }
        } while (alpha <= 0 || alpha > 1);
        prever = analisadata2(ficheiro, numeroDatas, tipo);
        for (int i = 0; i <= prever; i++) {
            if (i == 0) {
                mmep = ficheiro[0][6];
                mmep2[i] = (int) mmep;
            } else {
                mmep = alpha * ficheiro[i - 1][6] + (1 - alpha) * (mmep2[i - 1]);
                mmep2[i] = (int) mmep;
            }
        }
        System.out.println("O valor previsto para o valor determinado foi " + mmep2[prever]);
        printwriter2(ficheiro, mmep2, numeroDatas, a, "sim");
    }

    public static void media_exponencialmente_pesada_previsaosem(int[][] ficheiro, int numeroDatas, int[] mmep2, String tipo, String args, String dat) throws FileNotFoundException {
        double alpha, mmep = 0;
        String a = "previsão_exponencialmente_pesada";
        int prever = 0;
        alpha = Double.parseDouble(args);
        prever = analisadata2sem(ficheiro, numeroDatas, tipo, dat);
        for (int i = 0; i <= prever; i++) {
            if (i == 0) {
                mmep = ficheiro[0][6];
                mmep2[i] = (int) mmep;
            } else {
                mmep = alpha * ficheiro[i - 1][6] + (1 - alpha) * (mmep2[i - 1]);
                mmep2[i] = (int) mmep;
            }
        }
        System.out.println("O valor previsto para o valor determinado foi " + mmep2[prever]);
        printwriter2(ficheiro, mmep2, numeroDatas, a, "nao");
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void erro_medio_absoluto(int[] mmep2, int numeroDatas, int[][] ficheiro) {
        int erro = 0, errofinal;
        for (int i = 0; i < numeroDatas; i++) {
            erro = (Math.abs(mmep2[i] - ficheiro[i][6])) + erro;
        }
        errofinal = erro / numeroDatas;
        System.out.println("O erro médio absoluto é " + errofinal);
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void fazgrafico(int[] analise, int numeroDatas, int k, String nome, String sim) throws FileNotFoundException {
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.LINES);
        myPlotStyle.setLineWidth(1);
        myPlotStyle.setLineType(NamedPlotColor.BLUE);
        int tab[][] = new int[k][2];
        for (int i = 0; i < k; i++) {
            tab[i][0] = i;
            tab[i][1] = analise[i];
        }
        DataSetPlot s = new DataSetPlot(tab);
        s.setTitle("Consumo de Energia");
        s.setPlotStyle(myPlotStyle);
        p.addPlot(s);
        p.newGraph();
        p.plot();

        if (sim.equals("sim")) {
            System.out.print("Deseja guardar o gráfico \"sim\" ou \"nao\"? ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                String title = "Consumo de energia";
                File file = new File("statistics_" + title + ".png");
                JavaPlot plot = new JavaPlot();
                GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
                plot.setTerminal(terminal);
                plot.set("xlabel", "\"Observações\"");
                plot.set("ylabel", "\"" + title + "\"");
                plot.addPlot(s);
                PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
                stl.setStyle(Style.LINES);
                plot.setKey(JavaPlot.Key.OFF);
                plot.plot();

                System.out.println("Ficheiro guardado com sucesso!");
            }
            String hora;
            System.out.print("Deseja guardar o a série \"sim\" ou \"nao\"? ");
            resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                hora = getDateTime();
                File file = new File(nome + "_" + hora + ".csv");
                PrintWriter printWriter = new PrintWriter(file);
                for (int i = 0; i < numeroDatas; i++) {
                    printWriter.print(analise[i]);
                    printWriter.println();
                }
                printWriter.close();
            }
        } else {
            String title = "Consumo de energia";
            File file = new File("statistics_" + title + ".png");
            JavaPlot plot = new JavaPlot();
            GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
            plot.setTerminal(terminal);
            plot.set("xlabel", "\"Observações\"");
            plot.set("ylabel", "\"" + title + "\"");
            plot.addPlot(s);
            PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
            stl.setStyle(Style.LINES);
            plot.setKey(JavaPlot.Key.OFF);
            plot.plot();

            String hora1;
            hora1 = getDateTime();
            File file1 = new File(nome + "_" + hora1 + ".csv");
            PrintWriter printWriter = new PrintWriter(file1);
            for (int i = 0; i < numeroDatas; i++) {
                printWriter.print(analise[i]);
                printWriter.println();
            }
            printWriter.close();
        }
    }

    public static void fazgrafico2(int[] analise, int numeroDatas, int[][] ficheiro, String nome, String sim) throws FileNotFoundException {
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.LINES);
        myPlotStyle.setLineWidth(1);
        myPlotStyle.setLineType(NamedPlotColor.BLUE);
        PlotStyle myPlotStyle2 = new PlotStyle();
        myPlotStyle2.setStyle(Style.LINES);
        myPlotStyle2.setLineWidth(1);
        myPlotStyle2.setLineType(NamedPlotColor.RED);

        int tab[][] = new int[numeroDatas][2];
        int tab2[][] = new int[numeroDatas][2];

        for (int i = 0; i < numeroDatas; i++) {
            tab[i][0] = i;
            tab[i][1] = analise[i];
        }
        for (int i = 0; i < numeroDatas; i++) {
            tab2[i][0] = i;
            tab2[i][1] = ficheiro[i][6];
        }
        DataSetPlot s = new DataSetPlot(tab);
        DataSetPlot s2 = new DataSetPlot(tab2);
        s.setTitle("Alterado");
        s.setPlotStyle(myPlotStyle);
        s2.setTitle("Original");
        s2.setPlotStyle(myPlotStyle2);
        p.addPlot(s2);
        p.addPlot(s);
        p.newGraph();
        p.plot();

        if (sim.equals("sim")) {
            System.out.print("Deseja guardar o gráfico \"sim\" ou \"nao\"? ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                String title = "Consumo de energia";
                File file = new File("statistics_" + title + ".png");
                JavaPlot plot = new JavaPlot();
                GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
                plot.setTerminal(terminal);
                plot.set("xlabel", "\"Observações\"");
                plot.set("ylabel", "\"" + title + "\"");
                plot.addPlot(s);
                plot.addPlot(s2);
                PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
                stl.setStyle(Style.LINES);
                plot.setKey(JavaPlot.Key.OFF);
                plot.plot();

                System.out.println("Ficheiro guardado com sucesso!");
            }
        } else {
            String title = "Consumo de energia";
            File file = new File("statistics_" + title + ".png");
            JavaPlot plot = new JavaPlot();
            GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
            plot.setTerminal(terminal);
            plot.set("xlabel", "\"Observações\"");
            plot.set("ylabel", "\"" + title + "\"");
            plot.addPlot(s);
            plot.addPlot(s2);
            PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
            stl.setStyle(Style.LINES);
            plot.setKey(JavaPlot.Key.OFF);
            plot.plot();
        }
    }

    public static void fazgrafico2simples(int[] analise, int numeroDatas, int[][] ficheiro, int ordem, String nome, String sim) throws FileNotFoundException {
        String hora;
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.LINES);
        myPlotStyle.setLineWidth(1);
        myPlotStyle.setLineType(NamedPlotColor.BLUE);
        PlotStyle myPlotStyle2 = new PlotStyle();
        myPlotStyle2.setStyle(Style.LINES);
        myPlotStyle2.setLineWidth(1);
        myPlotStyle2.setLineType(NamedPlotColor.RED);

        int tab[][] = new int[numeroDatas - ordem + 1][2];
        int tab2[][] = new int[numeroDatas][2];

        for (int i = ordem - 1; i < numeroDatas; i++) {
            tab[i - ordem + 1][0] = i;
            tab[i - ordem + 1][1] = analise[i];
        }
        for (int i = 0; i < numeroDatas; i++) {
            tab2[i][0] = i;
            tab2[i][1] = ficheiro[i][6];
        }
        DataSetPlot s = new DataSetPlot(tab);
        DataSetPlot s2 = new DataSetPlot(tab2);
        s.setTitle("Alterado");
        s.setPlotStyle(myPlotStyle);
        s2.setTitle("Original");
        s2.setPlotStyle(myPlotStyle2);
        p.addPlot(s2);
        p.addPlot(s);
        p.newGraph();
        p.plot();

        if (sim.equals("sim")) {
            System.out.print("Deseja guardar o gráfico \"sim\" ou \"nao\"? ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                String title = "Media Móvel Simples";
                File file = new File("statistics_" + title + ".png");
                JavaPlot plot = new JavaPlot();
                GNUPlotTerminal terminal = new FileTerminal("png", title + ".png");
                plot.setTerminal(terminal);
                plot.set("xlabel", "\"Observações\"");
                plot.set("ylabel", "\"" + title + "\"");
                plot.addPlot(s);
                plot.addPlot(s2);
                PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
                stl.setStyle(Style.LINES);
                plot.setKey(JavaPlot.Key.OFF);
                plot.plot();

                System.out.println("Ficheiro guardado com sucesso!");
            }
        } else {
            String title = "Media Móvel Simples";
            File file = new File("statistics_" + title + ".png");
            JavaPlot plot = new JavaPlot();
            GNUPlotTerminal terminal = new FileTerminal("png", title + ".png");
            plot.setTerminal(terminal);
            plot.set("xlabel", "\"Observações\"");
            plot.set("ylabel", "\"" + title + "\"");
            plot.addPlot(s);
            plot.addPlot(s2);
            PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
            stl.setStyle(Style.LINES);
            plot.setKey(JavaPlot.Key.OFF);
            plot.plot();
        }
    }

    public static void fazgrafico2arr(int[][] analise, int numeroDatas, int[][] ficheiro, String nome, String sim) throws FileNotFoundException {
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.LINES);
        myPlotStyle.setLineWidth(1);
        myPlotStyle.setLineType(NamedPlotColor.BLUE);
        PlotStyle myPlotStyle2 = new PlotStyle();
        myPlotStyle2.setStyle(Style.LINES);
        myPlotStyle2.setLineWidth(1);
        myPlotStyle2.setLineType(NamedPlotColor.RED);

        int tab[][] = new int[numeroDatas][2];
        int tab2[][] = new int[numeroDatas][2];

        for (int i = 0; i < numeroDatas; i++) {
            tab[i][0] = i;
            tab[i][1] = analise[i][6];
        }
        for (int i = 0; i < numeroDatas; i++) {
            tab2[i][0] = i;
            tab2[i][1] = ficheiro[i][6];
        }
        DataSetPlot s = new DataSetPlot(tab);
        DataSetPlot s2 = new DataSetPlot(tab2);
        s.setTitle("Alterado");
        s.setPlotStyle(myPlotStyle);
        s2.setTitle("Original");
        s2.setPlotStyle(myPlotStyle2);
        p.addPlot(s2);
        p.addPlot(s);
        p.newGraph();
        p.plot();

        if (sim.equals("sim")) {
            System.out.print("Deseja guardar o gráfico \"sim\" ou \"nao\"? ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                String title = "Ordenação";
                File file = new File("statistics_" + title + ".png");
                JavaPlot plot = new JavaPlot();
                GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
                plot.setTerminal(terminal);
                plot.set("xlabel", "\"Observações\"");
                plot.set("ylabel", "\"" + title + "\"");
                plot.addPlot(s);
                plot.addPlot(s2);
                PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
                stl.setStyle(Style.LINES);
                plot.setKey(JavaPlot.Key.OFF);
                plot.plot();

                System.out.println("Ficheiro guardado com sucesso!");
            }
        } else {
            String title = "Ordenação";
            File file = new File("statistics_" + title + ".png");
            JavaPlot plot = new JavaPlot();
            GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
            plot.setTerminal(terminal);
            plot.set("xlabel", "\"Observações\"");
            plot.set("ylabel", "\"" + title + "\"");
            plot.addPlot(s);
            plot.addPlot(s2);
            PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
            stl.setStyle(Style.LINES);
            plot.setKey(JavaPlot.Key.OFF);
            plot.plot();
        }
    }

    public static void fazhistograma(int a, int b, int c, String nome) throws FileNotFoundException {
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.BOXES);
        myPlotStyle.setLineWidth(1);
        myPlotStyle.setLineType(NamedPlotColor.CORAL);
        myPlotStyle.setPointSize(1);
        myPlotStyle.setPointType(7);

        int tab[][] = new int[5][1];
        tab[0][0] = 0;
        tab[1][0] = a;
        tab[2][0] = b;
        tab[3][0] = c;
        tab[4][0] = 0;
        DataSetPlot s = new DataSetPlot(tab);
        s.setTitle("Histograma dos Intervalos");
        s.setPlotStyle(myPlotStyle);
        p.addPlot(s);
        p.newGraph();
        p.plot();

        System.out.print("Deseja guardar o gráfico \"sim\" ou \"nao\"? ");
        String resposta = sc.nextLine();
        if (resposta.equalsIgnoreCase("sim")) {
            String title = "Observações da Média";
            File file = new File("statistics_" + title + ".png");
            JavaPlot plot = new JavaPlot();
            GNUPlotTerminal terminal = new FileTerminal("png", "statis_" + title + ".png");
            plot.setTerminal(terminal);
            plot.set("xlabel", "\"Observações\"");
            plot.set("ylabel", "\"" + title + "\"");
            plot.addPlot(s);
            PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
            stl.setStyle(Style.LINES);
            plot.setKey(JavaPlot.Key.OFF);
            plot.plot();

            System.out.println("Ficheiro guardado com sucesso!");
        }
        String hora;
        System.out.print("Deseja guardar o a série \"sim\" ou \"nao\"? ");
        resposta = sc.nextLine();
        if (resposta.equalsIgnoreCase("sim")) {
            hora = getDateTime();
            File file = new File(nome + "_" + hora + ".csv");
            PrintWriter printWriter = new PrintWriter(file);
            System.out.println("O número de ocorrências dentro da média é " + b);
            System.out.println("O número de ocorrências acima da média é " + c);
            System.out.println("O número de ocorrências abaixo da média é " + a);
            printWriter.close();
        }
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void passarZero(int[][] fich) {
        for (int i = 0; i <= fich.length - 1; i++) {
            for (int j = 0; j < 7; j++) {
                fich[i][j] = 0;
            }
        }
    }

    public static String cortarmes(String args) {
        String dat;
        dat = "00/" + (args.substring(0, 2) + "/" + (args.substring(2, args.length())));
        return dat;
    }

    public static String cortardia(String args) {
        String dat;
        dat = (args.substring(0, 2)) + "/" + (args.substring(2, 4)) + "/" + (args.substring(4, args.length()));;
        return dat;
    }

    public static int separar2(int[][] ficheiro, int numeroDatas, int extremo1, int extremo2, int j, int[][] ana) {
        int soma = 0, k = 0, quant = 0, i;
        while (quant < numeroDatas) {
            for (i = quant; i < numeroDatas; i++) {
                if (ficheiro[i][j] == ficheiro[i + 1][j]) {
                    if (ficheiro[i][3] <= extremo2 && ficheiro[i][3] >= extremo1) {
                        soma = soma + ficheiro[i][6];
                    }
                }
                if (ficheiro[i][j] != ficheiro[i + 1][j]) {
                    if (ficheiro[i][3] <= extremo2 && ficheiro[i][3] >= extremo1) {
                        soma = soma + ficheiro[i][6];
                    }
                    quant++;
                    break;
                }
                quant++;
            }
            ana[k][6] = soma;
            for (int l = 0; l < 6; l++) {
                ana[k][l] = ficheiro[i][l];
            }
            k++;
            soma = 0;
        }
        return k;
    }

    public static int analisadata(int[][] ana, int numeroDatas, String prever, int d, int m, int a) {
        int k = 0, i;
        String[] separar = prever.split("/");
        for (i = 0; i < numeroDatas; i++) {
            if (d != 0 && m != 0) {
                if (ana[i][d] == Integer.parseInt(separar[0]) && ana[i][m] == Integer.parseInt(separar[1]) && ana[i][a] == Integer.parseInt(separar[2])) {
                    break;
                }
            } else {
                if (m != 0 && d == 0) {
                    if (ana[i][m] == Integer.parseInt(separar[0]) && ana[i][a] == Integer.parseInt(separar[1])) {
                        break;
                    }
                } else {
                    if (d == 0 && m == 0) {
                        if (ana[i][a] == Integer.parseInt(separar[0])) {
                            break;
                        }
                    }
                }
            }
        }
        return i;
    }

    public static int analisadata2(int[][] ficheiro, int numeroDatas, String tipo) {
        String prever1;
        int prever = 0;
        if (tipo.equals("11") || tipo.equals("12") || tipo.equals("13") || tipo.equals("14") || tipo.equals("2")) {
            System.out.println("Introduza o dia que pretende prever da seguinte forma - dia/mes/ano");
            prever1 = sc.nextLine();
            prever = analisadata(ficheiro, numeroDatas, prever1, 2, 1, 0);
        } else {
            if (tipo.equals("3")) {
                System.out.println("Introduza o mês que pretende prever da seguinte forma - mes/ano");
                prever1 = sc.nextLine();
                prever = analisadata(ficheiro, numeroDatas, prever1, 0, 1, 0);
            } else {
                if (tipo.equals("4")) {
                    System.out.println("Introduza o ano que pretende prever");
                    prever1 = sc.nextLine();
                    prever = analisadata(ficheiro, numeroDatas, prever1, 0, 0, 0);
                }
            }
        }
        return prever;
    }

    public static int analisadata2sem(int[][] ficheiro, int numeroDatas, String tipo, String dat) {
        String prever1 = dat;
        int prever = 0;
        if (tipo.equals("11") || tipo.equals("12") || tipo.equals("13") || tipo.equals("14") || tipo.equals("2")) {
            prever = analisadata(ficheiro, numeroDatas, prever1, 2, 1, 0);
        } else {
            if (tipo.equals("3")) {
                prever = analisadata(ficheiro, numeroDatas, prever1, 0, 1, 0);
            } else {
                if (tipo.equals("4")) {
                    prever = analisadata(ficheiro, numeroDatas, prever1, 0, 0, 0);
                }
            }
        }
        return prever;
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //========================================================================================================================================//
    //========================================================================================================================================//
    public static void printwriter(int[][] ficheiro, int numeroDatas, String nome) throws FileNotFoundException {
        String resposta, hora;
        System.out.print("Deseja guardar o a série \"sim\" ou \"nao\"? ");
        resposta = sc.nextLine();
        if (resposta.equalsIgnoreCase("sim")) {
            hora = getDateTime();
            File file = new File(nome + "_" + hora + ".csv");
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < numeroDatas; i++) {
                printWriter.print(ficheiro[i][6]);
                printWriter.println("");
            }
            printWriter.close();
        }
    }

    public static void printwriter2(int[][] ficheiro, int[] mmpe2, int numeroDatas, String nome, String a) throws FileNotFoundException {
        String resposta, hora;
        if (a.equals("sim")) {
            System.out.print("Deseja guardar o a série \"sim\" ou \"nao\"? ");
            resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                hora = getDateTime();
                File file = new File(nome + "_" + hora + ".csv");
                PrintWriter printWriter = new PrintWriter(file);
                for (int i = 0; i < numeroDatas; i++) {
                    printWriter.print(ficheiro[i][6] + "(original)");
                    printWriter.print("             ");
                    printWriter.print(mmpe2[i] + "(alterado)");
                    printWriter.println();
                }
                printWriter.close();
            }
        } else {
            hora = getDateTime();
            File file = new File(nome + "_" + hora + ".csv");
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < numeroDatas; i++) {
                printWriter.print(ficheiro[i][6] + "(original)");
                printWriter.print("             ");
                printWriter.print(mmpe2[i] + "(alterado)");
                printWriter.println();
            }
            printWriter.close();
        }
    }

    public static void printwriter3(int[][] ficheiro, int[][] mmpe2, int numeroDatas, String nome, String a) throws FileNotFoundException {
        String resposta, hora;
        if (a.equals("sim")) {
            System.out.print("Deseja guardar o a série \"sim\" ou \"nao\"? ");
            resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                hora = getDateTime();
                File file = new File(nome + "_" + hora + ".csv");
                PrintWriter printWriter = new PrintWriter(file);
                for (int i = 0; i < numeroDatas; i++) {
                    printWriter.print(ficheiro[i][6] + "(original)");
                    printWriter.print("             ");
                    printWriter.print(mmpe2[i][6] + "(alterado)");
                    printWriter.println();
                }
                printWriter.close();
            }
        } else {
            hora = getDateTime();
            File file = new File(nome + "_" + hora + ".csv");
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < numeroDatas; i++) {
                printWriter.print(ficheiro[i][6] + "(original)");
                printWriter.print("             ");
                printWriter.print(mmpe2[i][6] + "(alterado)");
                printWriter.println();
            }
            printWriter.close();
        }
    }
}
