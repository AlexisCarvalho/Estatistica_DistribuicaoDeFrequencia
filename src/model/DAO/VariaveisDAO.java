package model.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VariaveisDAO {

    public List<BigDecimal> listarDados(Connection conn){
        List dados = new ArrayList<BigDecimal>();

        String selectSQL = "SELECT * from dados";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);

            while(rs.next()){
                dados.add(new BigDecimal(rs.getString("variaveis")));
            }

        } catch (SQLException e){

        }
        return dados;
    }

    public boolean registrar(String valor,Connection conn) {

        String insertSQL = "INSERT INTO dados (variaveis) VALUES ('"
                + valor + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
