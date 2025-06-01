package br.edu.ufam.view;

import java.util.Scanner;

import br.edu.ufam.service.AutenticacaoService;

public class AutenticacaoView {
    private static Scanner scanner = new Scanner(System.in);
    private static AutenticacaoService autenticacaoService;

    public static void executar() {
        while (!autenticacaoService.isAutenticado()) {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    autenticacaoService.login();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        ;

        scanner.close();
    }

    private static void menu() {
        System.out.println("-------COFFECAKE-------");
        System.out.println("| 1 - Login           |");
        System.out.println("| 0 - Sair            |");
        System.out.print("Opção: ");
    }
}
