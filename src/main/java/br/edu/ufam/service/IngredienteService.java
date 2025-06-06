package br.edu.ufam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufam.config.ConexaoDatabase;
import br.edu.ufam.model.IngredienteModel;

public class IngredienteService {
    public List<IngredienteModel> listarIngrediente() {
        List<IngredienteModel> ingredientes = new ArrayList<>();
        String sql = "SELECT ID_Ingrediente, NomeIngrediente, Descricao FROM ingrediente";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                IngredienteModel ingrediente = new IngredienteModel(
                        rs.getInt("ID_Ingrediente"),
                        rs.getString("NomeIngrediente"),
                        rs.getString("Descricao"));

                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar ingredientes: " + e.getMessage());
        }

        return ingredientes;
    }

    public void cadastrarIngrediente(IngredienteModel ingrediente) {
        String sql = "INSERT INTO ingrediente (NomeIngrediente, Descricao) VALUES (?, ?)";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setString(2, ingrediente.getDescricao());

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                System.out.println("Ingrediente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar ingrediente.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar ingrediente: " + e.getMessage());
        }
    }

    public void alterarIngrediente(String nomeBusca, IngredienteModel ingrediente) {
        String sql = "UPDATE ingrediente SET NomeIngrediente = ?, Descricao = ?  WHERE NomeIngrediente = ?";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setString(2, ingrediente.getDescricao());
            stmt.setString(3, nomeBusca);

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                System.out.println("Ingrediente alterado com sucesso!");
            } else {
                System.out.println("Falha ao alterar ingrediente.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar ingrediente: " + e.getMessage());
        }
    }

    public IngredienteModel pesquisarIngrediente(String nome) {
        IngredienteModel ingrediente = null;
        String sql = "SELECT ID_Ingrediente, NomeIngrediente, Descricao FROM ingrediente WHERE NomeIngrediente = ?";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ingrediente = new IngredienteModel(
                        rs.getInt("ID_Ingrediente"),
                        rs.getString("NomeIngrediente"),
                        rs.getString("Descricao"));
            } else {
                System.out.println("Ingrediente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar ingrediente (" + nome + "): " + e.getMessage());
        }

        return ingrediente;
    }
}
