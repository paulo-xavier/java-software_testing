package com.example;
// Importações necessárias para trabalhar com JDBC e SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

// Classe que gerencia a conexão com o banco de dados SQLite
public class Connector {
    // URL de conexão com o banco de dados SQLite
    private static final String URL = "jdbc:sqlite:nome-do-banco-de-dados.db";

    // Método para estabelecer a conexão com o banco de dados
    public static Connection connect() throws SQLException {
        try {
            // Carrega o driver JDBC do SQLite
            Class.forName("org.sqlite.JDBC");

            // Estabelece a conexão com o banco de dados
            Connection connection = DriverManager.getConnection(URL);

            // Cria a tabela "usuarios" se ela não existir
            createTableIfNotExists(connection);

            // Retorna a conexão estabelecida
            return connection;
        } catch (ClassNotFoundException e) {
            // Lança uma exceção se o driver JDBC não for encontrado
            throw new SQLException("SQLite JDBC Driver not found", e);
        }
    }

    // Método para criar a tabela "usuarios" se ela não existir
    private static void createTableIfNotExists(Connection connection) throws SQLException {
        // Obtém metadados do banco de dados para verificar a existência da tabela
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "usuarios", null);

        // Se a tabela "usuarios" não existir, cria a tabela
        if (!resultSet.next()) {
            // Definição da instrução SQL para criar a tabela
            String createTableSQL = "CREATE TABLE usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "idade INTEGER" +
                    ");";

            // Cria a tabela utilizando uma declaração SQL
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
            }
        }
    }
}
