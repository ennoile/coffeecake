package br.edu.ufam.view;

import java.util.List;
import java.util.Scanner;

import br.edu.ufam.model.UsuarioModel;
import br.edu.ufam.service.UsuarioService;

public class UsuarioView {
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioService service = new UsuarioService();

    public static void executar() {
        int opcao = 1;

        while (opcao != 0) {
            menu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    alterarUsuario();
                    break;
                case 3:
                    pesquisarUsuario();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 0:
                    System.out.println("Voltando.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    private static void menu() {
        System.out.println("--------USUARIO--------");
        System.out.println("| 1 - Cadastrar       |");
        System.out.println("| 2 - Alterar         |");
        System.out.println("| 3 - Pesquisar (CPF) |");
        System.out.println("| 4 - Listar          |");
        System.out.println("| 0 - Voltar          |");
        System.out.print("Opção: ");
    }

    private static void cadastrarUsuario() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Função (1 - Gerente, 2 - Funcionário): ");
        int escolhaFuncao = scanner.nextInt();
        String funcao = "funcionario";
        scanner.nextLine();
        if (escolhaFuncao == 1) {
            funcao = "gerente";
        }

        UsuarioModel usuario = new UsuarioModel(escolhaFuncao, cpf, nome, telefone, email, login, senha, funcao);

        service.cadastrarUsuario(usuario);
    }

    private static void alterarUsuario() {
        System.out.print("Informe o CPF do usuário à ser alterado: ");
        String cpfBusca = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Função (1 - Gerente, 2 - Funcionário): ");
        int escolhaFuncao = scanner.nextInt();
        String funcao = "funcionario";
        scanner.nextLine();
        if (escolhaFuncao == 1) {
            funcao = "gerente";
        }

        UsuarioModel usuario = new UsuarioModel(escolhaFuncao, cpf, nome, telefone, email, login, senha, funcao);

        service.alterarUsuario(cpfBusca, usuario);
    }

    private static void pesquisarUsuario() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        UsuarioModel usuario = service.pesquisarUsuario(cpf);

        if (usuario != null) {
            System.out.println("ID: " + usuario.getId() + " | " +
                    " CPF: " + usuario.getCpf() + " | " +
                    " Nome: " + usuario.getNome() + " | " +
                    " Telefone: " + usuario.getTelefone() + " | " +
                    " Email: " + usuario.getEmail() + " | " +
                    " Login: " + usuario.getLogin() + " | " +
                    " Função: " + usuario.getFuncao());
        }
    }

    private static void listarUsuarios() {
        List<UsuarioModel> usuarios = service.listarUsuarios();

        if (usuarios.size() > 0) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println("ID: " + usuarios.get(i).getId() + " | " +
                        " CPF: " + usuarios.get(i).getCpf() + " | " +
                        " Nome: " + usuarios.get(i).getNome() + " | " +
                        " Telefone: " + usuarios.get(i).getTelefone() + " | " +
                        " Email: " + usuarios.get(i).getEmail() + " | " +
                        " Login: " + usuarios.get(i).getLogin() +
                        " Função: " + usuarios.get(i).getFuncao());
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}
