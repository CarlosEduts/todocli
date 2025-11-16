package com.todocli.view.components;

import com.todocli.model.Task;
import com.todocli.util.CliUtils;

import java.util.Scanner;

public class ConfirmBox {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean taskDelete(Task task) {
        printDelete(task);

        System.out.print("\nAção selecionada >> ");
        char selected = SCANNER.next().toUpperCase().charAt(0);
        return selected == 'Y';
    }

    public static boolean taskComplete(Task task) {
        printComplete(task);

        System.out.print("\nAção selecionada >> ");
        char selected = SCANNER.next().toUpperCase().charAt(0);
        return selected == 'Y';
    }

    private static void printDelete(Task task) {
        System.out.printf("""
                        ╔════════════════════════════════╗
                        ║      CONFIRMAR EXCLUSÃO        ║
                        ╠════════════════════════════════╣
                        ║ Tem certeza que deseja excluir ║
                        ║ a tarefa ID %03d?               ║
                        ║ - %s ║
                        ║                                ║
                        ║ [Y] Sim    [N] Não             ║
                        ╚════════════════════════════════╝
                        """,
                task.getId(),
                CliUtils.truncateOrPad(task.getTitle(), 28)
        );
    }

    private static void printComplete(Task task) {
        System.out.printf("""
                ╔═════════════════════════════════╗
                ║   MARCAR COMO CONCLUÍDA         ║
                ╠═════════════════════════════════╣
                ║ Deseja marcar a tarefa %03d como ║
                ║ concluída?                      ║
                ║                                 ║
                ║ [Y] Sim    [N] Não              ║
                ╚═════════════════════════════════╝
                """, task.getId());
    }
}
