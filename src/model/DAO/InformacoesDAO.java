package model.DAO;

import model.bean.Informacoes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InformacoesDAO {

    Connection conn;
    Informacoes informacoes;

    public InformacoesDAO() {
        conn = null;
        informacoes = new Informacoes();
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public Informacoes getInformacoes() {
        return informacoes;
    }

    public void buscarInformacoesData(String dia, String mes, String ano) {
        String data = ano + "-" + mes + "-" + dia; // Ainda nao feito
    }

    public List<BigDecimal> listarDados() {
        List<BigDecimal> dados = new ArrayList<>();

        String selectSQL = "SELECT * from DADOS";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);

            while (rs.next()) {
                dados.add(new BigDecimal(rs.getString("variaveis")));
            }

        } catch (SQLException sqlE) {
            System.err.println(sqlE.getMessage());
        }
        return dados;
    }

    public boolean registrarVariavel(String valor) {

        String insertSQL = "INSERT INTO DADOS (variaveis) VALUES ('"
                + valor + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(sqlE.getMessage());
            return false;
        }
    }

    public boolean registrarInformacoes(Informacoes informacoes) {
        int id_informacoes = 0;
        BigDecimal[][] tabela = informacoes.getTabela();

        String selectSQL = "SELECT * FROM INFORMACOES ORDER BY id_informacoes DESC LIMIT 1";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                id_informacoes = rs.getInt("id_informacoes");
            }

            id_informacoes++;

            String insertSQL = "INSERT INTO INFORMACOES (id_distribuicao, data, numero_de_dados, xMax, xMin, log, classes, amplitude, amplitude_intervalos) VALUES ('"
                    + id_informacoes + "','"
                    + informacoes.getData() + "','"
                    + informacoes.getNumeroDeDadosColetados() + "','"
                    + informacoes.getMaiorVariavel() + "','"
                    + informacoes.getMenorVariavel() + "','"
                    + informacoes.getLog() + "','"
                    + informacoes.getClasses() + "','"
                    + informacoes.getAmplitude() + "','"
                    + informacoes.getAmplitudeIntervalos() + "')";

            st.executeUpdate(insertSQL);

            for (BigDecimal[] bigDecimals : tabela) {
                String insertClasseTabela = "INSERT INTO DISTRIBUICAO (id_distribuicao, lim_inferior, lim_superior, fi, xi, fri, fri_porcentagem, fac, fac_porcentagem) VALUES ('"
                        + id_informacoes + "','"
                        + bigDecimals[0] + "','"
                        + bigDecimals[1] + "','"
                        + bigDecimals[2] + "','"
                        + bigDecimals[3] + "','"
                        + bigDecimals[4] + "','"
                        + bigDecimals[5] + "','"
                        + bigDecimals[6] + "','"
                        + bigDecimals[7] + "')";

                st.executeUpdate(insertClasseTabela);
            }
            return true;
        } catch (SQLException sqlE) {
            System.err.println(sqlE.getMessage());
            return false;
        }
    }
}
