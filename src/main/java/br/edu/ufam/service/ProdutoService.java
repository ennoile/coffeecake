package br.edu.ufam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import br.edu.ufam.config.ConexaoDatabase;
import br.edu.ufam.model.ProdutoModel;

public class ProdutoService {
    public List<ProdutoModel> listarProdutos() {
        List<ProdutoModel> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco, descricao from produto";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getFloat("preco"),
                        rs.getString("descricao"));
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pridutos!" + e.getMessage());
        }
        return produtos;
    }

    public void cadastrarProduto(ProdutoModel produto) {
        String sql = "INSERT INTO produto(id, nome, preco, descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setFloat(3, produto.getPreco());
            stmt.setString(4, produto.getDescricao());

            int linha = stmt.executeUpdate();

            if (linha > 0) {
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar usuário.");
            }
        } catch (SQLException e) {
            System.out.println("Error ao cadastrar Produto!" + e.getMessage());
        }
    }

    public void alterarProduto(String nomeBusca, ProdutoModel produto){
        String sql ="UPDATE produto SET id = ?, nome = ?, preco = ?, descricao = ? WHERE nome = ?";

        try(Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
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
        } catch(SQLException e){
        System.out.println("Error ao alterar produto!" + e.getMessage());
        }
    }

    public ProdutoModel pesquisarProduto(String nome){
        ProdutoModel produto = null;
        String sql =" SELECT id, nome, preco, descricao FROM produto WHERE nome = ?";

        try(Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setString(1, nome);

                    ResultSet rs = stmt.executeQuery();

                    if(rs.next()){
                        produto = new ProdutoModel(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getFloat("preco"),
                            rs.getString("descricao"));
                    }else{
                        System.out.println("Produto não encontrado!");
                    }
        }catch(SQLException e){
            System.out.println("Erro ao pesquisar produto("+nome+"): "+e.getMessage());
        }
        
        return produto;
    }
}
