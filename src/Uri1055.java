// Gulosos

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Uri1055 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./example/uri1055.txt");
        Scanner s = new Scanner(file);

        StringBuilder sb = new StringBuilder();

        int t = s.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = s.nextInt();
            int[] numeros = new int[n];
            for (int j = 0; j < n; j++) {
                numeros[j] = s.nextInt();
            }

            int sum = getEllegantSwap(numeros);
            sb.append("Case ").append(i).append(": ").append(sum);
            if (i < t) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static int getEllegantSwap(int[] numeros) {
        return Arrays.stream(numeros).sum();
    }
}