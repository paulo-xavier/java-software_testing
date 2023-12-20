package com.example;
// Importações necessárias para trabalhar com JDBC e SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

// Classe principal do aplicativo
public class App2 {
    // Método para inserir um novo usuário no banco de dados
    public static void inserirUsuario(String nome, int idade) {
        // Declaração SQL para a inserção de dados na tabela "usuarios"
        String sql = "INSERT INTO usuarios (nome, idade) VALUES (?, ?)";

        try (Connection connection = Connector.connect();  // Estabelece a conexão com o banco de dados
             PreparedStatement statement = connection.prepareStatement(sql)) {  // Prepara a instrução SQL

            // Define os parâmetros da instrução SQL com os valores fornecidos
            statement.setString(1, nome);
            statement.setInt(2, idade);

            // Executa a instrução SQL para realizar a inserção
            statement.executeUpdate();
        } catch (SQLException e) {
            // Trata exceções relacionadas ao acesso ao banco de dados
            e.printStackTrace();
        }
    }

    // Método principal do aplicativo
    public static void main(String[] args) {
        // Insere dois usuários no banco de dados como exemplo
        inserirUsuario("Alice", 25);
        inserirUsuario("Bob", 30);

        // Lista os usuários presentes no banco de dados
        listarUsuarios();
    }

    // Método para listar todos os usuários do banco de dados
    public static void listarUsuarios() {
        // Declaração SQL para selecionar todos os registros da tabela "usuarios"
        String sql = "SELECT * FROM usuarios";

        try (Connection connection = Connector.connect();  // Estabelece a conexão com o banco de dados
             PreparedStatement statement = connection.prepareStatement(sql);  // Prepara a instrução SQL
             ResultSet resultSet = statement.executeQuery()) {  // Executa a consulta e obtém o resultado

            // Imprime no console a lista de usuários
            System.out.println("Lista de Usuários:");

            while (resultSet.next()) {
                // Obtém os dados de cada usuário do resultado
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");

                // Imprime no console os detalhes do usuário
                System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
            }

        } catch (SQLException e) {
            // Trata exceções relacionadas ao acesso ao banco de dados
            e.printStackTrace();
        }
    }


    
}

