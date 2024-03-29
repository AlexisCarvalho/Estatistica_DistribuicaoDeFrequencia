package controller;

import model.DAO.InformacoesDAO;
import model.DistribuicaoDeFrequencia;
import model.bean.Informacoes;
import util.DbConnect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class Analise {

    Connection conn;
    private DistribuicaoDeFrequencia distriDeFreque;
    private BigDecimal[][] tabela;
    private Informacoes informacoes;
    private InformacoesDAO informacoesDAO;

    public Analise() {
        distriDeFreque = new DistribuicaoDeFrequencia();
        informacoes = new Informacoes();
        informacoesDAO = new InformacoesDAO();
        conn = null;
    }

    public boolean conectarAoBanco(String nomeBanco) {
        conn = DbConnect.getConexaoSQLITE(nomeBanco);
        if (conn != null) {
            informacoesDAO.setConnection(conn);
            return true;
        }
        return false;
    }

    public void adicionarVariavelAoBanco(String variavel) {
        informacoesDAO.registrarVariavel(variavel);
    }

    public List<BigDecimal> getDadosBanco() {
        return informacoesDAO.listarDados();
    }

    public void gerarResultados(List<BigDecimal> list) {
        distriDeFreque.efetuarCalculos(list);
        informacoes = distriDeFreque.getInformacoes();
        informacoes.setData(String.valueOf(LocalDate.now()));
        tabela = informacoes.getTabela();
    }

    public void salvarNoBanco() {
        informacoesDAO.registrarInformacoes(informacoes);
    }

    public BigDecimal[][] getTabela() {
        return tabela;
    }

    public String getData() {
        return informacoes.getData();
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
