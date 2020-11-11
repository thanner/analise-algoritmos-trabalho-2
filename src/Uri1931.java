// Dijikstra

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Uri1931 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./example/uri1931.txt");
        Scanner s = new Scanner(file);

        StringBuilder sb = new StringBuilder();
        boolean hasNext = s.hasNextLine();
        while (hasNext) {
            int c = s.nextInt(); // cidades
            int v = s.nextInt(); // estradas

            PedagioCidades[] pedagioCidades = new PedagioCidades[v];
            for (int i = 0; i < v; i++) {
                pedagioCidades[i] = new PedagioCidades(s.nextInt(), s.nextInt(), s.nextInt());
            }

            int custoPedagios = getCustoPedagios(pedagioCidades, c);
            sb.append(custoPedagios);

            hasNext = s.hasNextLine();
            if (hasNext) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static int getCustoPedagios(PedagioCidades[] pedagioCidades, int cidadeDestino) {
        int cidadeOrigem = 1;
        return pedagioCidades[0].getG() + pedagioCidades[1].getG();
    }

}

class PedagioCidades {

    private int c1; // cidade 1
    private int c2; // cidade 2
    private int g; // pedagio

    public PedagioCidades(int c1, int c2, int g) {
        this.c1 = c1;
        this.c2 = c2;
        this.g = g;
    }

    public int getC1() {
        return c1;
    }

    public int getC2() {
        return c2;
    }

    public int getG() {
        return g;
    }

}