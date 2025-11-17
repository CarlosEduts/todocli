package com.todocli.view.components;

import com.todocli.model.Task;
import com.todocli.util.CliUtils;
import com.todocli.enums.TaskOption;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class TaskDetail {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static TaskOption show(Task task) {
        while (true) {
            print(task);
            System.out.print("\nAção selecionada >> ");
            char selected = SCANNER.next().toUpperCase().charAt(0);

            Optional<TaskOption> option = TaskOption.fromChar(selected);
            if (option.isPresent()) {
                return option.get();
            }

            Message.error("Ação indisponível, selecione uma ação válida");
        }
    }

    /* ------------ MÉTODOS PRIVADOS AUXILIARES ------------ */
    private static void print(Task task) {
        CliUtils.clear();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                      DETALHES DA TAREFA                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ ID: %03d                                                                                      ║\n", task.getId());
        System.out.printf("║ Data: %s                                                                             ║\n", task.getDate().format(DATE_FORMAT));
        System.out.printf("║ Hora: %s                                                                                  ║\n", task.getTime().format(TIME_FORMAT));
        System.out.printf("║ Título: %s ║\n", CliUtils.truncateOrPad(task.getTitle(), 84));
        System.out.printf("║ Descrição: %s ║\n", CliUtils.truncateOrPad(task.getDescription(), 81));
        System.out.printf("║ Status: %s ║\n", CliUtils.truncateOrPad(task.isCompleted() ? "Concluída" : "Pendente", 84));
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %s  %s  [B] Voltar                                         ║\n", (task.isCompleted() || task.isDeleted() ? " ".repeat(25) : "[M] Marcar como concluída"), (task.isDeleted() ? "[R] Restaurar" : "[D] Excluir  "));
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
}
