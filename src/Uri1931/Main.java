package Uri1931;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int c = s.nextInt(); // cidades
        int v = s.nextInt(); // estradas

        Map<Integer, Cidade> cidades = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            cidades.put(i, new Cidade(i));
        }

        for (int i = 0; i < v; i++) {
            Cidade c1 = cidades.get(s.nextInt());
            Cidade c2 = cidades.get(s.nextInt());
            int g = s.nextInt();

            c1.addDestino(c2, g);
            c2.addDestino(c1, g);
        }

        cidades = defineNovasCidades(cidades);

        int custoPedagios = getCustoPedagios(cidades, c);
        System.out.println(custoPedagios);
    }

    private static Map<Integer, Cidade> defineNovasCidades(Map<Integer, Cidade> cidades) {
        Map<Integer, Cidade> novasCidades = new HashMap<>();
        for (Cidade cidade : cidades.values()) {
            Cidade novaCidade = new Cidade(cidade.getName());
            novasCidades.put(cidade.getName(), novaCidade);
        }

        for (Cidade cidade : cidades.values()) {
            Cidade novaCidade = novasCidades.get(cidade.getName());
            for (Cidade cidadeAdjacenteNivel1 : cidade.cidadesAdjacentes.keySet()) {
                for (Cidade cidadeAdjacenteNivel2 : cidadeAdjacenteNivel1.cidadesAdjacentes.keySet()) {
                    if (cidade != cidadeAdjacenteNivel2) {
                        int pedagio1 = cidade.cidadesAdjacentes.get(cidadeAdjacenteNivel1);
                        int pedagio2 = cidadeAdjacenteNivel1.cidadesAdjacentes.get(cidadeAdjacenteNivel2);
                        int pedagioTotal = pedagio1 + pedagio2;

                        Cidade novaCidadeAdjacente = novasCidades.get(cidadeAdjacenteNivel2.getName());

                        Map<Cidade, Integer> cidadeDestinoMap = novaCidade.getCidadesAdjacentes();
                        if (!cidadeDestinoMap.containsKey(novaCidadeAdjacente) || cidadeDestinoMap.get(novaCidadeAdjacente) > pedagioTotal) {
                            novaCidade.addDestino(novaCidadeAdjacente, pedagioTotal);
                        }
                    }
                }
            }
            novasCidades.put(cidade.getName(), novaCidade);
        }
        return novasCidades;
    }

    private static int getCustoPedagios(Map<Integer, Cidade> cidades, int nomeCidadeDestino) {
        Cidade cidadeOrigem = cidades.get(1);
        Cidade cidadeDestino = cidades.get(nomeCidadeDestino);

        calculateShortestPathFromSource(cidades, cidadeOrigem);

        int pedagio = cidadeDestino.getPedagio();
        return pedagio != Integer.MAX_VALUE ? pedagio : -1;
    }

    public static Map<Integer, Cidade> calculateShortestPathFromSource(Map<Integer, Cidade> cidades, Cidade cidadeInicial) {
        cidadeInicial.setPedagio(0);

        Set<Cidade> cidadesResolvidas = new HashSet<>();
        Set<Cidade> cidadesIncertas = new HashSet<>();

        cidadesIncertas.add(cidadeInicial);

        while (cidadesIncertas.size() != 0) {
            Cidade cidadeAtual = pegaCidadeMenorPedagio(cidadesIncertas);
            cidadesIncertas.remove(cidadeAtual);
            for (Map.Entry<Cidade, Integer> cidadePedagio :
                    cidadeAtual.getCidadesAdjacentes().entrySet()) {
                Cidade cidadeAdjacente = cidadePedagio.getKey();
                Integer pedagio = cidadePedagio.getValue();
                if (!cidadesResolvidas.contains(cidadeAdjacente)) {
                    calculaMenorPedagio(cidadeAdjacente, pedagio, cidadeAtual);
                    cidadesIncertas.add(cidadeAdjacente);
                }
            }
            cidadesResolvidas.add(cidadeAtual);
        }
        return cidades;
    }

    private static Cidade pegaCidadeMenorPedagio(Set<Cidade> cidadesIncertas) {
        Cidade cidadepedagioMaisBaixo = null;
        int pedagioMaisBaixo = Integer.MAX_VALUE;
        for (Cidade cidade : cidadesIncertas) {
            int pedagio = cidade.getPedagio();
            if (pedagio < pedagioMaisBaixo) {
                pedagioMaisBaixo = pedagio;
                cidadepedagioMaisBaixo = cidade;
            }
        }
        return cidadepedagioMaisBaixo;
    }

    private static void calculaMenorPedagio(Cidade cidadeAdjacente, Integer pedagio, Cidade cidadeAtual) {
        Integer sourceDistance = cidadeAtual.getPedagio();
        if (sourceDistance + pedagio < cidadeAdjacente.getPedagio()) {
            cidadeAdjacente.setPedagio(sourceDistance + pedagio);
        }
    }
}

class Cidade {

    private final int name;
    Map<Cidade, Integer> cidadesAdjacentes = new HashMap<>();
    private Integer pedagio = Integer.MAX_VALUE;

    public Cidade(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public void addDestino(Cidade c2, int pedagio) {
        cidadesAdjacentes.put(c2, pedagio);
    }

    public Integer getPedagio() {
        return pedagio;
    }

    public void setPedagio(Integer pedagio) {
        this.pedagio = pedagio;
    }

    public Map<Cidade, Integer> getCidadesAdjacentes() {
        return cidadesAdjacentes;
    }

}