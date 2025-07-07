package br.edu.ufam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import br.edu.ufam.config.ConexaoDatabase;
import br.edu.ufam.model.ComandaModel;
import br.edu.ufam.model.ComandaProdutoTableModel;

public class ComandaService {
    public void cadastrarComanda(ComandaModel comanda, List<ComandaProdutoTableModel> produtosComanda) {
        String sql = "INSERT INTO comanda (data_criacao, status, fk_cliente, fk_usuario, valor_total) VALUES (?, ?, ?, ?, ?)";

        int idComanda = -1;

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, new Timestamp(System.currentTimeMillis()).toString());
            stmt.setString(2, comanda.getStatus());
            stmt.setInt(3, comanda.getCliente().getId());
            stmt.setInt(4, comanda.getUsuario().getId());
            stmt.setFloat(5, comanda.getValorTotal());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idComanda = rs.getInt(1);
                }
                System.out.println("Comanda cadastrada com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar comanda.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar comanda: " + e.getMessage());
        }

        if (produtosComanda != null && !produtosComanda.isEmpty() && idComanda > 0) {
            String sqlProduto = "INSERT INTO comanda_produto (quantidade_produto, fk_comanda, fk_produto) VALUES (?, ?, ?)";

            try (Connection conn = ConexaoDatabase.getConnection();
                    PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto)) {
                for (ComandaProdutoTableModel produto : produtosComanda) {
                    stmtProduto.setInt(1, produto.getQuantidade());
                    stmtProduto.setInt(2, idComanda);
                    stmtProduto.setInt(3, produto.getId());
                    stmtProduto.addBatch();
                }
                int[] linhasProdutos = stmtProduto.executeBatch();
                System.out.println("Produtos da comanda cadastrados com sucesso: " + linhasProdutos.length);
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar produtos da comanda: " + e.getMessage());
            }
        }
    }
}
