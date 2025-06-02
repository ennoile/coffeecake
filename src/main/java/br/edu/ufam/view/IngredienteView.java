package br.edu.ufam.view;

import java.util.List;
import java.util.Scanner;

import br.edu.ufam.model.IngredienteModel;
import br.edu.ufam.service.IngredienteService;

public class IngredienteView {
    private static Scanner scanner = new Scanner(System.in);
    private static IngredienteService service = new IngredienteService();

    public static void executar() {
        int opcao = 1;

        while (opcao != 0) {
            menu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarIngrediente();
                    break;
                case 2:
                    alterarIngrediente();
                    break;
                case 3:
                    pesquisarIngrediente();
                    break;
                case 4:
                    listarIngredientes();
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
        System.out.println("------INGREDIENTE------");
        System.out.println("| 1 - Cadastrar       |");
        System.out.println("| 2 - Alterar         |");
        System.out.println("| 3 - Pesquisar (Nome)|");
        System.out.println("| 4 - Listar          |");
        System.out.println("| 0 - Voltar          |");
        System.out.print("Opção: ");
    }

    private static void cadastrarIngrediente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        IngredienteModel ingrediente = new IngredienteModel(0, nome, descricao);

        service.cadastrarIngrediente(ingrediente);
    }

    private static void alterarIngrediente() {
        System.out.print("Informe o nome do ingrediente à ser alterado: ");
        String nomeBusca = scanner.nextLine();
        IngredienteModel ingredienteExistente = service.pesquisarIngrediente(nomeBusca);

        if (ingredienteExistente == null) {
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        IngredienteModel ingrediente = new IngredienteModel(0, nome, descricao);

        service.alterarIngrediente(nomeBusca, ingrediente);
    }

    private static void pesquisarIngrediente() {
        System.out.print("Nome: ");
        String nomeBusca = scanner.nextLine();

        IngredienteModel ingrediente = service.pesquisarIngrediente(nomeBusca);

        if (ingrediente != null) {
            System.out.println("ID: " + ingrediente.getId() + " | " +
                    " Nome: " + ingrediente.getNome() + " | " +
                    " Telefone: " + ingrediente.getDescricao());
        }
    }

    private static void listarIngredientes() {
        List<IngredienteModel> ingredientes = service.listarIngrediente();

        if (ingredientes.size() > 0) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < ingredientes.size(); i++) {
                System.out.println("ID: " + ingredientes.get(i).getId() + " | " +
                        " Nome: " + ingredientes.get(i).getNome() + " | " +
                        " Descrição: " + ingredientes.get(i).getDescricao());
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}
