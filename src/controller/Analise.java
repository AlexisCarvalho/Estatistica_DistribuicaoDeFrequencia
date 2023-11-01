package controller;

import model.DistribuicaoDeFrequencia;
import model.bean.Informacoes;

import java.math.BigDecimal;
import java.util.List;

public class Analise {

    DistribuicaoDeFrequencia distriDeFreque;
    BigDecimal[][] tabela;
    Informacoes informacoes;

    public Analise() {
        distriDeFreque = new DistribuicaoDeFrequencia();
        informacoes = new Informacoes();
    }

    public void gerarResultados(List<BigDecimal> list) {
        distriDeFreque.efetuarCalculos(list);
        informacoes = distriDeFreque.getInformacoes();
        tabela = informacoes.getTabela();
    }

    public BigDecimal[][] getTabela() {
        return tabela;
    }

    public int getNumeroDeDadosColetados() {
        return informacoes.getNumeroDeDadosColetados().intValue();
    }

    public BigDecimal getMaiorVariavel() {
        return informacoes.getMaiorVariavel();
    }

    public BigDecimal getMenorVariavel() {
        return informacoes.getMenorVariavel();
    }

    public BigDecimal getLog() {
        return informacoes.getLog();
    }

    public int getClasses() {
        return informacoes.getClasses().intValue();
    }

    public BigDecimal getAmplitudeAmostral() {
        return informacoes.getAmplitude();
    }

    public BigDecimal getAmplitudeIntervalos() {
        return informacoes.getAmplitudeIntervalos();
    }

    public BigDecimal[] getLimitesInferiores() {
        BigDecimal[] limitesInferiores = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            limitesInferiores[smtr] = tabela[smtr][0];
        }
        return limitesInferiores;
    }

    public BigDecimal[] getLimitesSuperiores() {
        BigDecimal[] limitesSuperiores = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            limitesSuperiores[smtr] = tabela[smtr][1];
        }
        return limitesSuperiores;
    }

    public BigDecimal[] getFrequenciasAbsolutas() {
        BigDecimal[] frequenciasAbsolutas = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasAbsolutas[smtr] = tabela[smtr][2];
        }
        return frequenciasAbsolutas;
    }

    public BigDecimal[] getPontosMedios() {
        BigDecimal[] pontosMedios = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            pontosMedios[smtr] = tabela[smtr][3];
        }
        return pontosMedios;
    }

    public BigDecimal[] getFrequenciasRelativas() {
        BigDecimal[] frequenciasRelativas = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasRelativas[smtr] = tabela[smtr][4];
        }
        return frequenciasRelativas;
    }

    public BigDecimal[] getFrequenciasRelativasPorcentagem() {
        BigDecimal[] frequenciaRelativaPorcentagem = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciaRelativaPorcentagem[smtr] = tabela[smtr][5];
        }
        return frequenciaRelativaPorcentagem;
    }

    public BigDecimal[] getFrequenciasAcumuladas() {
        BigDecimal[] frequenciasAcumuladas = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasAcumuladas[smtr] = tabela[smtr][6];
        }
        return frequenciasAcumuladas;
    }

    public BigDecimal[] getFrequenciasAcumuladasPorcentagem() {
        BigDecimal[] frequenciasAcumuladasPorcentagem = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasAcumuladasPorcentagem[smtr] = tabela[smtr][7];
        }
        return frequenciasAcumuladasPorcentagem;
    }
}
