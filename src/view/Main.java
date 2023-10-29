package view;

import controller.Analise;
import model.DAO.VariaveisDAO;
import model.DistribuicaoDeFrequencia;
import util.DbConnect;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DistribuicaoDeFrequencia distriDeFre = new DistribuicaoDeFrequencia();
        Analise analise = new Analise();
        Scanner scanner = new Scanner(System.in);
        VariaveisDAO variaveisDao = new VariaveisDAO();
        Random random = new Random();
        Connection conn = null;

        while (conn == null) {
            System.out.println(DbConnect.status);
            System.out.println("Digite o nome do arquivo do banco SQLite de onde virao os dados\n(Nomes nao reconhecido gerarao um novo banco vazio)");
            String banco = scanner.next();
            conn = DbConnect.getConexaoSQLITE(banco);
        }

        int resposta = 0;
        while (resposta < 1 || resposta > 3) {
            System.out.println("Deseja registrar dados no banco? \n1) Sim, gerar dados aleatorios\n2) Sim, irei registrar dados manualmente\n3) Nao, o banco ja inclui os dados necessarios");
            resposta = scanner.nextInt();
        }

        switch (resposta) {
            case 1:
                System.out.println("Quantas variaveis deseja adicionar? (Inteiro)");
                int variaveisNovas = scanner.nextInt();
                System.out.println("Os numeros gerados respeitarao a escala de 0 a ? (Real)");
                double escala = scanner.nextDouble();

                for (int a = 0; a < variaveisNovas; a++) {
                    variaveisDao.registrar(String.valueOf(new BigDecimal(String.valueOf(random.nextDouble(escala)), new MathContext(3))), conn);
                }
                break;

            case 2:

                String variavel = "";
                int indice = 1;
                System.out.println("Digite as variaveis uma por uma, quando derminar digite E");

                while (!variavel.equals("e") && !variavel.equals("E")) {
                    System.out.print(indice + "ª: ");
                    variavel = scanner.next();

                    if (!variavel.equals("e") && !variavel.equals("E")) {
                        variaveisDao.registrar(variavel, conn);
                        indice++;
                    }
                }

                break;

            default:

                break;
        }

        analise.gerarTabela(variaveisDao.listarDados(conn));

        System.out.println("Numero de dados coletados: " + analise.getNumeroDeDadosColetados());
        System.out.println("xMax: " + analise.getMaiorVariavel());
        System.out.println("xMin: " + analise.getMenorVariavel());
        System.out.println("log: " + analise.getLog());
        System.out.println("Classes: " + analise.getClasses());
        System.out.println("Amplitude: "+ analise.getAmplitudeAmostral());
        System.out.println("Amplitude Intervalos: "+ analise.getAmplitudeIntervalos());

        BigDecimal[] limitesInferiores = analise.getLimitesInferiores();
        BigDecimal[] limitesSuperiores = analise.getLimitesSuperiores();
        BigDecimal[] frequenciasAbsolutas = analise.getFrequenciasAbsolutas();
        BigDecimal[] pontosMedios = analise.getPontosMedios();
        BigDecimal[] frequenciasRelativas = analise.getFrequenciasRelativas();
        BigDecimal[] frequenciaRelativaPorcentagem = analise.getFrequenciasRelativasPorcentagem();
        BigDecimal[] frequenciasAcumuladas = analise.getFrequenciasAcumuladas();
        BigDecimal[] frequenciasAcumuladasPorcentagem = analise.getFrequenciasAcumuladasPorcentagem();

        System.out.println(" li      Li   fi     xi     fri    fri%    fac    fac%");

        for(int smtr = 0;smtr < (analise.getTabela()[0].length - 1);smtr++){
            System.out.println(
                    limitesInferiores[smtr] + " --| " + limitesSuperiores[smtr] +
                    " |"+frequenciasAbsolutas[smtr]+"| " +
                    " |"+pontosMedios[smtr]+"| " +
                    " |"+frequenciasRelativas[smtr]+"| " +
                    " |"+frequenciaRelativaPorcentagem[smtr]+"| " +
                    " |"+frequenciasAcumuladas[smtr]+"| " +
                    " |"+frequenciasAcumuladasPorcentagem[smtr]+"| "
            );
        }
    }
}