package model.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Variaveis {

    public List<BigDecimal> listarDados(Connection conn) {
        List<BigDecimal> dados = new ArrayList<>();

        String selectSQL = "SELECT * from dados";

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

    public boolean registrar(String valor, Connection conn) {

        String insertSQL = "INSERT INTO dados (variaveis) VALUES ('"
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
}
