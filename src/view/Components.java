package view;

import model.Task;
import util.CliUtils;
import model.Colors;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Components {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void info(Colors type, String message) {
        String border = "═".repeat(message.length() + 28);

        CliUtils.clear();
        System.out.println(type.getColor());
        System.out.println("╔" + border + "╗");
        System.out.println("║              " + message.toUpperCase() + "              ║");
        System.out.println("╚" + border + "╝");
        System.out.println("\033[0m");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void home() {
        CliUtils.clear();
        System.out.println("""      
                ╔═════════════════════════════════════════════════════════════════════╗
                ║                         JAVA TODO CLI - MENU                        ║
                ╠═════════════════════════════════════════════════════════════════════╣
                ║ [L] Listar tarefas       [C] Criar tarefa       [F] Filtrar         ║
                ║ [S] Buscar tarefa        [Q] Sair                                   ║
                ╠═════════════════════════════════════════════════════════════════════╣
                ║ Atalho: Pressione a tecla entre colchetes e ENTER.                  ║
                ╚═════════════════════════════════════════════════════════════════════╝
                """);
    }

    public static void tasks(List<Task> tasks) {
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
                    truncateOrPad(task.getTitle(), 30),
                    (task.isCompleted() ? "x" : " "),
                    truncateOrPad(task.getDescription(), 30)
            );
        }
        System.out.println("╠═════╩════════════╩═══════╩════════════════════════════════╩═══════╩════════════════════════════════╣");
        System.out.println("║                               Selecione uma tarefa a partir de seu ID                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void task(Task task) {
        CliUtils.clear();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                      DETALHES DA TAREFA                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ ID: %03d                                                                                      ║\n", task.getId());
        System.out.printf("║ Data: %s                                                                             ║\n", task.getDate().format(DATE_FORMAT));
        System.out.printf("║ Hora: %s                                                                                  ║\n", task.getTime().format(TIME_FORMAT));
        System.out.printf("║ Título: %s ║\n", truncateOrPad(task.getTitle(), 84));
        System.out.printf("║ Descrição: %s ║\n", truncateOrPad(task.getDescription(), 81));
        System.out.printf("║ Status: %s ║\n", truncateOrPad(task.isCompleted() ? "Concluída" : "Pendente", 84));
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                   [E] Editar  [M] Marcar como concluída  [D] Excluir  [B] Voltar             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void exclude(Task task) {
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
                truncateOrPad(task.getTitle(), 28)
        );
    }

    public static void confirmCompleted(Task task) {
        System.out.printf("""
                ╔════════════════════════════════╗
                ║   MARCAR COMO CONCLUÍDA        ║
                ╠════════════════════════════════╣
                ║ Deseja marcar a tarefa %03d como ║
                ║ concluída?                     ║
                ║                                ║
                ║ [Y] Sim    [N] Não             ║
                ╚════════════════════════════════╝
                """, task.getId());
    }

    // Métodos auxiliares privados
    private static String truncateOrPad(String input, int maxLength) {
        if (input.length() < maxLength) {
            return input + " ".repeat(maxLength - input.length());
        } else {
            return input.substring(0, maxLength - 3) + "...";
        }
    }
}
