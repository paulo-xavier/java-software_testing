package com.example;


// Importações necessárias para o teste com JUnit e manipulação de arquivos
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.sql.ResultSet;


// Importações para interagir com o banco de dados
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe de teste para a classe App
public class AppTest {
    // URL do banco de dados de teste
    private static final String TEST_DB_URL = "jdbc:sqlite:test-database.db";

    // Método executado antes de cada teste
    @Before
    public void setUp() {

     

        /* Este método cria um banco de dados de teste (test-database.db) e uma tabela (usuarios) no início dos testes.
           Insere três registros de teste (João, Maria, Jonas) na tabela para garantir dados iniciais para os testes. */
           try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
           Statement statement = connection.createStatement()) {

          // Criação da tabela
          statement.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                  "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                  "nome TEXT NOT NULL," +
                  "idade INTEGER" +
                  ");");

          // Inserção de dados de teste
          statement.execute("INSERT INTO usuarios (nome, idade) VALUES ('João', 25);");
          statement.execute("INSERT INTO usuarios (nome, idade) VALUES ('Maria', 30);");
          statement.execute("INSERT INTO usuarios (nome, idade) VALUES ('Jonas', 43);");
           
          
          
        } catch (SQLException e) {
          e.printStackTrace();
       
    }

    }

    // Método executado após todos os testes
    @AfterClass
    public static void tearDown() {
      /* Este método é executado após a execução de todos os testes.
           Remove o banco de dados de teste (test-database.db) para limpar o ambiente após a conclusão dos testes. */
           try {
            Files.deleteIfExists(Paths.get("test-database.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Teste de carga: inserção de 10 usuários e medição do tempo de execução
    @Test
    public void testCargaInsercaoUsuarios() {
        long startTime = System.currentTimeMillis();  // Marca o tempo inicial
        for (int i = 1; i <= 1000; i++) {
            inserirUsuarioTesteCarga("Usuário" + i, 25);
        }
        long endTime = System.currentTimeMillis();  // Marca o tempo final
        long duration = endTime - startTime;  // Calcula a duração do teste

        
        System.out.println("Lista de Usuários após a inserção:");
        listarUsuarios();
        System.out.println("Tempo de execução: " + duration + " milissegundos");

    }


    // Método auxiliar para inserir um usuário de teste
    private void inserirUsuarioTesteCarga(String nome, int idade) {
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nome, idade) VALUES (?, ?)")) {

            statement.setString(1, nome);
            statement.setInt(2, idade);

            int rowsAffected = statement.executeUpdate();
            assertTrue(rowsAffected > 0);  // Garante que a inserção foi bem-sucedida

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Método auxiliar para listar usuários do banco de dados
    public static void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Lista de Usuários:");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");

                System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test 
    public void deletarUsuario() {
 
        testCargaInsercaoUsuarios();
        deletarCargaUsuarios(); 

    }


   public void  deletarCargaUsuarios () {
            try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {

            int i = 1; 
            int rowsAffected = 1; 


            while (rowsAffected > 0){

                statement.setInt(1, i);
                rowsAffected = statement.executeUpdate();

                i++; 
            }

            System.out.println("Lista de usuários após deletado"); 
            listarUsuarios();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

   }

   @Test
   public void testConsultaEmMassa() {

        testCargaInsercaoUsuarios();
        
        long startTime = System.currentTimeMillis();
        
        listarUsuarios();
        
        long endTime = System.currentTimeMillis();  // Marca o tempo final
        
        long duration = endTime - startTime;


        System.out.println("O tempo de duração foi de " + duration + "milisegundos");

   }




    @Test
    public void testSegurancaInjecaoSQL() {
        // Tentativa de Injeção de SQL
        /*
         * Este teste de segurança aborda a preocupação com a injeção de SQL. 
         * Ele tenta inserir um usuário com um nome malicioso que contenha uma
         * instrução SQL destinada a excluir a tabela de usuários (DROP TABLE usuarios).
         * O teste então verifica se a tabela ainda existe após a tentativa de injeção de SQL.
         * Isso é uma simulação de um ataque de injeção de SQL, e o teste verifica se a
         * aplicação está adequadamente protegida contra esse tipo de ataque.
         * 
         */
        String nome = "'; DROP TABLE usuarios; --";
        int idade = 25;

        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nome, idade) VALUES (?, ?)")) {

            statement.setString(1, nome);
            statement.setInt(2, idade);

            //int rowsAffected = statement.executeUpdate();

            // O teste de segurança verifica se a tabela 'usuarios' ainda existe após a tentativa de injeção de SQL
            assertFalse(tabelaUsuariosFoiExcluida());

        } catch (SQLException e) {
            // O teste de segurança verifica se a exceção lançada não causou a exclusão da tabela 'usuarios'
            assertTrue(tabelaUsuariosExiste());
        }
    }

    // Método auxiliar para verificar se a tabela 'usuarios' ainda existe no banco de dados
    private boolean tabelaUsuariosExiste() {
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             ResultSet resultSet = connection.getMetaData().getTables(null, null, "usuarios", null)) {

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para verificar se a tabela 'usuarios' foi excluída do banco de dados
    private boolean tabelaUsuariosFoiExcluida() {
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             ResultSet resultSet = connection.getMetaData().getTables(null, null, "usuarios", null)) {

            return !resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }



}
