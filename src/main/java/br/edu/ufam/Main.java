package br.edu.ufam;

import java.util.Scanner;

import br.edu.ufam.service.AutenticacaoService;
import br.edu.ufam.view.IngredienteView;
import br.edu.ufam.view.ProdutoView;
import br.edu.ufam.view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        AutenticacaoService autenticacaoService = new AutenticacaoService();

        while (!autenticacaoService.isAutenticado()) {
            autenticacaoService.login();
        }

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (true) {
            System.out.println("-------COFFECAKE-------");
            System.out.println("| 1 - Usuario         |");
            System.out.println("| 2 - Ingrediente     |");
            System.out.println("| 3 - Produto |");
            System.out.println("| 0 - Sair            |");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    UsuarioView.executar();
                    break;
                case 2:
                    IngredienteView.executar();
                    break;
                case 3:
                    ProdutoView.executar();
                    break;
                case 0:
                    scanner.close();
                    autenticacaoService.logout();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
