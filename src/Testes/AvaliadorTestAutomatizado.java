package Testes;

import Model.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvaliadorTestAutomatizado {

    private Usuario joao;
    private Usuario jose;
    private Usuario maria;
    private Avaliador leiloeiro;

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){
        // cenário: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");
        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));
        // executando a ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        // comparando a saída com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
    }
    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario joao = new Usuario("Joao");
        Leilao leilao = new Leilao("Playstation 3 Novo");
        leilao.propoe(new Lance(joao, 1000.0));
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");
        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }

    @BeforeEach
    private void instanciarObjetos() {
        joao = new Usuario("Joao");
        jose = new Usuario("José");
        maria = new Usuario("Maria");
        leiloeiro = new Avaliador();
        Leilao leilao = new Leilao("Playstation 3 Novo");
    }
}
