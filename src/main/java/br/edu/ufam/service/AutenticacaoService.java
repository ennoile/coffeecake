package br.edu.ufam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import br.edu.ufam.config.ConexaoDatabase;
import br.edu.ufam.model.UsuarioModel;

public class AutenticacaoService {
    private boolean autenticado = false;
    private UsuarioModel usuarioLogado = null;
    private Scanner scanner = new Scanner(System.in);

    public boolean isAutenticado() {
        return autenticado;
    }

    public UsuarioModel getUsuarioLogado() {
        return usuarioLogado;
    }

    public void login() {
        System.out.print("login: ");
        String login = scanner.nextLine();
        System.out.print("senha: ");
        String senha = scanner.nextLine();

        String sql = "SELECT id, nome, telefone, email, login, funcao FROM usuario WHERE login = ? AND senha = ?";

        try (Connection conn = ConexaoDatabase.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("login"),
                        "********",
                        rs.getString("funcao"));

                this.autenticado = true;
                this.usuarioLogado = usuario;

                System.out.println("Usuário " + usuario.getNome() + " autenticado com sucesso!");
            } else {
                System.out.println("Usuário ou senha inválidos.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar usuários: " + e.getMessage());
        }
    }

    public void logout() {
        this.autenticado = false;
        this.usuarioLogado = null;
        System.out.println("Usuário deslogado com sucesso!");
    }
}
