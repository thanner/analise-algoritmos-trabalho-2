package Uri1100;

import java.util.*;

public class Main {
    public static int MAX_LINHAS = 8;
    public static int MAX_COLUNAS = 8;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

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

            int quantidadeMovimentos = PEGA_MOVIMENTOS_BFS(linhaInicial, colunaInicial, linhaAlvo, colunaAlvo);

            sb.append("To get from ").append(colunaInicialChar).append(linhaInicial).append(" to ").append(colunaAlvoChar).append(linhaAlvo).append(" takes ").append(quantidadeMovimentos).append(" knight moves.");

            hasNext = s.hasNextLine();
            if (hasNext) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static int PEGA_MOVIMENTOS_BFS(int linhaInicial, int colunaInicial, int linhaAlvo, int colunaAlvo) {
        Queue<No> queue = new ArrayDeque<>();
        queue.add(new No(linhaInicial, colunaInicial, 0));

        while (!queue.isEmpty()) {
            No no = queue.poll();

            if (CHEGOU_DESTINO(no, linhaAlvo, colunaAlvo)) {
                return no.getQuantidadeMovimentos();
            }

            List<No> nosCandidatos = PEGA_POSSIVEIS_MOVIMENTOS(no.getLinha(), no.getColuna(), no.getQuantidadeMovimentos());
            for (No noCandidato : nosCandidatos) {
                if (EH_ACEITAVEL(noCandidato)) {
                    queue.add(noCandidato);
                }
            }
        }
        return 0;
    }

    private static boolean CHEGOU_DESTINO(No no, int linhaAlvo, int colunaAlvo) {
        return no.getLinha() == linhaAlvo && no.getColuna() == colunaAlvo;
    }

    private static List<No> PEGA_POSSIVEIS_MOVIMENTOS(int linhaAtual, int colunaAtual, int quantidadeMovimentosAtual) {
        List<No> acoes = new ArrayList<>();
        acoes.add(new No(linhaAtual - 2, colunaAtual - 1, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual - 2, colunaAtual + 1, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual - 1, colunaAtual - 2, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual - 1, colunaAtual + 2, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual + 2, colunaAtual - 1, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual + 2, colunaAtual + 1, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual + 1, colunaAtual - 2, quantidadeMovimentosAtual + 1));
        acoes.add(new No(linhaAtual + 1, colunaAtual + 2, quantidadeMovimentosAtual + 1));
        return acoes;
    }

    private static boolean EH_ACEITAVEL(No no) {
        return (no.getLinha() > 0) && (no.getLinha() <= MAX_LINHAS) && (no.getColuna() > 0) && (no.getColuna() <= MAX_COLUNAS);
    }

}

class No {
    private final int linha;
    private final int coluna;
    private final int quantidadeMovimentos;

    public No(int linha, int coluna, int quantidadeMovimentos) {
        this.linha = linha;
        this.coluna = coluna;
        this.quantidadeMovimentos = quantidadeMovimentos;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getQuantidadeMovimentos() {
        return quantidadeMovimentos;
    }
}

