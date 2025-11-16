package com.todocli.view.components;

import com.todocli.model.Task;
import com.todocli.util.CliUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TasksList {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static Task show(List<Task> tasks) {
        Task selectedTask = null;
        int selectedId;

        while (true) {
            print(tasks);
            System.out.print("\nID selecionado >> ");
            selectedId = SCANNER.nextInt();
            SCANNER.nextLine();

            for (Task task : tasks) {
                if (task.getId() == selectedId) {
                    selectedTask = task;
                    return selectedTask;
                }
            }

            Message.error("ID indisponível, selecione um ID válido");
        }

    }

    private static void print(List<Task> tasks) {
        int quantity = tasks.size();
        int completedQuantity = Math.toIntExact(tasks.stream().filter(Task::isCompleted).count());

        CliUtils.clear();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ TAREFAS: (Concluídas %03d/%03d)       Ordenar: data ↑     Filtro: nenhum                             ║\n", completedQuantity, quantity);
        System.out.println("╠═════╦════════════╦═══════╦════════════════════════════════╦═══════╦════════════════════════════════╣");
        System.out.println("║ ID  ║    DATA    ║ HORA  ║ TÍTULO                         ║  ST   ║ DESCRIÇÃO                      ║");
        System.out.println("╠═════╬════════════╬═══════╬════════════════════════════════╬═══════╬════════════════════════════════╣");
        for (Task task : tasks) {
            System.out.printf("║ %03d ║ %s ║ %s ║ %s ║ [%s]   ║ %s ║\n",
                    task.getId(),
                    task.getDate().format(DATE_FORMAT),
                    task.getTime().format(TIME_FORMAT),
                    CliUtils.truncateOrPad(task.getTitle(), 30),
                    (task.isCompleted() ? "x" : " "),
                    CliUtils.truncateOrPad(task.getDescription(), 30)
            );
        }
        System.out.println("╠═════╩════════════╩═══════╩════════════════════════════════╩═══════╩════════════════════════════════╣");
        System.out.println("║                               Selecione uma tarefa a partir de seu ID                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
}
