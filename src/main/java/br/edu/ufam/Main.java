package br.edu.ufam;

import java.util.Scanner;

import br.edu.ufam.view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (true) {
            System.out.println("-------COFFECAKE-------");
            System.out.println("| 1 - Usuario |");
            System.out.println("| 0 - Sair |");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    UsuarioView.executar();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
