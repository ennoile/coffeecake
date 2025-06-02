package br.edu.ufam.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import br.edu.ufam.model.ProdutoModel;
import br.edu.ufam.service.ProdutoService;

public class ProdutoView {
    private static Scanner scanner = new Scanner(System.in);
    private static ProdutoService service = new ProdutoService();

    public static void executar(){
        int opcao = 1;

        while(opcao != 0){
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
                case  3:
                    pesquisarProduto();
                    break;
                case 4:
                    listarProdutos();
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

    public static void menu(){
        System.out.println("--------PRODUTO--------");
        System.out.println("| 1 - Cadastrar       |");
        System.out.println("| 2 - Alterar         |");
        System.out.println("| 3 - Pesquisar (NOME) |");
        System.out.println("| 4 - Listar          |");
        System.out.println("| 0 - Voltar          |");
        System.out.print("Opção: ");
    }

    public static void cadastrarProduto(){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Preço: ");
        Float preco = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Descrição: ");
        String descricao = scanner.nextLine();

        ProdutoModel produto = new ProdutoModel(0, nome, preco, descricao);
        service.cadastrarProduto(produto);
    }

    public static void alterarProduto(){
        System.out.println("Informe o nome do produto que deseja alterar: ");
        String nomeBusca = scanner.nextLine();
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Preco: ");
        Float preco = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Descrição: ");
        String descricao = scanner.nextLine();

        ProdutoModel produto = new ProdutoModel(0, nome, preco, descricao);
        service.alterarProduto(nomeBusca, produto);


    }

    public static void pesquisarProduto(){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        ProdutoModel produto = service.pesquisarProduto(nome);

        if(produto != null){
            System.out.println("ID: "+ produto.getId()+"| "+
                    "Nome: "+produto.getNome()+" | "+
                    "Preco: "+ produto.getPreco()+" | "+
                    "Descriçaão: "+ produto.getDescricao());
        }
    }

    public static void listarProdutos(){
        List<ProdutoModel> produtos = service.listarProdutos();
        
        if(produtos.size() > 0 ){
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
            for(int i = 0; i < produtos.size(); i++){
                System.out.println("Id: "+ produtos.get(i).getId()+" | "+
                "Nome: "+ produtos.get(i).getNome()+" | "+
                "Preço: "+ produtos.get(i).getPreco()+" | "+
                "Descrição: "+ produtos.get(i).getDescricao());
        }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        }
        
    }

}
