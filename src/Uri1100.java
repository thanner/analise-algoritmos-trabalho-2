// Tentativa e erro

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uri1100 {
    public static int[][] matrizVisitado;
    public static int MAX_LINHAS = 8;
    public static int MAX_COLUNAS = 8;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./example/uri1100.txt");
        Scanner s = new Scanner(file);

        StringBuilder sb = new StringBuilder();
        boolean hasNext = s.hasNextLine();
        while (hasNext) {
            String line = s.nextLine();
            char colunaInicialChar = line.charAt(0);
            int colunaInicial = colunaInicialChar - 'a' + 1;
            int linhaInicial = Integer.parseInt(String.valueOf(line.charAt(1)));
            char colunaAlvoChar = line.charAt(3);
            int colunaAlvo = colunaAlvoChar - 'a' + 1;
            int linhaAlvo = Integer.parseInt(String.valueOf(line.charAt(4)));
            CRIA_MATRIZ_VISITADO();

            int quantidadeMovimentos = PEGA_MOVIMENTOS(linhaInicial, colunaInicial, linhaAlvo, colunaAlvo, 0);

            sb.append("To get from ").append(colunaInicialChar).append(linhaInicial).append(" to ").append(colunaAlvoChar).append(linhaAlvo).append(" takes ").append(quantidadeMovimentos).append(" knight moves.");

            hasNext = s.hasNextLine();
            if (hasNext) {
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static void CRIA_MATRIZ_VISITADO() {
        matrizVisitado = new int[MAX_LINHAS][MAX_COLUNAS];
        for (int i = 0; i < MAX_LINHAS; i++) {
            for (int j = 0; j < MAX_COLUNAS; j++) {
                matrizVisitado[i][j] = -1;
            }
        }
    }

    private static int PEGA_MOVIMENTOS(int linhaAtual, int colunaAtual, int linhaAlvo, int colunaAlvo, int quantidadeMovimentos) {
        matrizVisitado[linhaAtual - 1][colunaAtual - 1] = quantidadeMovimentos;

        if (linhaAlvo == linhaAtual && colunaAlvo == colunaAtual) {
            return quantidadeMovimentos;
        }

        List<Acao> acoes = PEGA_ACOES(linhaAtual, colunaAtual);
        for (Acao acao : acoes) {
            if (EH_ACEITAVEL(acao, quantidadeMovimentos + 1)) {
                // Estender
                int linhaAnterior = linhaAtual;
                linhaAtual = acao.getLinha();
                int colunaAnterior = colunaAtual;
                colunaAtual = acao.getColuna();
                quantidadeMovimentos++;

                int movimentos = PEGA_MOVIMENTOS(linhaAtual, colunaAtual, linhaAlvo, colunaAlvo, quantidadeMovimentos);
                if (movimentos >= 0) {
                    return movimentos;
                }

                // Desfazer
                linhaAtual = linhaAnterior;
                colunaAtual = colunaAnterior;
                quantidadeMovimentos--;
            }
        }

        return -1;
    }

    private static List<Acao> PEGA_ACOES(int linhaAtual, int colunaAtual) {
        List<Acao> acoes = new ArrayList<>();
        acoes.add(new Acao(linhaAtual - 2, colunaAtual - 1));
        acoes.add(new Acao(linhaAtual - 2, colunaAtual + 1));
        acoes.add(new Acao(linhaAtual - 1, colunaAtual - 2));
        acoes.add(new Acao(linhaAtual - 1, colunaAtual + 2));
        acoes.add(new Acao(linhaAtual + 2, colunaAtual - 1));
        acoes.add(new Acao(linhaAtual + 2, colunaAtual + 1));
        acoes.add(new Acao(linhaAtual + 1, colunaAtual - 2));
        acoes.add(new Acao(linhaAtual + 1, colunaAtual + 2));
        return acoes;
    }

    private static boolean EH_ACEITAVEL(Acao acao, int movimentos) {
        return (acao.getLinha() > 0) && (acao.getLinha() <= MAX_LINHAS) && (acao.getColuna() > 0) && (acao.getColuna() <= MAX_COLUNAS) &&
                (matrizVisitado[acao.getLinha() - 1][acao.getColuna() - 1] == -1 || matrizVisitado[acao.getLinha() - 1][acao.getColuna() - 1] >= movimentos);
    }

}

class Acao {
    private final int linha;
    private final int coluna;

    public Acao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }
}

