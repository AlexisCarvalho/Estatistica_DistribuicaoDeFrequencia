package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

    public static String status = "Não conectou";
    private static final String tableDados =
            "create table IF NOT EXISTS dados(" +
                    "variaveis text" +
                    ")";
    private static final String tableDistribuicao =
            "create table IF NOT EXISTS distribuicao(" +
                    "data text," +
                    "fi text," +
                    "xi text," +
                    "fri text," +
                    "friPorcentagem text," +
                    "fac text," +
                    "facPorcentagem text" +
                    ")";

    // TODO: Adicionar sistema para enviar o relatorio ao banco

    DbConnect() {
    }

    public static Connection getConexaoSQLITE(String banco) {
        Connection connection = null;

        try {
            String myDatabase = banco + ".db";
            String url = "jdbc:sqlite:" + myDatabase;

            connection = DriverManager.getConnection(url);

            if (connection != null) {
                status = "Conectado com sucesso";
            } else {
                status = "Nao foi possivel conectar";
                System.err.println(status);
            }

            Statement st = connection.createStatement();
            st.executeUpdate(tableDados);
            st.executeUpdate(tableDistribuicao);

            return connection;

        } catch (SQLException sqlE) {
            System.err.println(status);
            System.err.println(sqlE.getMessage());
            return null;
        }
    }
}
