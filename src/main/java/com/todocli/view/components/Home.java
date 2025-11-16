package com.todocli.view.components;

import com.todocli.enums.HomeOption;
import com.todocli.util.CliUtils;

import java.util.Optional;
import java.util.Scanner;

public class Home {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static HomeOption show() {
        while (true) {
            print();
            System.out.print("\nAção selecionada >> ");
            char selected = SCANNER.next().toUpperCase().charAt(0);

            Optional<HomeOption> option = HomeOption.fromChar(selected);
            if (option.isPresent()) {
                return option.get();
            }

            Message.error("Ação indisponível, selecione uma ação válida");
        }
    }

    /* ------------ MÉTODOS PRIVADOS AUXILIARES ------------ */
    private static void print() {
        CliUtils.clear();
        System.out.println("""      
                ╔═════════════════════════════════════════════════════════════════════╗
                ║                         JAVA TODO CLI - MENU                        ║
                ╠═════════════════════════════════════════════════════════════════════╣
                ║ [L] Listar tarefas       [C] Criar tarefa       [F] Filtrar         ║
                ║ [S] Buscar tarefa        [D] Tarefas Excluídas  [Q] Sair            ║
                ╠═════════════════════════════════════════════════════════════════════╣
                ║ Atalho: Pressione a tecla entre colchetes e ENTER.                  ║
                ╚═════════════════════════════════════════════════════════════════════╝
                """);
    }
}
