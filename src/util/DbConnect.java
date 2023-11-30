package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

    public static String status = "Não conectou";
    private static final String tableDados =
            "create table IF NOT EXISTS DADOS(" +
                    "variaveis text" +
                    ")";
    private static final String tableDistribuicao =
            "create table IF NOT EXISTS DISTRIBUICAO(" +
                    "id_distribuicao integer," +
                    "lim_inferior text," +
                    "lim_superior text," +
                    "fi text," +
                    "xi text," +
                    "fri text," +
                    "fri_porcentagem text," +
                    "fac text," +
                    "fac_porcentagem text" +
                    ")";

    private static final String tableInformacoes =
            "create table IF NOT EXISTS INFORMACOES(" +
                    "id_informacoes integer primary key autoincrement," +
                    "id_distribuicao integer," +
                    "data text," +
                    "numero_de_dados integer," +
                    "xMax text," +
                    "xMin text," +
                    "log text," +
                    "classes text," +
                    "amplitude text," +
                    "amplitude_intervalos text" +
                    ")";

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
            st.executeUpdate(tableInformacoes);

            return connection;

        } catch (SQLException sqlE) {
            System.err.println(status);
            System.err.println(sqlE.getMessage());
            return null;
        }
    }
}
