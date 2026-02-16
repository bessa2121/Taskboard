package br.com.taskboard.ui;

import br.com.taskboard.service.BoardService;
import br.com.taskboard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleUI implements CommandLineRunner {

    private final BoardService boardService;
    private final CardService cardService;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        menuPrincipal();
    }

    private void menuPrincipal() {

        int option;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Criar novo board");
            System.out.println("2 - Selecionar board");
            System.out.println("3 - Excluir board");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> criarBoard();
                case 2 -> selecionarBoard();
                case 3 -> excluirBoard();
            }

        } while (option != 4);
    }

    private void criarBoard() {
        System.out.print("Nome do board: ");
        String nome = scanner.nextLine();

        boardService.createBoard(nome);
        System.out.println("Board criado com sucesso!");
    }

    private void selecionarBoard() {

        boardService.listBoards()
                .forEach(b -> System.out.println(b.getId() + " - " + b.getName()));

        System.out.print("ID do board: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        menuBoard(id);
    }

    private void excluirBoard() {
        System.out.print("ID do board: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        boardService.deleteBoard(id);
        System.out.println("Board excluído.");
    }

    private void menuBoard(Long boardId) {

        int option;

        do {
            System.out.println("\n===== MENU BOARD =====");
            System.out.println("1 - Criar Card");
            System.out.println("2 - Mover Card");
            System.out.println("3 - Cancelar Card");
            System.out.println("4 - Bloquear Card");
            System.out.println("5 - Desbloquear Card");
            System.out.println("6 - Fechar Board");
            System.out.print("Escolha: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> criarCard(boardId);
                case 2 -> moverCard();
                case 3 -> cancelarCard();
                case 4 -> bloquearCard();
                case 5 -> desbloquearCard();
            }

        } while (option != 6);
    }

    private void criarCard(Long boardId) {
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();

        cardService.createCard(boardId, title, desc);
        System.out.println("Card criado.");
    }

    private void moverCard() {
        System.out.print("ID do card: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        cardService.moveToNext(id);
        System.out.println("Card movido.");
    }

    private void cancelarCard() {
        System.out.print("ID do card: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        cardService.cancelCard(id);
        System.out.println("Card cancelado.");
    }

    private void bloquearCard() {
        System.out.print("ID do card: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Motivo: ");
        String reason = scanner.nextLine();

        cardService.blockCard(id, reason);
        System.out.println("Card bloqueado.");
    }

    private void desbloquearCard() {
        System.out.print("ID do card: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Motivo: ");
        String reason = scanner.nextLine();

        cardService.unblockCard(id, reason);
        System.out.println("Card desbloqueado.");
    }
}
