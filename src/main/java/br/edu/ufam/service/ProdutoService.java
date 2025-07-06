package br.edu.ufam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufam.config.ConexaoDatabase;
import br.edu.ufam.model.EstoqueProduto;
import br.edu.ufam.model.ProdutoModel;

public class ProdutoService {
    public List<ProdutoModel> listarProdutos() {
        List<ProdutoModel> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco, descricao, quantidade_estoque from produto";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel(
                        rs.getInt("id_produto"),
                        rs.getString("nome"),
                        rs.getFloat("preco"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade_estoque"));
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pridutos!" + e.getMessage());
        }
        return produtos;
    }

    public ProdutoModel cadastrarProduto(ProdutoModel produto) {
        String sql = "INSERT INTO produto(nome, preco, descricao, quantidade_disponivel) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produto.getNome());
            stmt.setFloat(2, produto.getPreco());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getQuantidade());

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        produto.setId(generatedKeys.getInt(1));
                        System.out.println("Produto cadastrado com sucesso!");
                        return produto;
                    }
                }
            } else {
                System.out.println("Falha ao cadastrar produto.");
            }
        } catch (SQLException e) {
            System.out.println("Error ao cadastrar produto!" + e.getMessage());
        }

        return null;
    }

    public void alterarProduto(String nomeBusca, ProdutoModel produto) {
        String sql = "UPDATE produto SET id = ?, nome = ?, preco = ?, descricao = ? WHERE nome = ?";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setFloat(3, produto.getPreco());
            stmt.setString(4, produto.getDescricao());

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                System.out.println("Produto alterado com sucesso!");
            } else {
                System.out.println("Falha ao alterar produto.");
            }
        } catch (SQLException e) {
            System.out.println("Error ao alterar produto!" + e.getMessage());
        }
    }

    public ProdutoModel pesquisarProduto(String nome) {
        ProdutoModel produto = null;
        String sql = " SELECT id, nome, preco, descricao FROM produto WHERE nome = ?";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new ProdutoModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getFloat("preco"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade_disponivel"));
            } else {
                System.out.println("Produto não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar produto(" + nome + "): " + e.getMessage());
        }

        return produto;
    }

    public void cadastrarEstoque(EstoqueProduto estoque) {
        String sql = "INSERT INTO estoque_produto(id_produto, quantidade, data_entrada) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getProduto().getId());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.setTimestamp(3, Timestamp.valueOf(estoque.getDataEntrada()));

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                System.out.println("Estoque cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar estoque.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar estoque: " + e.getMessage());
        }
    }

    public void alterarEstoque(EstoqueProduto estoque) {
        String sql = "UPDATE estoque_produto SET quantidade = ?, data_entrada = ? WHERE id_produto = ?";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setTimestamp(2, Timestamp.valueOf(estoque.getDataEntrada()));
            stmt.setInt(3, estoque.getProduto().getId());

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                System.out.println("Estoque alterado com sucesso!");
            } else {
                System.out.println("Falha ao alterar estoque.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar estoque: " + e.getMessage());
        }
    }
}
