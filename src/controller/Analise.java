package controller;

import model.DistribuicaoDeFrequencia;

import java.math.BigDecimal;
import java.util.List;

public class Analise {

    DistribuicaoDeFrequencia distriDeFreque;
    BigDecimal[][] tabela;
    public Analise(){
        distriDeFreque = new DistribuicaoDeFrequencia();
    }

    public boolean gerarTabela(List<BigDecimal> list){
        distriDeFreque.efetuarCalculos(list);
        tabela = distriDeFreque.getTabela();
        if(tabela instanceof BigDecimal[][]){
            return true;
        }
        return false;
    }

    public BigDecimal[][] getTabela(){
        return tabela;
    }

    public int getNumeroDeDadosColetados(){
        return distriDeFreque.getNumeroDeDadosColetados();
    }

    public BigDecimal getMaiorVariavel(){
        return distriDeFreque.getMaiorVariavel();
    }

    public BigDecimal getMenorVariavel(){
        return distriDeFreque.getMenorVariavel();
    }

    public BigDecimal getLog(){
        return distriDeFreque.getLog();
    }

    public int getClasses(){
        return distriDeFreque.getClasses();
    }

    public BigDecimal getAmplitudeAmostral(){
        return distriDeFreque.getAmplitudeAmostral();
    }

    public BigDecimal getAmplitudeIntervalos(){
        return distriDeFreque.getAmplitudeIntervalos();
    }

    public BigDecimal[] getLimitesInferiores(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] limitesInferiores = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            limitesInferiores[smtr] = tabela[smtr][0];
        }
        return limitesInferiores;
    }

    public BigDecimal[] getLimitesSuperiores(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] limitesSuperiores = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            limitesSuperiores[smtr] = tabela[smtr][1];
        }
        return limitesSuperiores;
    }

    public BigDecimal[] getFrequenciasAbsolutas(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] frequenciasAbsolutas = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            frequenciasAbsolutas[smtr] = tabela[smtr][2];
        }
        return frequenciasAbsolutas;
    }

    public BigDecimal[] getPontosMedios(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] pontosMedios = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            pontosMedios[smtr] = tabela[smtr][3];
        }
        return pontosMedios;
    }

    public BigDecimal[] getFrequenciasRelativas(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] frequenciasRelativas = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            frequenciasRelativas[smtr] = tabela[smtr][4];
        }
        return frequenciasRelativas;
    }

    public BigDecimal[] getFrequenciasRelativasPorcentagem(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] frequenciaRelativaPorcentagem = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            frequenciaRelativaPorcentagem[smtr] = tabela[smtr][5];
        }
        return frequenciaRelativaPorcentagem;
    }

    public BigDecimal[] getFrequenciasAcumuladas(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] frequenciasAcumuladas = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            frequenciasAcumuladas[smtr] = tabela[smtr][6];
        }
        return frequenciasAcumuladas;
    }

    public BigDecimal[] getFrequenciasAcumuladasPorcentagem(){
        tabela = distriDeFreque.getTabela();
        BigDecimal[] frequenciasAcumuladasPorcentagem = new BigDecimal[tabela.length];

        for(int smtr = 0;smtr < tabela.length;smtr++){
            frequenciasAcumuladasPorcentagem[smtr] = tabela[smtr][7];
        }
        return frequenciasAcumuladasPorcentagem;
    }
}
