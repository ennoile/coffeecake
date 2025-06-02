package br.edu.ufam.view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import br.edu.ufam.model.EstoqueProduto;
import br.edu.ufam.model.ProdutoModel;
import br.edu.ufam.service.ProdutoService;

public class ProdutoView {
    private static Scanner scanner = new Scanner(System.in);
    private static ProdutoService service = new ProdutoService();

    public static void executar() {
        int opcao = 1;

        while (opcao != 0) {
            menu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    pesquisarProduto();
                    break;
                case 4:
                    alterarEstoque();
                    break;
                case 5:
                    listarProdutos();
                    break;
                case 6:
                    listarEstoque();
                    break;
                case 0:
                    System.out.println("Voltando");
                    break;
                default:
                    System.out.println("Opção Invalida! Tente Novamente.");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("--------PRODUTO--------");
        System.out.println("| 1 - Cadastrar       |");
        System.out.println("| 2 - Alterar         |");
        System.out.println("| 3 - Pesquisar (NOME)|");
        System.out.println("| 4 - Alterar estoque |");
        System.out.println("| 5 - Listar          |");
        System.out.println("| 6 - Listar estoque  |");
        System.out.println("| 0 - Voltar          |");
        System.out.print("Opção: ");
    }

    public static void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        Float preco = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        ProdutoModel produto = new ProdutoModel(0, nome, preco, descricao);
        produto = service.cadastrarProduto(produto);

        if (produto != null) {
            System.out.print("Quantidade em estoque: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();
            LocalDateTime dataEntrada = LocalDateTime.now();

            EstoqueProduto estoque = new EstoqueProduto(0, produto, quantidade, dataEntrada);
            service.cadastrarEstoque(estoque);
        }
    }

    public static void alterarProduto() {
        System.out.println("Informe o nome do produto que deseja alterar: ");
        String nomeBusca = scanner.nextLine();

        ProdutoModel produtoExistente = service.pesquisarProduto(nomeBusca);
        if (produtoExistente == null) {
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preco: ");
        Float preco = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        ProdutoModel produto = new ProdutoModel(0, nome, preco, descricao);
        service.alterarProduto(nomeBusca, produto);

    }

    public static void alterarEstoque() {
        System.out.print("Informe o nome do produto que deseja alterar o estoque: ");
        String nomeBusca = scanner.nextLine();

        ProdutoModel produtoExistente = service.pesquisarProduto(nomeBusca);
        if (produtoExistente == null) {
            return;
        }

        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        LocalDateTime dataEntrada = LocalDateTime.now();

        EstoqueProduto estoque = new EstoqueProduto(0, produtoExistente, quantidade, dataEntrada);
        service.alterarEstoque(estoque);
    }

    public static void pesquisarProduto() {
        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        ProdutoModel produto = service.pesquisarProduto(nome);

        if (produto != null) {
            System.out.println("ID: " + produto.getId() + "| " +
                    "Nome: " + produto.getNome() + " | " +
                    "Preco: " + produto.getPreco() + " | " +
                    "Descriçaão: " + produto.getDescricao());
        }
    }

    public static void listarProdutos() {
        List<ProdutoModel> produtos = service.listarProdutos();

        if (produtos.size() > 0) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println("Id: " + produtos.get(i).getId() + " | " +
                        "Nome: " + produtos.get(i).getNome() + " | " +
                        "Preço: " + produtos.get(i).getPreco() + " | " +
                        "Descrição: " + produtos.get(i).getDescricao());
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");

        }

    }

    public static void listarEstoque() {
        List<EstoqueProduto> estoque = service.listarEstoque();

        if (estoque.size() > 0) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < estoque.size(); i++) {
                System.out.println("Id: " + estoque.get(i).getId() + " | " +
                        "Produto: " + estoque.get(i).getProduto().getNome() + " | " +
                        "Quantidade: " + estoque.get(i).getQuantidade() + " | " +
                        "Data de Entrada: " + estoque.get(i).getDataEntrada());
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------");

        }
    }
}
