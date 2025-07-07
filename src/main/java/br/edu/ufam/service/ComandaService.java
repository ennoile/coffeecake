package br.edu.ufam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.edu.ufam.config.ConexaoDatabase;
import br.edu.ufam.model.ComandaModel;

public class ComandaService {
    public void cadastrarComanda(ComandaModel comanda) {
        String sql = "INSERT INTO comanda (data_criacao, status, fk_cliente, fk_usuario, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, new Timestamp(System.currentTimeMillis()).toString());
            stmt.setString(2, comanda.getStatus());
            stmt.setInt(3, comanda.getCliente().getId());
            stmt.setInt(4, comanda.getUsuario().getId());
            stmt.setFloat(5, comanda.getValorTotal());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Comanda cadastrada com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar comanda.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar comanda: " + e.getMessage());
        }
    }
}
