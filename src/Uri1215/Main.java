package Uri1215;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int tamanho = 0;
        ArrayList<String> palavras = new ArrayList();
        Scanner ler = new Scanner(System.in);
        int i = 0;
        String palavra = "";
        String mini_pal = "";
        while (ler.hasNextLine()) {
            palavra = ler.nextLine().toLowerCase();
            if ("<eof>".equals(palavra) || ".<eof>".equals(palavra) || "/n<eof>".equals(palavra) || "eof".equals(palavra)) {
                break;
            }
            for (char ch : palavra.toCharArray()) {
                if (Character.isLetter(ch)) {
                    mini_pal = mini_pal + ch;
                } else if (mini_pal.compareTo("") != 0) {
                    if (!palavras.contains(mini_pal)) {
                        palavras.add(mini_pal);
                    }
                    mini_pal = "";
                }
            }
            if (mini_pal.compareTo("") != 0) {
                if (!palavras.contains(mini_pal)) {
                    palavras.add(mini_pal);
                }
                mini_pal = "";
            }
            mini_pal = "";

            i++;
            // ler.useDelimiter("<EOF>");
        }
        Arrays.sort(palavras.toArray());
        for (String x : palavras) {
            System.out.println(x);
        }
    }

}