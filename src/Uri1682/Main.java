package Uri1682;

import java.util.Scanner;

public class Main {
    private static final char[] acoes = {'N', 'O', 'P'};
    private static final int tamanho_max = 5000;
    private static final char[] codigoGenetico = new char[tamanho_max];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ENCONTRA_CODIGO(-1, tamanho_max);

        int tamanho = s.nextInt();
        while (tamanho != 0) {
            System.out.println(new String(codigoGenetico, 0, tamanho));
            tamanho = s.nextInt();
        }
    }

    private static boolean ENCONTRA_CODIGO(int posicao, int tamanho) {
        if (tamanho == 0 || posicao == tamanho - 1) {
            return true;
        }

        for (int i = 0; i < acoes.length; i++) {
            char acao = acoes[i];
            if (EH_ACEITAVEL(acao, posicao + 1)) {
                // Estender
                posicao += 1;
                codigoGenetico[posicao] = acao;

                boolean codigoCompleto = ENCONTRA_CODIGO(posicao, tamanho);
                if (codigoCompleto) {
                    return true;
                }

                // Retornar
                codigoGenetico[posicao] = Character.MIN_VALUE;
                posicao -= 1;
            }
        }
        return false;
    }

    private static boolean EH_ACEITAVEL(char acao, int novaPosicao) {
        codigoGenetico[novaPosicao] = acao;

        for (int tamanhoTeste = 1; tamanhoTeste <= (novaPosicao + 1) / 2; tamanhoTeste++) {
            boolean ehIgual = true;
            for (int i = novaPosicao; i > novaPosicao - tamanhoTeste && ehIgual; i--) {
                int j = i - tamanhoTeste;
                if (codigoGenetico[i] != codigoGenetico[j]) {
                    ehIgual = false;
                }
            }
            if (ehIgual) {
                codigoGenetico[novaPosicao] = Character.MIN_VALUE;
                return false;
            }
        }

        codigoGenetico[novaPosicao] = Character.MIN_VALUE;
        return true;
    }
}